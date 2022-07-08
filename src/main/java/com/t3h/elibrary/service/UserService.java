package com.t3h.elibrary.service;

import com.t3h.elibrary.common.Constants;
import com.t3h.elibrary.entity.ModelUser;
import com.t3h.elibrary.entity.UserInfo;
import com.t3h.elibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserInfo> getAllStudent() {
        return userRepository.listAllStudent(Constants.USER_TYPE_STUDENT);
    }

    public UserInfo getStudentByUsername(String username) throws Exception {
        Optional<UserInfo> userInforOptional = Optional.ofNullable(userRepository.getStudentByUsername(username));
        UserInfo userInfor;
        if (!userInforOptional.isEmpty()) {
            userInfor = userInforOptional.get();
        } else {
            throw new Exception("Book can not found!");
        }
        return userInfor;
    }

    public UserInfo saveUserInfor(UserInfo userInfor) {
        return userRepository.save(userInfor);
    }

    public List<UserInfo> listUser(String username) {
        if (username == null || username.isEmpty()) {
            return userRepository.findAll();
        } else {
            return userRepository.findByUsernameLikeIgnoreCase(username);
        }
    }
//    public ModelUser getModelUserByUsername(String username){
//        return userRepository.findById(username)
//                .orElse(null);
//    }
}
