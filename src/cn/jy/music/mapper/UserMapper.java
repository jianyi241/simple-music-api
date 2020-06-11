package cn.jy.music.mapper;


import org.springframework.stereotype.Repository;

import cn.jy.music.pojo.User;
@Repository("userMapper")
public interface UserMapper {
	User login(User user);
}
