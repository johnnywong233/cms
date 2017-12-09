package com.johnny.cms.service;

import com.johnny.cms.domain.User;
import com.johnny.cms.domain.UserExample;
import com.johnny.cms.mapper.UserMapper;
import com.johnny.cms.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Johnny
 * Date: 2017/12/1
 * Time: 下午3:39
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 仅仅只是根据用户名username来检查是否存在
     *
     * @param user user
     * @return true/false
     */
    public boolean checkExist(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(user.getUserName());
        long count = userMapper.countByExample(example);
        return count != 0;
    }

    /**
     * 根据用户名username和password来检查是否存在
     *
     * @param user user
     * @return true/false
     */
    public boolean checkUsernameAndPassword(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(user.getUserName());
        //校验用户是否匹配
        long count = userMapper.countByExample(example);
        //校验密码是否相等
        List<User> users = userMapper.selectByExample(example);
        boolean isEqual = false;
        for (User user1 : users) {
            isEqual = MD5Util.JM(MD5Util.KL(MD5Util.MD5Encode(user.getUserPassword(), "UTF-8"))).equals(user1.getUserPassword());
        }
        return count != 0 && isEqual;
    }

    /**
     * 存储用户（先加密密码）
     *
     * @param user user
     */
    public void addUser(User user) {
        String encryptedPassword;
        try {
            //方法一：使用AES加密方法
//            encryptedPassword = EncryptUtil.encrypt(user.getUserPassword());
            //方法二：使用MD5加密方法
            encryptedPassword = MD5Util.MD5Encode(user.getUserPassword(), "UTF-8");
            user.setUserPassword(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.insert(user);
    }

    public int updateUser(User user) {
        String encryptedPassword;
        try {
//            encryptedPassword = EncryptUtil.encrypt(user.getUserPassword());
            encryptedPassword = MD5Util.MD5Encode(user.getUserPassword(), "UTF-8");
            user.setUserPassword(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMapper.updateByPrimaryKey(user);
    }

}
