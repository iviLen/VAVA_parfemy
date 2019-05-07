package com.MsgServer;

import java.util.List;
import javax.ejb.Remote;
import model.MojWishList;
import model.MojaVonavka;
import model.Vonavka;
import model.VonavkaObchodu;
import model.Znacka;

@Remote
public interface ExampleServerRemote {
	public List<Vonavka> vypisZoznamParfemov();
	public List<Znacka> vypisZoznamParfemov_Znacky();
	public List<MojaVonavka> vypisMojeParfemy();
	public List<MojWishList> vypisMojWishList();
	public void pridajDoMojich(int id);
	public int zistiIDVonavky(int id_WhisListu);
	public int odstranZWishListu(int id_WishListu);
	public int odstranZMojichParfemov(int id_WishListu);
	public int pridajDoWishListu(int id);
	public int zistiIDVonavkyVMojichParfemoch(int id_MojejVonavky);
	public Vonavka vratVonavku(int id);
	public List<VonavkaObchodu> vratVonavkyObchodu(int id_vonavky);
	public Znacka vratZnackuVonavky(int id_znacky);
}
