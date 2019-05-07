package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controller.Controller;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Trieda ZoznamParfemov, vdaka ktorej sa zobrazi obrazovka so zoznamom vsetkych vonaviek.
 * Vzdy sa kontroluje jazyk a farba pozadia, ktoru si pouzivatel zvolil a podla toho sa zobrazi.
 */

public class ZoznamParfemov extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	public ZoznamParfemov(Controller control) {
		
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
		lblNewLabel.setBounds(70, 36, 407, 78);
		lblNewLabel.setFont(new Font("Gabriola", Font.PLAIN, 60));
		contentPane.add(lblNewLabel);
		
	    ArrayList<JCheckBox> velkost = new ArrayList<JCheckBox>();
	    String vel[] = {"30 ml","50 ml", "100 ml", "150 ml"};
	    for (int i = 0; i < vel.length; i++) 
	    {
	    	JCheckBox checkbox = new JCheckBox(vel[i]);
	    	if(control.getFarba() == 1)
				checkbox.setBackground(new Color(204, 255, 204));
			else if(control.getFarba() == 2)
				checkbox.setBackground(new Color(250,226,232));
	        velkost.add(checkbox); 
	    }
	    
	    velkost.get(0).setBounds(171, 220, 75, 23);
	    velkost.get(1).setBounds(280, 220, 75, 23);
	    velkost.get(2).setBounds(171, 258, 88, 23);
	    velkost.get(3).setBounds(280, 258, 89, 23);
	    
	    for(int i = 0; i < vel.length; i++) 
	    {
	    	velkost.get(i).setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	    	contentPane.add(velkost.get(i));
	    }
	    
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(167, 316, 199, 99);
		contentPane.add(scrollPane_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1,BoxLayout.Y_AXIS));
	    ArrayList<JCheckBox> checkboxy3 = new ArrayList<JCheckBox>();
	    String znacky[] = {"Armani","Calvin Klein", "DKNY", "Gucci", "Hugo Boss","Chloé","Lacoste","Lanvin","Montblanc",
	    		"Prada" ,"Trussardi","Versace","Yves Saint Laurent"};
	    for (int i = 0; i < znacky.length; i++) 
	    {
	    	JCheckBox checkbox = new JCheckBox(znacky[i]);
	    	checkbox.setBackground(Color.WHITE);
	        checkboxy3.add(checkbox); 
	        panel_1.add(checkbox);
	    }
	    
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 162, 454, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				DetailParfemu detail = new DetailParfemu(control,table);
				detail.setVisible(true);
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(70, 163, 89, 29);
		lblNewLabel_1.setFont(new Font("Gabriola", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(171, 164, 195, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//Tlacidlo, ktore vypise zoznam vsetkych parfemov
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBounds(70, 496, 141, 35);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.vypisZoznamParfemov(table);
			}
		});
		btnNewButton_2.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_2);
		
		//Tlacidlo, ktore vrati aplikaciu na hlavne menu
		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setBounds(225, 496, 141, 35);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false); 
			    dispose(); 
			    Menu.main(null);	
			}
		});
		btnNewButton_3.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_3);
		
		//Tlacidlo, ktore vyfiltruje zoznam vonaviek podla atributov zadanych pouzivatelom
		JButton btnNewButton_4 = new JButton();
		btnNewButton_4.setBounds(70, 455, 296, 35);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.vyfiltruj(table,textField, velkost, checkboxy3);
			}
		});
		btnNewButton_4.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(70, 223, 89, 16);
		lblNewLabel_2.setFont(new Font("Gabriola", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(70, 316, 69, 16);
		lblNewLabel_3.setFont(new Font("Gabriola", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_3);
		
		//Tlacidlo, ktore vytlaci obsah tabulky
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.vytlac(table);
			}
		});
		btnNewButton.setBounds(536, 502, 247, 29);
		btnNewButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		if(control.getJazyk() == 1) 
		{
			lblNewLabel.setText("Zoznam parfémov");
			lblNewLabel_1.setText("Názov:");
			lblNewLabel_2.setText("Veľkosť:");
			lblNewLabel_3.setText("Značka:");
			btnNewButton_2.setText("Celý zoznam");
			btnNewButton_3.setText("Hlavné menu");
			btnNewButton_4.setText("Vyhľadať");
			btnNewButton.setText("Vytlačiť zoznam");
			model.addColumn("ID");
			model.addColumn("Názov");
			model.addColumn("Veľkosť");
			model.addColumn("Značka");
		}
		else 
		{
			lblNewLabel.setText("List of perfumes");
			lblNewLabel_1.setText("Title:");
			lblNewLabel_2.setText("Size:");
			lblNewLabel_3.setText("Brand:");
			btnNewButton_2.setText("All perfumes");
			btnNewButton_3.setText("Menu");
			btnNewButton_4.setText("Search");
			btnNewButton.setText("Print the list");
			model.addColumn("ID");
			model.addColumn("Title");
			model.addColumn("Size");
			model.addColumn("Brand");
		}
		
		control.vypisZoznamParfemov(table);
	}
}
