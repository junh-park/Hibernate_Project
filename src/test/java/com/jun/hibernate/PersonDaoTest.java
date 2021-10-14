package com.jun.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jun.hibernate.dao.PersonDao;
import com.jun.hibernate.domain.Person;

public class PersonDaoTest {
	private PersonDao personDao = new PersonDao();
	private Person person1;
	private Person person2;

	@BeforeMethod
	public void setUp() {
		person1 = new Person(30, "Jun", "Park");
		person2 = new Person(25, "Dodo", "Xu");
		
		personDao.deleteAll();
	}

	@Test
	public void storeAndLoadEventTest() {

		personDao.add(person1);
		personDao.add(person2);
		List<Person> people = personDao.getAll();

		assertThat(people, hasItems(person1, person2));
	}

	@Test
	public void getPersonTest() {

		personDao.add(person1);

		Person personSaved = personDao.get(person1.getId());

		comparePerson(personSaved, person1);
	}

	@Test
	public void deleteAllTest() {
		personDao.add(person1);

		personDao.deleteAll();

		List<Person> people = personDao.getAll();

		assertThat(people.size(), is(0));
	}

	@Test
	public void addEmailAddressTest() {

		person1.getEmailAddresses().add("jhpar25@hotmail.com");
		personDao.addEmailAddress(person1);
		
		Person personRetrieved = personDao.get(person1.getId());
		
		comparePerson(personRetrieved, person1);
	}
	

	private void comparePerson(Person person1, Person person2) {
		System.out.println(person1.toString());
		assertThat(person1.getAge(), is(person2.getAge()));
		assertThat(person1.getFirstName(), is(person2.getFirstName()));
		assertThat(person1.getLastName(), is(person2.getLastName()));
		assertThat(person1.getEmailAddresses(), is(person2.getEmailAddresses()));
	}

}
