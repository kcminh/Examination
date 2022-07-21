package com.t3h.elibrary.service;

import com.t3h.elibrary.common.Constants;
import com.t3h.elibrary.entity.UserInfor;
import com.t3h.elibrary.repository.UserInforRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInforService {
    @Autowired
    private UserInforRepository userInforRepository;

    public List<UserInfor> getAllStudent() {
        return userInforRepository.listAllStudent(Constants.USER_TYPE_STUDENT);
    }

    public UserInfor getStudentByUsername(String username) throws Exception {
        Optional<UserInfor> userInforOptional = Optional.ofNullable(userInforRepository.getStudentByUsername(username));
        UserInfor userInfor;
        if (!userInforOptional.isEmpty()) {
            userInfor = userInforOptional.get();
        } else {
            throw new Exception("Book can not found!");
        }
        return userInfor;
    }

    public UserInfor saveUserInfor(UserInfor userInfor) {
            return userInforRepository.save(userInfor);
    }
}
