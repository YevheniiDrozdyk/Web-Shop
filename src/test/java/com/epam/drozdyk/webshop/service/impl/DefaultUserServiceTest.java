package com.epam.drozdyk.webshop.service.impl;

import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {
    private static final String EXISTENCE_USER_EMAIL = "Yevhenii_Drozdyk@epam.com";
    private static final String NONEXISTENCE_USER_EMAIL = "Ivan_Petrov@epam.com";
    private static final String VALID_USER_PASSWORD = "Password0";
    private static final String INVALID_USER_PASSWORD = "passworD1";
    private static final String FIRST_NAME = "Yevhenii";
    private static final String LAST_NAME = "Drozdyk";
    private static final String USER_AVATAR = "Yevhenii_Drozdyk@epam.com.jpg";

    @InjectMocks
    private DefaultUserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TransactionHandler transactionHandler;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = User.newBuilder()
                .setId(1)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(NONEXISTENCE_USER_EMAIL)
                .setPassword(VALID_USER_PASSWORD)
                .setAvatar(USER_AVATAR)
                .build();
    }

    @Test
    public void shouldLoginUserWhenPasswordIsVadid() throws Exception {
        when(userRepository.getUser(EXISTENCE_USER_EMAIL)).thenReturn(user);
        when(transactionHandler.doInTransaction(any())).thenReturn(user);

        final boolean condition = userService.login(EXISTENCE_USER_EMAIL, VALID_USER_PASSWORD);

        assertTrue(condition);
    }

    @Test
    public void shouldNotLoginUserWhenPasswordIsInvalid() throws Exception {
        when(userRepository.getUser(EXISTENCE_USER_EMAIL)).thenReturn(user);
        when(transactionHandler.doInTransaction(any())).thenReturn(user);

        final boolean condition = userService.login(EXISTENCE_USER_EMAIL, INVALID_USER_PASSWORD);

        assertFalse(condition);
    }

    @Test
    public void registerUser() throws Exception {
        userService.register(user);
        verify(transactionHandler).doInTransaction(any());
    }

    @Test
    public void shouldReturnTrueWhenUserExist() throws Exception {
        when(userRepository.getUser(NONEXISTENCE_USER_EMAIL)).thenReturn(user);
        when(transactionHandler.doInTransaction(any())).thenReturn(user);

        final boolean condition = userService.isExist(NONEXISTENCE_USER_EMAIL);

        assertTrue(condition);
    }

    @Test
    public void shouldReturnFalseWhenUserNotExist() throws Exception {
        when(userRepository.getUser(NONEXISTENCE_USER_EMAIL)).thenReturn(null);
        when(transactionHandler.doInTransaction(any())).thenReturn(null);

        final boolean condition = userService.isExist(NONEXISTENCE_USER_EMAIL);

        assertFalse(condition);
    }

    @Test
    public void getExistenceUser() throws Exception {
        when(userRepository.getUser(EXISTENCE_USER_EMAIL)).thenReturn(user);
        when(transactionHandler.doInTransaction(any())).thenReturn(user);

        final User actual = userService.getUser(EXISTENCE_USER_EMAIL);

        assertEquals(user, actual);
    }
    
    @Test
    public void getNonexistentUser() throws Exception {
        when(userRepository.getUser(NONEXISTENCE_USER_EMAIL)).thenReturn(null);
        when(transactionHandler.doInTransaction(any())).thenReturn(null);

        final User actual = userService.getUser(NONEXISTENCE_USER_EMAIL);

        assertNull(actual);
    }    
}