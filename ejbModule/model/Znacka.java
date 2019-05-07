package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Trieda Znacka reprezentuje tabulku znacka z databazy
 */

@Entity
@Table(name="znacka")
public class Znacka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nazov")
	private String nazov;
	
	public Znacka() {}
	
	public Znacka(int id, String nazov) {
		this.id = id;
		this.nazov = nazov;
	}
	
	public Znacka(String nazov) {
		super();
		this.nazov = nazov;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazov() {
		return nazov;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
}
