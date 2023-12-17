package cn.itcast.travel.service.impl;


import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 校验用户名
     * @param username
     * @return
     */
    @Override
    public User selectUserByUserName(String username) {
        return userDao.selectUserByUserName(username);
    }

    /**
     * 注册数据并保存到数据
     * @param user
     */
    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    /**
     * 校验手机号
     * @param telephone
     * @return
     */
    @Override
    public User selectUserByTelephone(String telephone) {
        return userDao.selectUserByTelephone(telephone);
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    @Override
    public User selectUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    @Override
    public User selectUserByUserNameAndPassword(String username, String password) {
        return userDao.selectUserByUserNameAndPassword(username, password);
    }
}
