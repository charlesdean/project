package cn.itcast.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	//添加
	public void save(T entity);
	//修改
	public void update(T entity);
	//删除
	public void delete(Serializable id);
	//查询单个
	public T findObjectbyId(Serializable id);
	//查询所有
	public List<T> findObjects();
}
