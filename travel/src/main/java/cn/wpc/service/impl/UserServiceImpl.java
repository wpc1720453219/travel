package cn.wpc.service.impl;

import cn.wpc.dao.UserDao;
import cn.wpc.dao.impl.UserDaoImpl;
import cn.wpc.entiy.User;
import cn.wpc.service.UserService;
import cn.wpc.util.MailUtils;
import cn.wpc.util.UuidUtil;

public class UserServiceImpl implements UserService {
	
   private UserDao userDao=new UserDaoImpl();
   
   public boolean regist(User user) {
		
	   User u = userDao.findByUsername(user.getUsername());
	   if(u != null){
           //用户名存在，注册失败
           return false;
       }
	   user.setCode(UuidUtil.getUuid());
	   user.setStatus("N");
	   userDao.save(user);
	   
	   //3.激活邮件发送，邮件正文？

       String content="<a href='http://localhost:8080/web/user/active?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";

       MailUtils.sendMail(user.getEmail(),content,"激活邮件");

	   return true;
	}
@Override
public boolean active(String code) {
    
	 User user=userDao.findByCode(code);
	 if(user!=null) {
		 userDao.updateStatus(user);
		 return true;
	 }else {
		 return false;
	 }

}
@Override
public User login(User user) {
	return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
}

}
