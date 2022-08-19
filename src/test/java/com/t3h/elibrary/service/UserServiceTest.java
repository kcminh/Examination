package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.ModelUser;
import com.t3h.elibrary.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Test
    @DisplayName("test")
    public void testListUserSuccess(){
        when(userRepository.findAll()).thenReturn(List.of(new ModelUser()));

        List<ModelUser> users = userService.listUser("");

        assertTrue(!users.isEmpty());
    }
}
