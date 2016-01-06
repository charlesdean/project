package cn.itcast.nsfw.user.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource 
	private UserDao userdao;
	@Override
	public void save(User user) {
		
			userdao.save(user);
	}

	@Override
	public void update(User user) {
		
		userdao.update(user);
	}

	@Override
	public void delete(Serializable id) {
		
		userdao.delete(id);
	}

	@Override
	public User findObjectbyId(Serializable id) {
		
		return userdao.findObjectbyId(id);
	}

	@Override
	public List<User> findObjects() {
		
		return userdao.findObjects();
	}

}
