package cn.wpc.service;

import cn.wpc.entiy.User;

public interface UserService {

	boolean regist(User user);

	boolean active(String code);

	User login(User user);

	

}
