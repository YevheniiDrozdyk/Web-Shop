package com.epam.drozdyk.webshop.database;

import com.epam.drozdyk.webshop.exception.TransactionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.drozdyk.webshop.constant.Constant.Message.CANNOT_OBTAIN_DATA_SOURCE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionHandlerTest {
    @InjectMocks
    private TransactionHandler transactionHandler;
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private TransactionMethod method;

    @Test
    public void testDoInTransactionCommitSuccessful() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);

        transactionHandler.doInTransaction(() -> method);

        verify(connection).commit();
    }

    @Test(expected = TransactionException.class)
    public void testDoInTransactionCommitUnsuccessfulRollbackSuccessful() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        doThrow(new SQLException()).when(connection).commit();

        transactionHandler.doInTransaction(() -> method);

        verify(connection).commit();
        verify(connection).rollback();
    }

    @Test(expected = TransactionException.class)
    public void testDoInTransactionCommitUnsuccessfulRollbackUnsuccessful() throws Exception {
        when(dataSource.getConnection()).thenReturn(connection);
        doThrow(new SQLException()).when(connection).commit();
        doThrow(new SQLException()).when(connection).rollback();

        transactionHandler.doInTransaction(() -> method);

        verify(connection).commit();
        verify(connection).rollback();
    }

    @Test(expected = TransactionException.class)
    public void shouldThrowExceptionWhenNoConnectionInDataSource() throws Exception {
        when(dataSource.getConnection()).thenThrow(new SQLException(CANNOT_OBTAIN_DATA_SOURCE));
        transactionHandler.doInTransaction(() -> method);
    }
}