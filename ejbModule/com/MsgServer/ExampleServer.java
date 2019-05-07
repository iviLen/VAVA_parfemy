package com.MsgServer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import controller.Controller;
import model.MojWishList;
import model.MojaVonavka;
import model.Vonavka;
import model.VonavkaObchodu;
import model.Znacka;

/*
 * Trieda ExampleServer, ktora vykonava spojenie aplikacie s databazou.
 * Su v nej vsetky dolezite metody, ktore tahaju informacie z databazy.
 */

@Stateless
@LocalBean
public class ExampleServer implements ExampleServerRemote, ExampleServerLocal {
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
    public ExampleServer() {
    	
    }
     
    //Metoda, ktora vytiahne z databazy udaje o vonavkach a ulozi ich do listu vonaviek
    @Override
    public List<Vonavka> vypisZoznamParfemov() {
    	LOGGER.entering(this.getClass().getName(), "vypis zoznam parfemov");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();

    	try {
    		session.beginTransaction();
    		List<Vonavka> vonavky = session.createQuery("from Vonavka").getResultList();
    		session.getTransaction().commit();
    		return vonavky;
    	} 
    	catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut informacie z databazy o vonavkach.");
    		return null;
    	}
    	finally {
    		LOGGER.log(Level.INFO, "Vytiahli sa informacie o vonavke z databazy.");
    		LOGGER.exiting(this.getClass().getName(), "vypis zoznam parfemov");
    		factory.close();
    	}
    }
    
    //Metoda, ktora vytiahne z databazy udaje o Znackach 
    @Override
    public List<Znacka> vypisZoznamParfemov_Znacky() {
    	LOGGER.entering(this.getClass().getName(), "vypis zoznam parfemov_znacky");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
    	try {
    		session.beginTransaction();
    		List<Znacka> znacky = session.createQuery("from Znacka").getResultList();
    		session.getTransaction().commit();
    		return znacky;
    	} 
    	catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut info o znackach.");
    		return null;
    	}
    	finally {
    		LOGGER.log(Level.INFO, "Vytiahli sa informacie o znackach z databazy.");
    		LOGGER.exiting(this.getClass().getName(), "vypis zoznam parfemov_znacky");
    		factory.close();
    	}
    }
    
    //Metoda, ktora dostane udaje z databazy z tabulky moje parfemy
    @Override
    public List<MojaVonavka> vypisMojeParfemy() {
    	LOGGER.entering(this.getClass().getName(), "vypis moje parfemy");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
    	try {
    		session.beginTransaction();
    		List<MojaVonavka> mojeVonavky = session.createQuery("from MojaVonavka").getResultList();
    		session.getTransaction().commit();
    		return mojeVonavky;
    	} 
    	catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut info o mojich vonavkach.");
    		return null;
    	}
    	finally {
    		LOGGER.log(Level.INFO, "Vytiahli sa informacie o mojich vonavkach z databazy.");
    		LOGGER.exiting(this.getClass().getName(), "vypis moje parfemy");
    		factory.close();
    	}
    }
    
    //Metoda, ktora vytiahne informacie z databazy z tabulky moj wishlist
    @Override
    public List<MojWishList> vypisMojWishList() {
    	LOGGER.entering(this.getClass().getName(), "vypis moj wishlist");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
    	try {
    		session.beginTransaction();
    		List<MojWishList> mojWishList = session.createQuery("from MojWishList").getResultList();
    		session.getTransaction().commit();
    		return mojWishList;
    	} 
    	catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut info z mojho wishlistu z databazy.");
    		return null;
    	}
    	finally {
    		LOGGER.log(Level.INFO, "Vytiahli sa informacie z mojho wishlistu z databazy.");
    		LOGGER.exiting(this.getClass().getName(), "vypis moj wishlist");
    		factory.close();
    	}
    }
    
    //Metoda ktora insertne vonavku do tabulky moje vonavky v databaze
    @Override
    public void pridajDoMojich(int id) {
    	LOGGER.entering(this.getClass().getName(), "pridaj vonavku do mojich vonaviek");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			MojaVonavka vonavka = new MojaVonavka(id);
			session.save(vonavka);
			session.getTransaction().commit();
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Vonavku sa nepodarilo pridat do tabulky moje vonavky v databaze.");
    	}
		finally {
			LOGGER.log(Level.INFO, "Vonavka sa pridala do mojich vonaviek v databaze.");
			LOGGER.exiting(this.getClass().getName(), "pridaj vonavku do mojich vonaviek");
			factory.close();
		}
    }

    //Metoda, ktora zisti id vonavky, konkretnej vonavky z wishlistu
    @Override
    public int zistiIDVonavky(int id_WhisListu) {
    	LOGGER.entering(this.getClass().getName(), "zisti id vonavky z wish listu");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			MojWishList moj = session.get(MojWishList.class, id_WhisListu);
			session.getTransaction().commit();
			return moj.getVonavka_id();
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "ID vonavky sa nepodarilo zistit z tabulky moj wishlist v databaze.");
    		return 0;
    	}
		finally {
			LOGGER.log(Level.INFO, "ID vonavky bolo zistene.");
			LOGGER.exiting(this.getClass().getName(), "zisti id vonavky z wish listu");
			factory.close();
		}
    }
    
    //Metoda, ktora odstrani konkretnu vonavku z tabulky moj wishlist v databaze
    @Override
    public int odstranZWishListu(int id_WishListu) {
    	LOGGER.entering(this.getClass().getName(), "odstran vonavku z wishlistu");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			MojWishList moj = session.get(MojWishList.class, id_WishListu);
			session.delete(moj);
			session.getTransaction().commit();
			return 1;
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Vonavku sa nepodarilo odstranit z tabulky moj wishlist z databazy.");
    		return 0;
    	}
		finally {
			LOGGER.log(Level.INFO, "Vonavka bola odstranena z tabulky moj wishlist z databazy.");
			LOGGER.exiting(this.getClass().getName(), "odstran vonavku z wishlistu");
			factory.close();
		}
    }
    
    //Metoda, ktora odstrani vonavku z tabulky moje parfemy
    @Override
    public int odstranZMojichParfemov(int id_WishListu) {
    	LOGGER.entering(this.getClass().getName(), "odstran vonavku z mojich vonaviek");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			MojaVonavka moja = session.get(MojaVonavka.class, id_WishListu);
			session.delete(moja);
			session.getTransaction().commit();
			return 1;
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Vonavku sa nepodarilo odstranit z tabulky moje parfemy z databazy.");
    		return 0;
    	}
		finally {
			LOGGER.log(Level.INFO, "Vonavka bola odstranena z tabulky moje parfemy z databazy.");
			LOGGER.exiting(this.getClass().getName(), "odstran vonavku z mojich vonaviek");
			factory.close();
		}
    }
    
    //Metoda, ktora prida vonavku do wishlistu
    @Override
    public int pridajDoWishListu(int id) {
    	LOGGER.entering(this.getClass().getName(), "pridaj vonavku do mojho wishlistu");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			MojWishList moj = new MojWishList(id);
			session.save(moj);
			session.getTransaction().commit();
			return 1;
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Vonavku sa nepodarilo pridat do tabulky moj wishlist v databaze.");
    		return 0;
    	}
		finally {
			LOGGER.log(Level.INFO, "Vonavka bola pridana do tabulky moj wishlist v databaze.");
			LOGGER.exiting(this.getClass().getName(), "pridaj vonavku do mojho wishlistu");
			factory.close();
		}
    }
    
    //Metoda, ktora zisti id konkretnej vonavky z tabulky moje vonavky v databaze
    @Override
    public int zistiIDVonavkyVMojichParfemoch(int id_MojejVonavky) {
    	LOGGER.entering(this.getClass().getName(), "zisti id vonavky v mojich parfemoch");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			MojaVonavka moja = session.get(MojaVonavka.class, id_MojejVonavky);
			session.getTransaction().commit();
			return moja.getVonavka_id();
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa zistit id vonavky z tabulky moje parfemy.");
    		return 0;
    	}
		finally {
			LOGGER.log(Level.INFO, "Podarilo sa zistit id vonavky z tabulky moje parfemy.");
			LOGGER.exiting(this.getClass().getName(), "zisti id vonavky v mojich parfemoch");
			factory.close();
		}
    }
    
    //Metoda, ktora vytiahne informacie o vonavke, ktoru chceme zobrazit na obrazovku v detaile z databazy 
    @Override
    public Vonavka vratVonavku(int id) {
    	LOGGER.entering(this.getClass().getName(), "vrat vonavku");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			Vonavka vonavka = session.get(Vonavka.class, id);
			session.getTransaction().commit();
			return vonavka;
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut info o vonavke z tabulky vonavky.");
    		return null;
    	}
		finally {
			LOGGER.log(Level.INFO, "Podarilo sa vytiahnut info o vonavke z tabulky vonavky.");
			LOGGER.exiting(this.getClass().getName(), "vrat vonavku");
			factory.close();
		}
    }
    
    @Override
    public List<VonavkaObchodu> vratVonavkyObchodu(int id_vonavky) {
    	LOGGER.entering(this.getClass().getName(), "vytiahni info o cene v jednotlivych obchodoch danej vonavky");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			List<VonavkaObchodu> list = session.createQuery("from VonavkaObchodu v WHERE v.vonavka_id = "+id_vonavky).getResultList();
			session.getTransaction().commit();
			return list;
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut info o cene danej vonavky z tabulky vonavka obchodu.");
    		return null;
    	}
		finally {
			LOGGER.log(Level.INFO, "Podarilo sa vytiahnut info o cene danej vonavky z tabulky vonavka obchodu.");
			LOGGER.exiting(this.getClass().getName(), "vytiahni info o cene v jednotlivych obchodoch danej vonavky");
			factory.close();
		}
    }
    
    @Override
    public Znacka vratZnackuVonavky(int id_znacky) {
    	LOGGER.entering(this.getClass().getName(), "vytiahni info o znacke danej vonavky z databazy");
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			Znacka znacka = session.get(Znacka.class, id_znacky);
			session.getTransaction().commit();
			return znacka;
		} 
		catch(Exception e){
    		LOGGER.log(Level.SEVERE, "Nepodarilo sa vytiahnut znacku danej vonavky.");
    		return null;
    	}
		finally {
			LOGGER.log(Level.INFO, "Podarilo sa vytiahnut znacku danej vonavky.");
			LOGGER.exiting(this.getClass().getName(), "vytiahni info o znacke danej vonavky z databazy");
			factory.close();
		}
    }
}