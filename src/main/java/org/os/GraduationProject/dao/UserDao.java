package org.os.GraduationProject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.os.GraduationProject.pojo.User;

@Mapper
public interface UserDao {
    @Select("select * from user where user = #{user}")
    User findByUser(String user);

    @Insert("insert into user(id,user,password) values(#{id},#{user},#{password})")
    int insertUser(User user);

    @Select("SELECT * from User where id = #{id}")
    User findById(int id);
}
