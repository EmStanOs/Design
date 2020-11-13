package org.os.GraduationProject.service;

import org.os.GraduationProject.pojo.User;

public interface UserService {
    User findByUser(String user);
    int insertUser(User user);
    User findById(int id);
}
