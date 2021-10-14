package com.jun.hibernate.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Event {
	private Long id;
	private String title;
	private Date date;
	private Set<Person> participants = new HashSet<Person>();

	public Event() {
	}

	public Event(String title, Timestamp date) {
		this.title = title;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Person> participants) {
		this.participants = participants;
	}

	public String toString() {
		return new StringBuilder("Event :").append(getTitle()).append(" Date: ").append(getDate()).toString();
	}

	public boolean equals(Object obj) {
		if (!(this instanceof Event))
			return false;
		if (this == null)
			return false;
		if (this == obj)
			return true;
		Event other = (Event) obj;
		return (this.title == other.getTitle()) && (this.id == other.getId());
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode())
				+ ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

}
