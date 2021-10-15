package com.jun.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractCrudDao<T> {
	private final SessionFactory sessionFactory;
	private final Class<T> entityClass;
	private final String entityName;

	public AbstractCrudDao(SessionFactory sessionFactory, Class<T> entityClass, String entityName) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
		this.entityName = entityName;
	}
	
	public T save(T entity) {
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}
	
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
	
	public T find(long id) {
		return sessionFactory.getCurrentSession().get(entityClass, id);
	}
	
	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(entityClass).list();
	}
}
