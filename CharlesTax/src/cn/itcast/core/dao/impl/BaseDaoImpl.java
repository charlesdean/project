package cn.itcast.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.core.dao.BaseDao;
@SuppressWarnings("all")
public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	Class<T> clazz;
	 public BaseDaoImpl() {
		//спринй
		 ParameterizedType pt =	(ParameterizedType) getClass().getGenericSuperclass();
		 clazz = (Class<T>)pt.getActualTypeArguments()[0];
	}
	@Override
	public void save(T entity) {
		
		getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		
		getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		
		getHibernateTemplate().delete(findObjectbyId(id));
	}

	@Override
	public T findObjectbyId(Serializable id) {
		
		return 	getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		
		Query query = getSession().createQuery("FROM "+clazz.getSimpleName());

		List<T> list =(List<T>) query.list();
		return 	list;
	
	}

}
