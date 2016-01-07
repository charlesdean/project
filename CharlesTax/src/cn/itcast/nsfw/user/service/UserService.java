package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

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
		//����excel
		public void exportExcel(List<User> userList, ServletOutputStream outputStream);
		//����excel
		public void importExcel(File userExcel, String userExcelFileName);
}
