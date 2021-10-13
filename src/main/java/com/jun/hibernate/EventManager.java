package com.jun.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jun.hibernate.domain.Event;
import com.jun.hibernate.util.HibernateUtil;

public class EventManager {
	public static void main(String[] args) {
		EventManager manager = new EventManager();
		
		String arg = "list";
		
		if(arg.equals("store")) {
			manager.createAndStoreEvent("My Event", new Date());
		} else if (arg.equals("list")) {
			List<Event> events = manager.listEvents();
			for (Event event : events) {
				System.out.println("Event: " + event.getTitle() + " Time: " + event.getDate());
			}
		}
	}

	private List<Event> listEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List result = session.createQuery("from Event").list();
		
		session.getTransaction().commit();
		return result;
	}

	private void createAndStoreEvent(String title, Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(date);
		session.save(theEvent);
		
		session.getTransaction().commit();
	}
}
