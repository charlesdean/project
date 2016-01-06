package cn.itcast.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	//���
	public void save(T entity);
	//�޸�
	public void update(T entity);
	//ɾ��
	public void delete(Serializable id);
	//��ѯ����
	public T findObjectbyId(Serializable id);
	//��ѯ����
	public List<T> findObjects();
}
