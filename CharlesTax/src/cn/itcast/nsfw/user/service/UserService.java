package cn.itcast.nsfw.user.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.nsfw.user.entity.User;

public interface UserService {
	
	//���
		public void save(User user);
		//�޸�
		public void update(User user);
		//ɾ��
		public void delete(Serializable id);
		//��ѯ����
		public User findObjectbyId(Serializable id);
		//��ѯ����
		public List<User> findObjects();
}
