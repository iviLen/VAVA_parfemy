package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.MsgServer.ExampleServer;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.MojWishList;
import model.MojaVonavka;
import model.Vonavka;
import model.VonavkaObchodu;
import model.Znacka;
import java.awt.EventQueue;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import view.Menu;

/*
 * Trieda Controller, ktora riadi beh aplikacie
 */

public class Controller {
	
	private static Controller controller = new Controller();
	private int farba = 2;
	private int jazyk = 1;
	
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	private static final String PATH = "log.txt";
	
	public static ExampleServer bean = new ExampleServer();
	
	public static Controller getInstance() {
		return controller;
	}
	
	public void setFarba(int farba) {
		this.farba = farba;
	}
	
	public int getFarba() {
		return farba;
	}
	
	public void setJazyk(int jazyk) {
		this.jazyk = jazyk;
	}
	
	public int getJazyk() {
		return jazyk;
	}
	
	public static void main(String[] args) {

		try {
			FileHandler handler = new FileHandler(PATH);
			InputStream configFile = Controller.class.getResourceAsStream("logging.properties");
			LogManager.getLogManager().readConfiguration(configFile);
			handler.setFormatter(new SimpleFormatter());
			
			LOGGER.addHandler(handler);
			LOGGER.setUseParentHandlers(false);
			LOGGER.setLevel(Level.INFO);
			
		} catch (SecurityException e) {
			System.out.println("Konfiguracia zlyhala.");
		} catch (IOException e) {
			System.out.println("Zla cesta k suboru properties - logovanie.");
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
			
	//Metoda, ktora vypise zoznam mojich parfemov do tabulky
	public void vypisMojeParfemy(JTable table)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<MojaVonavka> mojeVonavky = Controller.bean.vypisMojeParfemy();
		List<Vonavka> vonavky = Controller.bean.vypisZoznamParfemov();
		List<Znacka> znacky = Controller.bean.vypisZoznamParfemov_Znacky();
		Object rowData[] = new Object[4];
		
		for(int i = 0; i < mojeVonavky.size(); i++) 
		{
			rowData[0] = mojeVonavky.get(i).getID();
			
			for(int ij = 0; ij < vonavky.size(); ij++) 
			{
				if(mojeVonavky.get(i).getVonavka_id() == vonavky.get(ij).getID()) 
				{
					rowData[1] = vonavky.get(ij).getNazov();
					rowData[2] = vonavky.get(ij).getVelkost();
					
					for(int ijk = 0; ijk < znacky.size(); ijk++) 
					{
						if(vonavky.get(ij).getZnacka() == znacky.get(ijk).getId()) 
						{
							rowData[3] = znacky.get(ijk).getNazov();
							break;
						}
					}
					break;
				}
			}
			model.addRow(rowData);
		}
	}

	//Metoda, ktora vypise vonavky vo wishliste pouzivatela
	public void vypisMojWishlist(JTable table)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<MojWishList> mojWishList = Controller.bean.vypisMojWishList();
		List<Vonavka> vonavky = Controller.bean.vypisZoznamParfemov();
		List<Znacka> znacky = Controller.bean.vypisZoznamParfemov_Znacky();
		Object rowData[] = new Object[4];
		
		for(int i = 0; i < mojWishList.size(); i++)
		{
			rowData[0] = mojWishList.get(i).getID();
			
			for(int ij = 0; ij < vonavky.size(); ij++)
			{
				if(mojWishList.get(i).getVonavka_id() == vonavky.get(ij).getID()) 
				{
					rowData[1] = vonavky.get(ij).getNazov();
					rowData[2] = vonavky.get(ij).getVelkost();
					
					for(int ijk = 0; ijk < znacky.size(); ijk++)
					{
						if(vonavky.get(ij).getZnacka() == znacky.get(ijk).getId()) 
						{
							rowData[3] = znacky.get(ijk).getNazov();
							break;
						}
					}
					break;
				}
			}
			model.addRow(rowData);
		}
	}
	
	//Metoda, ktora vypise do tabulky zoznam vsetkych vonaviek v databaze
	public void vypisZoznamParfemov(JTable table)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Vonavka> vonavky = Controller.bean.vypisZoznamParfemov();
		List<Znacka> znacky = Controller.bean.vypisZoznamParfemov_Znacky();
		Object rowData[] = new Object[4];
		
		for(int i = 0; i < vonavky.size(); i++) 
		{
			rowData[0] = vonavky.get(i).getID();
			rowData[1] = vonavky.get(i).getNazov();
			rowData[2] = vonavky.get(i).getVelkost();
			for(int j = 0; j < znacky.size(); j++) 
			{
				if(vonavky.get(i).getZnacka() == znacky.get(j).getId())
					rowData[3] = znacky.get(j).getNazov();
			}
			model.addRow(rowData);
		}
	}

	//Metoda, ktora zabezpecuje pridanie zvolenej vonavky medzi moje parfemy
	public int pridajDoMojich(int id) 
	{
		Controller.bean.pridajDoMojich(id);
		return 1;		
	}

	//Metoda, ktora zisti id vonavky z tabulky
	public int zistiIDVonavky(JTable table) 
	{
		int row = table.getSelectedRow();
		
		if(row == -1)
			return -1;
		
		int value = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		return Controller.bean.zistiIDVonavky(value);	
	}

	//Metoda, ktora odstrani parfem z wishlistu
	public int odstranZWishListu(int value) 
	{
		return Controller.bean.odstranZWishListu(value);
	}

	//Metoda, ktora odstrani parfem z mojich parfemov
	public int odstranZMojichParfemov(int value) 
	{
		return Controller.bean.odstranZMojichParfemov(value);
	}

	//Metoda, ktora filtruje parfemy podla poziadaviek pouzivatela
	public void vyfiltruj(JTable table,JTextField textField, ArrayList<JCheckBox> velkost, ArrayList<JCheckBox> checkboxy3) 
	{
		int nazovZvoleny = 1, pom = 0;
		int velkosti[] = new int[4];
		velkosti[0] = 30;
		velkosti[1] = 50;
		velkosti[2] = 100;
		velkosti[3] = 200;
		
		if(textField.getText().equals(""))
			nazovZvoleny = 0;
		for(JCheckBox x : velkost) 
		{
			if(!x.isSelected())
			{
				velkosti[pom] = 0;
			}
			pom++;
		}
		
		List<Vonavka> vonavky = Controller.bean.vypisZoznamParfemov();
		List<Znacka> znacky = Controller.bean.vypisZoznamParfemov_Znacky();
		ArrayList<Vonavka> zoznam = new ArrayList<Vonavka>();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		if(nazovZvoleny == 1) {
			for(Vonavka x : vonavky)
			{
				pom = 0;
				if(x.getNazov().equals(textField.getText()))
				{
					zoznam.add(x);
					pom = 1;
				}
				if(pom == 0) 
				{
					for(int i = 0; i < 4; i++) 
					{
						if(pom == 0 && x.getVelkost() == velkosti[i]) 
						{
							zoznam.add(x);
							pom = 1;
						}
					}
					if(pom == 0) 
					{
						for(Znacka y : znacky) 
						{
							for(JCheckBox check : checkboxy3) 
							{
								if(check.isSelected() && check.getText().equals(y.getNazov())) 
								{
									if(x.getZnacka() == y.getId())
										zoznam.add(x);
								}
							}
						}
					}
				}
			}
		} 
		else 
		{
			for(Vonavka x : vonavky) 
			{
				pom = 0;
				for(int i = 0; i < 4; i++) 
				{
					if(pom == 0 && x.getVelkost() == velkosti[i]) 
					{
						zoznam.add(x);
						pom = 1;
					}
				}
				if(pom == 0) 
				{
					for(Znacka y : znacky) 
					{
						for(JCheckBox check : checkboxy3) 
						{
							if(check.isSelected() && check.getText().equals(y.getNazov())) 
							{
								if(x.getZnacka() == y.getId())
									zoznam.add(x);
							}
						}
					}
				}
			}
		}
		
		Object rowData[] = new Object[4];
		for(Vonavka x : zoznam) 
		{
			rowData[0] = x.getID();
			rowData[1] = x.getNazov();
			rowData[2] = x.getVelkost();
			for(Znacka z : znacky) 
			{
				if(x.getZnacka() == z.getId()) 
				{
					rowData[3] = z.getNazov();
					model.addRow(rowData);
					break;
				}
			}
		}
	}

	//Metoda, ktora zisti id vonavky, ktora sa nasledne vyobrazi v detaile parfemu
	public int detail(JTable table) 
	{
		int column = 0;
		int row = table.getSelectedRow();
		String [] value = new String[1];
		value[0] = table.getModel().getValueAt(row, column).toString();
	    int hodnota = Integer.parseInt(value[0]);
		
		return hodnota;
	}

	//Metoda, ktora vypise detailne informacie o vonavke, ktore si zisti z databazy
	public void spravto(int id, ArrayList<JLabel> labely)
	{
		Vonavka vonavka = Controller.bean.vratVonavku(id);
		List<VonavkaObchodu> ceny = Controller.bean.vratVonavkyObchodu(id);
		Znacka znacka = Controller.bean.vratZnackuVonavky(vonavka.getZnacka());
		
		labely.get(0).setText(vonavka.getNazov());
		labely.get(1).setText(znacka.getNazov());
		labely.get(2).setText(vonavka.getTyp());
		labely.get(3).setText(Integer.toString(vonavka.getVelkost())+" ml");
		labely.get(4).setText(String.format("%.02f",ceny.get(0).getCena())+ " €");
		labely.get(5).setText(String.format("%.02f",ceny.get(1).getCena())+ " €");
		labely.get(6).setText(String.format("%.02f",ceny.get(2).getCena())+ " €");	
	}

	//Metoda, ktora prida vonavky do wishlistu
	public int pridajDoWishListu(int id) 
	{
		return Controller.bean.pridajDoWishListu(id);
	}

	//Metoda, ktora zisti id vonavky v mojich parfemoch
	public int zistiIDVonavkyVMojichParfemoch(JTable table) 
	{
		int row = table.getSelectedRow();
		
		if(row == -1)
			return -1;
		
		int value = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
		return Controller.bean.zistiIDVonavkyVMojichParfemoch(value);
	}

	//Metoda, ktora genetuje pdf z detailu parfemu s detailnymi informaciami o parfeme
	public void vygenerujpdf(ArrayList<JLabel> labely, ArrayList<JLabel> labely2) 
	{
		LOGGER.entering(this.getClass().getName(), "vygenetuj PDF");
		String file = "C:\\Users\\User\\Desktop\\MsgFromServerEJB\\detail.pdf";
		Document dokument = new Document();
		
		try {
			PdfWriter.getInstance(dokument, new FileOutputStream(new File(file)));
			dokument.open();
			Image obrazok = Image.getInstance("C:\\Users\\User\\Desktop\\MsgFromServerEJB\\images\\perfume.png");
			Image notino = Image.getInstance("C:\\Users\\User\\Desktop\\MsgFromServerEJB\\images\\notino.png");
			Image fann = Image.getInstance("C:\\Users\\User\\Desktop\\MsgFromServerEJB\\images\\fann.jpg");
			Image mari = Image.getInstance("C:\\Users\\User\\Desktop\\MsgFromServerEJB\\images\\mari.png");
			obrazok.setAlignment(Element.ALIGN_RIGHT);
			
			Font nadpis = new Font();
			nadpis.setStyle(Font.BOLD);
			nadpis.setSize(25);
			if(this.getJazyk() == 1)
				dokument.add(new Paragraph("Detail parfému",nadpis));
			else
				dokument.add(new Paragraph("Detail of perfume",nadpis));
			dokument.add( Chunk.NEWLINE );
			
			BaseFont baseFont = BaseFont.createFont("c:\\WINDOWS\\fonts\\calibri.ttf", BaseFont.IDENTITY_H, true); 
		       Font font  = new Font(baseFont); 
		       font.setStyle(Font.BOLD);
				font.setSize(15);
			
			Font text = new Font();
			text.setStyle(Font.BOLD);
			text.setSize(20);
			dokument.add(new Paragraph(labely2.get(0).getText() +" "+ labely.get(0).getText() ,font));
			dokument.add(new Paragraph(labely2.get(1).getText() +" "+ labely.get(1).getText() ,font));
			dokument.add(new Paragraph(labely2.get(2).getText() +" "+ labely.get(2).getText() ,font));
			dokument.add(new Paragraph(labely2.get(3).getText() +" "+ labely.get(3).getText() ,font));
			dokument.add( Chunk.NEWLINE );
			
			if(this.getJazyk() == 1)
				dokument.add(new Paragraph("Ceny v jednotlivých obchodoch:",font));
			else
				dokument.add(new Paragraph("The prices in stores:",font));
			
			dokument.add(notino);
			dokument.add(new Paragraph(labely.get(4).getText() ,font));
			dokument.add( Chunk.NEWLINE );
			dokument.add(fann);
			dokument.add(new Paragraph(labely.get(5).getText() ,font));
			dokument.add( Chunk.NEWLINE );
			dokument.add(mari);
			dokument.add(new Paragraph(labely.get(6).getText() ,font));
			
			dokument.close();
			
		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Nepodarilo sa najst cestu k dokumentu, v ktorom sa ma vygenerovat pdf.");
			e.printStackTrace();
		}
		
		LOGGER.log(Level.INFO, "Bolo vygenerovane PDF.");
		LOGGER.exiting(this.getClass().getName(), "vygenetuj PDF");
		
	}

	//Metoda, ktora vytlaci tabulku
	public void vytlac(JTable table) 
	{
		LOGGER.entering(this.getClass().getName(), "vytlac tabulku");
		try {
			table.print(JTable.PrintMode.NORMAL);
		} catch (PrinterException e) {
			LOGGER.log(Level.SEVERE, "Nepodarilo sa vytlacit tabulku.");
			e.printStackTrace();
		}	
		LOGGER.log(Level.INFO, "Bola vytlacena tabulka.");
		LOGGER.exiting(this.getClass().getName(), "vytlac tabulku");
	}
}
