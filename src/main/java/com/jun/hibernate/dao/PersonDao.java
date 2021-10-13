package com.jun.hibernate.dao;

import java.util.List;

import org.hibernate.Session;

import com.jun.hibernate.domain.Event;
import com.jun.hibernate.domain.Person;
import com.jun.hibernate.util.HibernateUtil;

public class PersonDao {
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	
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


	public void addPersonToEvent(Person person, Event event) {
		session.beginTransaction();

		person.getEvents().add(event);
		session.update(person);

		session.getTransaction().commit();
	}
}
