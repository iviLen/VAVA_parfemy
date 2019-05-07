package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/*
 * Trieda DetailParfemu, vdaka ktorej sa zobrazí obrazovka s detailom vybrateho parfemu.
 * Vzdy sa kontroluje jazyk a farba pozadia, ktoru si pouzivatel zvolil.
 */

public class DetailParfemu extends JFrame {

	private JPanel contentPane;

	public DetailParfemu(Controller control, JTable table) {
		
		int id = control.detail(table);
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		
		if(control.getFarba() == 1)
			contentPane.setBackground(new Color(204, 255, 204));
		else if(control.getFarba() == 2)
			contentPane.setBackground(new Color(250,226,232));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(70, 40, 407, 78);
		lblNewLabel.setFont(new Font("Gabriola", Font.PLAIN, 60));
		contentPane.add(lblNewLabel);
		
		//Tlacidlo, ktore prida vonavku do wishlistu
		JButton btnNewButton = new JButton();
		btnNewButton.setBounds(740, 158, 151, 42);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				int pridanie = control.pridajDoWishListu(id);	
				if(pridanie == 1)
				{
					if(control.getJazyk() == 1)
						JOptionPane.showMessageDialog(null, "Voňavka bola pridaná do Wishlistu.");
					else
						JOptionPane.showMessageDialog(null, "The perfume was added to Wishlist.");
				}
				else
				{
					if(control.getJazyk() == 1)
						JOptionPane.showMessageDialog(null, "Voňavku sa nepodarilo pridať do Wishlistu.");
					else
						JOptionPane.showMessageDialog(null, "Adding the perfume was unsuccessful.");
				}
			}
		});
		btnNewButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		//tlacidlo, ktore prida vonavku do mojich parfemov
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(740, 240, 151, 42);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				int pridanie = control.pridajDoMojich(id);	
				if(pridanie == 1)
				{
					if(control.getJazyk() == 1)
						JOptionPane.showMessageDialog(null, "Voňavka bola pridaná do Mojich Parfémov.");
					else
						JOptionPane.showMessageDialog(null, "The perfume was added to My Perfumes.");
				}
				else
				{
					if(control.getJazyk() == 1)
						JOptionPane.showMessageDialog(null, "Voňavku sa nepodarilo pridať do Mojich Parfémov.");
					else
						JOptionPane.showMessageDialog(null, "Adding the perfume was unsuccessful.");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Gabriola", Font.PLAIN, 18));
		contentPane.add(btnNewButton_1);
		
		//Tlacidlo, ktore vypise zoznam vsetkych parfemov
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBounds(740, 404, 151, 42);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ZoznamParfemov zoznam = new ZoznamParfemov(control);
				zoznam.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_2);
		
		//Tlacidlo, ktore vrati pouziatela na hlavne menu
		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setBounds(740, 486, 151, 42);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false); 
			    dispose(); 
			    Menu.main(null);	
			}
		});
		btnNewButton_3.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_3);
		
		JLabel obrazocek= new JLabel("");
		obrazocek.setBounds(384, 158, 290, 370);
		obrazocek.setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource("/perfume.png")).getImage();
		obrazocek.setIcon(new ImageIcon(img));
		contentPane.add(obrazocek);
		
		ArrayList<JLabel> labely = new ArrayList<JLabel>();
		String nazvyLabelov = "";
		for(int i = 0; i < 7; i++) 
		{
			JLabel label = new JLabel(nazvyLabelov);
			labely.add(label);
			labely.get(i).setFont(new Font("Gabriola", Font.PLAIN, 22));
		}
		
		labely.get(0).setBounds(192, 158, 202, 31);
		labely.get(1).setBounds(192, 201, 202, 31);
		labely.get(2).setBounds(192, 244, 202, 31);
		labely.get(3).setBounds(192, 287, 202, 31);
		labely.get(4).setBounds(192, 415, 202, 31);
		labely.get(5).setBounds(192, 459, 202, 31);
		labely.get(6).setBounds(192, 502, 61, 31);

		for(int i = 0; i < 7; i++) 
		{
			contentPane.add(labely.get(i));
		}
		
		ArrayList<JLabel> labely2 = new ArrayList<JLabel>();
		
		if(control.getJazyk() == 1) 
		{
			String nazvyLabelov2[] = {"Názov:", "Značka:", "Typ:", "Veľkosť:", "Notino:", "Fann:", "Marionnaud:"};
			for(int i = 0; i < nazvyLabelov2.length; i++) 
			{
				JLabel label = new JLabel(nazvyLabelov2[i]);
				labely2.add(label);
				labely2.get(i).setFont(new Font("Gabriola", Font.PLAIN, 22));
			}
		}
		else
		{
			String nazvyLabelov2[] = {"Title:", "Brand:", "Type:", "Size:", "Notino:", "Fann:", "Marionnaud:"};
			for(int i = 0; i < nazvyLabelov2.length; i++) 
			{
				JLabel label = new JLabel(nazvyLabelov2[i]);
				labely2.add(label);
				labely2.get(i).setFont(new Font("Gabriola", Font.PLAIN, 22));
			}
		}
		
		labely2.get(0).setBounds(70, 158, 87, 31);
		labely2.get(1).setBounds(70, 201, 61, 31);
		labely2.get(2).setBounds(70, 244, 61, 31);
		labely2.get(3).setBounds(70, 287, 61, 31);
		labely2.get(4).setBounds(70, 415, 61, 31);
		labely2.get(5).setBounds(70, 459, 61, 31);	
		labely2.get(6).setBounds(70, 502, 97, 31);

		for(int i = 0; i < 7; i++) 
		{
			contentPane.add(labely2.get(i));
		}
		
		//Tlacidlo, ktore vygeneruje pdf z detailu parfemu
		JButton btnNewButton_4 = new JButton();
		btnNewButton_4.setBounds(740, 322, 151, 42);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.vygenerujpdf(labely,labely2);
				
				if(control.getJazyk() == 1)
					JOptionPane.showMessageDialog(null, "Bolo vygenerované PDF.");
				else
					JOptionPane.showMessageDialog(null, "PDF was generated successfuly.");
			}
		});
		btnNewButton_4.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_4);
		
		control.spravto(id,labely);
		
		if(control.getJazyk() == 1) 
		{
			lblNewLabel.setText("Detail parfému");
			btnNewButton.setText("Pridať do wishlistu");
			btnNewButton_1.setText("Pridať do mojich");
			btnNewButton_2.setText("Zoznam parfémov");
			btnNewButton_3.setText("Hlavné menu");
			btnNewButton_4.setText("Vygenerovať PDF");
		}
		else 
		{
			lblNewLabel.setText("Detail of perfume");
			btnNewButton.setText("Add to wishlist");
			btnNewButton_1.setText("Add to my perfumes");
			btnNewButton_2.setText("All perfumes");
			btnNewButton_3.setText("Menu");
			btnNewButton_4.setText("Generate PDF");
		}
	}
}
