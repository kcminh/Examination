package com.t3h.elibrary.service;

import com.t3h.elibrary.entity.ModelUser;
import com.t3h.elibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<ModelUser> listUser(String username){
        if(username == null || username.isEmpty()) {
            return userRepository.findAll();
        }else {
            return userRepository.findByUsernameLikeIgnoreCase(username);
        }
    }
//    public ModelUser getModelUserByUsername(String username){
//        return userRepository.findById(username)
//                .orElse(null);
//    }
}
