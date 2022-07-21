package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.ModelUser;
import com.t3h.elibrary.entity.UserInfo;
import com.t3h.elibrary.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Class làm nhiệm vụ test UserService
 */
@SpringBootTest
public class UserServiceTest {
    // Junit + Mockito

    /**
     * tạo  real Object cho service
     * trong class Test chỉ duy nhất InjectMocks
     * @InjectMock
     */
    @InjectMocks
    private UserService userService;

    /* tạo properties tương ứng trong service UserService */
    @Mock
    private UserRepository userRepository;


    /* tạo case test tương ứng cho funcs */
    @Test
    @DisplayName("test list user success")
    public void testListUserSuccess(){
        // setup
        when(userRepository.findAll()).thenReturn(List.of(new UserInfo()));

        // execute
        List<UserInfo> users = userService.listUser("");

        // assert
        assertTrue(!users.isEmpty());
    }


    //
//    @Test
//    @DisplayName("get user return null")
//    public void testGetUserByUserNameReturnNull(){
//        // setup
//
//        // execute
//        ModelUser user = userService.getModelUserByUsername("jonh");
//
//        // assert
//        assertNull(user);
//    }
//    //
//    @Test
//    @DisplayName("get user return success")
//    public void testGetUserByUserNameSuccess(){
//        // setup
//        String username = "jonh";
//        when(userRepository.findById(username)).thenReturn(Optional.of(new ModelUser()));
//        // execute
//        ModelUser user = userService.getModelUserByUsername(username);
//
//        // assert
//        assertNotNull(user);
//    }
}
