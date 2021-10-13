package com.jun.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import com.jun.hibernate.domain.Event;
import com.jun.hibernate.util.HibernateUtil;

public class EventDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	public Long add(Event event) {
		session.beginTransaction();
		
		Long savedId = (Long) session.save(event);
		
		session.getTransaction().commit();
		return savedId;
	}

	public List<Event> listEvents() {
		session.beginTransaction();

		List<Event> result = session.createQuery("from Event").list();

		session.getTransaction().commit();
		return result;
	}

}

