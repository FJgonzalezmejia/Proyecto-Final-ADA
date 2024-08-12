package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User(1L, "John Doe");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindUserById() {
        User user = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findUserById(1L);

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testSaveUser_Exception() {
        User user = new User(1L, "John Doe");
        when(userRepository.save(user)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.saveUser(user));

        assertEquals("Database error", exception.getMessage());
    }
}

