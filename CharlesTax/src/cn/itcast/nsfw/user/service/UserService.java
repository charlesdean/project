package cn.itcast.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.itcast.nsfw.user.entity.User;

public interface UserService {
	
		//添加
		public void save(User user);
		//修改
		public void update(User user);
		//删除
		public void delete(Serializable id);
		//查询单个
		public User findObjectbyId(Serializable id);
		//查询所有
		public List<User> findObjects();
		//导出excel
		public void exportExcel(List<User> userList, ServletOutputStream outputStream);
		//导入excel
		public void importExcel(File userExcel, String userExcelFileName);
}
