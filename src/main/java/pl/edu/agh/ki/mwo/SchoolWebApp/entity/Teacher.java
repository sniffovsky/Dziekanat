package pl.edu.agh.ki.mwo.SchoolWebApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher {
	// PROPERTIES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String name;
	@Column
	private String surname;
	@Column
	private String title;
	@Column
	private String pesel;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, mappedBy = "schoolClass")
	private Set<SchoolClass> charges;

	// CONSTRUCTORS
	public Teacher(String name, String surname, String title, String pesel) {
		this();
		this.name = name;
		this.surname = surname;
		this.title = title;
		this.pesel = pesel;
	}

	public Teacher() {
		super();
	}
	// GETTERS & SETTERS

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Set<SchoolClass> getCharges() {
		return charges;
	}

	public void setCharges(Set<SchoolClass> lectures) {
		if (charges == null) {
			charges = new HashSet<SchoolClass>();
		}
		this.charges = lectures;
	}

	public void addCharges(SchoolClass schoolClass) {
		if (charges == null) {
			charges = new HashSet<SchoolClass>();
		}
		charges.add(schoolClass);
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", surname=" + surname + ", title=" + title + ", pesel=" + pesel
				+ ", charges=" + charges + "]";
	}

	// HELPERS

}
