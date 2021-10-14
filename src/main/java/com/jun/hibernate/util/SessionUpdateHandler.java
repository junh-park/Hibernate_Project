package com.jun.hibernate.util;

import org.hibernate.Session;

public class SessionUpdateHandler {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	public void sessionHandler(Statement stmt) {
		session.beginTransaction();
		stmt.statement(session);
		session.getTransaction().commit();
	}
}
