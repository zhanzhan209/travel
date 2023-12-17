package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User selectUserByUserName(String username);

    void insert(User user);

    User selectUserByTelephone(String telephone);

    User selectUserByEmail(String email);

    User selectUserByUserNameAndPassword(String username, String password);
}
