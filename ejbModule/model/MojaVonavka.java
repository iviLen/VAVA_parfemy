package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Trieda MojaVonavka reprezentuje tabulku moje_vonavky z databazy
 */

@Entity
@Table(name="moje_vonavky")
public class MojaVonavka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="vonavka_id")
	private int vonavka_id;

	public MojaVonavka() {}
	
	public MojaVonavka(int id, int vonavka_id) {
		this.id = id;
		this.vonavka_id = vonavka_id;
	}
	
	public MojaVonavka(int vonavka_id) {
		super();
		this.vonavka_id = vonavka_id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setVonavka_id(int vonavka_id) {
		this.vonavka_id = id;
	}

	public int getVonavka_id() {
		return vonavka_id;
	}
}
