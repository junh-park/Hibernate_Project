package com.jun.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jun.hibernate.dao.EventDao;
import com.jun.hibernate.dao.PersonDao;
import com.jun.hibernate.domain.Event;

public class EventDaoTest {
	private Timestamp time;
	private EventDao eventDao = new EventDao();

	@BeforeMethod
	public void setUp() {
		LocalDateTime now = LocalDateTime.now();
		time = Timestamp.valueOf(now);
		Event event = new Event("New Event", time);

		eventDao.add(event);
	}

	@Test
	public void storeAndLoadEventTest() throws ParseException {
		List<Event> events = eventDao.listEvents();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (Event event : events) {
			String formattedDate = sdf.format(event.getDate());
			System.out.println("Event: " + event.getTitle() + " Time: " + event.getDate());

			assertThat(event.getTitle(), is("New Event"));
			assertThat(formattedDate, is(sdf.format(time)));
		}
	}
}
