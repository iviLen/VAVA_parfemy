package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Trieda VonavkaObchodu reprezentuje tabulku vonavka_obchodu z databazy
 */

@Entity
@Table(name="vonavka_obchodu")
public class VonavkaObchodu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cena")
	private float cena;
	
	@Column(name="vonavka_id")
	private int vonavka_id;
	
	@Column(name="obchod_id")
	private int obchod_id;

	public VonavkaObchodu() {}
	
	public VonavkaObchodu(int id, float cena, int vonavka_id, int obchod_id) {
		this.id = id;
		this.cena = cena;
		this.vonavka_id = vonavka_id;
		this.obchod_id = obchod_id;
	}
	
	public VonavkaObchodu(float cena, int vonavka_id, int obchod_id) {
		super();
		this.cena = cena;
		this.vonavka_id = vonavka_id;
		this.obchod_id = obchod_id;
	}
	
	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public int getVonavka_id() {
		return vonavka_id;
	}

	public void setVonavka_id(int vonavka_id) {
		this.vonavka_id = vonavka_id;
	}

	public int getObchod_id() {
		return obchod_id;
	}

	public void setObchod_id(int obchod_id) {
		this.obchod_id = obchod_id;
	}	
}
