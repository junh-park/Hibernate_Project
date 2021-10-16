package com.jun.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import com.jun.hibernate.domain.Event;
import com.jun.hibernate.domain.Person;
import com.jun.hibernate.util.HibernateUtil;
import com.jun.hibernate.util.SessionUpdateHandler;

public class PersonDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();
	private SessionUpdateHandler handler = new SessionUpdateHandler();

	public Long add(Person person) {
		session.beginTransaction();

		Long savedId = (Long) session.save(person);

		session.getTransaction().commit();
		return savedId;
	}

	public Person get(Long id) {
		session.beginTransaction();
		Person person = session.get(Person.class, id);
		session.getTransaction().commit();
		return person;
	}

	public List<Person> getAll() {

		session.beginTransaction();

		List<Person> people = session.createQuery("from Person").list();

		session.getTransaction().commit();
		return people;
	}

	public void addEmailAddress(Person person) {
		handler.sessionHandler(s -> s.saveOrUpdate(person));
	}

	public void addPersonToEvent(Person person, Event event) {
		handler.sessionHandler(s -> {
			person.addToEvent(event);
			session.saveOrUpdate(person);
		});
	}

	public void deleteAll() {
		handler.sessionHandler(s -> {
			List<Person> people = s.createQuery("from Person").list();
			if(people != null) {
				for (Person person : people) {
					person.removeEmailAddresses();
					s.saveOrUpdate(person);
				}
				s.createQuery("delete from Person").executeUpdate();
			}
		});
	}
}
