package com.jun.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jun.hibernate.dao.EventDao;
import com.jun.hibernate.dao.PersonDao;
import com.jun.hibernate.domain.Event;
import com.jun.hibernate.domain.Person;

public class EventPersonTest {
	private EventDao eventDao = new EventDao();
	private PersonDao personDao = new PersonDao();
	private Event event;
	private Event event2;
	private Person person;

	@BeforeMethod
	public void setUp() {
		LocalDateTime now = LocalDateTime.now();
		Timestamp time = Timestamp.valueOf(now);
		event = new Event("New Event", time);
		event2 = new Event("My Event", time);

		person = new Person(30, "Jun", "Park");
	}
	
	@Test
	public void addPersonToMultipleEventTest() {
		Long eventId = eventDao.add(event);
		Long eventId2 = eventDao.add(event2);
		Long personId = personDao.add(person);
		
		personDao.addPersonToEvent(person, event);
		personDao.addPersonToEvent(person, event2);
		Person retrievedPerson = personDao.get(personId);
		Set<Event> events = retrievedPerson.getEvents();
		
		for (Event event : events) {
			System.out.println("Event : " + event.getTitle() + " Event ID: " + event.getId() + " Date: " + event.getDate());
		} 
		
		assertThat(retrievedPerson.getId(), is(personId));
		assertThat(events.size(), is(2));
		assertThat(events.contains(event), is(true));
		assertThat(events.contains(event2), is(true));
	}

}
