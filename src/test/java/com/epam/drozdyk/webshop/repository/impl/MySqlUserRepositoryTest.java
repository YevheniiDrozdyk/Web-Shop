package com.epam.drozdyk.webshop.repository.impl;

import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.database.MySqlConnection;
import com.epam.drozdyk.webshop.exception.MySqlConnectionException;
import com.epam.drozdyk.webshop.exception.RepositoryException;
import com.epam.drozdyk.webshop.mapper.model.impl.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.drozdyk.webshop.constant.Constant.Message.CANNOT_FIND_CONNECTION_MESSAGE;
import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.SELECT_USER_BY_EMAIL;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest({MySqlConnection.class, MySqlUserRepository.class})
public class MySqlUserRepositoryTest {
    private static final String USER_MAIL = "Yevhenii_Drozdyk@epam.com";

    private MySqlUserRepository userRepository;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private UserMapper userMapper;
    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        userRepository = new MySqlUserRepository();
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(MySqlConnection.class);
    }

    @Test
    public void testGetUserAllInvocationsOfSqlMethodsAreSuccessful() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(SELECT_USER_BY_EMAIL)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(userMapper.mapUser(resultSet)).thenReturn(user);

        userRepository.getUser(USER_MAIL);
        Mockito.verify(preparedStatement).close();
    }

    @Test(expected = RepositoryException.class)
    public void testGetUserFailedToPrepareStatement() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenThrow(new SQLException());

        userRepository.getUser(USER_MAIL);
    }

    @Test(expected = RepositoryException.class)
    public void testGetUserFailedSetEmailArgumentForPrepareStatement() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        doThrow(new SQLException()).when(preparedStatement).setString(1, USER_MAIL);

        userRepository.getUser(USER_MAIL);
    }

    @Test(expected = RepositoryException.class)
    public void testGetUserFailedToExecuteQuery() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        doThrow(new SQLException()).when(preparedStatement).executeQuery();

        userRepository.getUser(USER_MAIL);
        Mockito.verify(preparedStatement).close();
    }

    @Test(expected = RepositoryException.class)
    public void testGetUserFailedToCloseResultSet() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doThrow(new SQLException()).when(resultSet).close();

        userRepository.getUser(USER_MAIL);
    }

    @Test
    public void testAddNewUserSuccessfulAttempt() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);

        userRepository.addNewUser(user);
        Mockito.verify(preparedStatement).execute();
    }

    @Test(expected = RepositoryException.class)
    public void testAddNewUserFailedToPrepareStatement() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenThrow(new SQLException());

        userRepository.addNewUser(user);
    }

    @Test(expected = RepositoryException.class)
    public void testAddNewUserFailedToExecuteOnPrepareStatement() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenReturn(connection);
        when(connection.prepareStatement(any())).thenReturn(preparedStatement);
        doThrow(new SQLException()).when(preparedStatement).execute();

        userRepository.addNewUser(user);
    }

    @Test(expected = MySqlConnectionException.class)
    public void shouldThrowExceptionWhenTryToGetCurrentConnectionFromMySqlConnection() throws Exception {
        when(MySqlConnection.getCurrentConnection()).thenThrow(new MySqlConnectionException(CANNOT_FIND_CONNECTION_MESSAGE));
        userRepository.getUser(USER_MAIL);
    }
}