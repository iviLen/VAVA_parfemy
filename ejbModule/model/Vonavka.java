package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Trieda Vonavka reprezentuje tabulku vonavky z databazy
 */

@Entity
@Table(name="vonavky")
public class Vonavka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nazov")
	private String nazov;
	
	@Column(name="velkost")
	private int velkost;
	
	@Column(name="typ")
	private String typ;
	
	@Column(name="znacka_id")
	private int znacka;
	
	public Vonavka() {}
	
	public Vonavka(int id, String nazov, int velkost, int znacka) {
		this.id = id;
		this.nazov = nazov;
		this.velkost = velkost;
		this.znacka = znacka;
	}
	
	public Vonavka(String nazov, int velkost, String typ, int znacka) {
		super();
		this.nazov = nazov;
		this.velkost = velkost;
		this.typ = typ;
		this.znacka = znacka;
	}

	public int getID(){
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getNazov() {
		return nazov;
	}
	
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
	
	public int getVelkost() {
		return velkost;
	}
	
	public void setVelkost(int velkost) {
		this.velkost = velkost;
	}
	
	public int getZnacka() {
		return znacka;
	}
	
	public void setZnacka(int znacka) {
		this.znacka = znacka;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
}
