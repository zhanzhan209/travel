package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from tab_user where username=#{username}")
    User selectUserByUserName(String username);


    @Insert("insert into tab_user (username,password,name,birthday,sex,telephone,email) values (#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email})")
    void insert(User user);

    @Select("select * from tab_user where telephone = #{telephone}")
    User selectUserByTelephone(String telephone);

    @Select("select * from tab_user where email=#{email}")
    User selectUserByEmail(String email);

//    @Select("select * from tab_user where username=#{arg0} and password=#{arg1}")
//    User selectUserByUserNameAndPassword(String username, String password);
    @Select("select * from tab_user where username=#{username} and password=#{password}")
    User selectUserByUserNameAndPassword(@Param("username") String username,
                                         @Param("password") String password);

}
