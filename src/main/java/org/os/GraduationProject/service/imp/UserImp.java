package org.os.GraduationProject.service.imp;

import org.os.GraduationProject.dao.UserDao;
import org.os.GraduationProject.pojo.User;
import org.os.GraduationProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImp implements UserService {
    @Autowired(required = false)
    UserDao dao;

    @Override
    public User findByUser(String user) {
        return dao.findByUser(user);
    }

    @Override
    public int insertUser(User user) {
        return dao.insertUser(user);
    }

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }
}
