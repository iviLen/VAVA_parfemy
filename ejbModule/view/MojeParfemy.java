package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.Controller;

/*
 * Trieda MojeParfemy, vdaka ktorej sa zobrazi obrazovka so zoznamom mojich parfemov.
 * Vzdy sa kontroluje jazyk a farba pozadia, ktoru si pouzivatel zvolil a podla toho sa zobrazi.
 */

public class MojeParfemy extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public MojeParfemy(Controller control) {
	
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 153, 590, 352);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		//Tlacidlo, ktore vymaze vonavku z mojich parfemov
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
		
				if (row == -1) 
				{
					if(control.getJazyk() == 1)
						JOptionPane.showMessageDialog(null, "Označte voňavku.");
					else
						JOptionPane.showMessageDialog(null, "Select the perfume.");
				}
				else 
				{
					int value = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
					int odstranenie = control.odstranZMojichParfemov(value);	
					if(odstranenie == 1)
					{
						if(control.getJazyk() == 1)
							JOptionPane.showMessageDialog(null, "Voňavka bola vymazaná z Mojich Parfémov.");
						else
							JOptionPane.showMessageDialog(null, "The perfume was deleted from My Perfumes.");
					}
					else
					{
						if(control.getJazyk() == 1)
							JOptionPane.showMessageDialog(null, "Voňavku sa nepodarilo vymazať z Mojich Parfémov.");
						else
							JOptionPane.showMessageDialog(null, "Deleting the perfume was unsuccessful.");
					}
					control.vypisMojeParfemy(table);
				}
			}
		});
		btnNewButton.setBounds(706, 196, 149, 40);
		btnNewButton.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		//Tlacidlo, ktore prida vonavku do mojho wishlistu
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(706, 303, 149, 40);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vonavka_id = control.zistiIDVonavkyVMojichParfemoch(table);
			
				if (vonavka_id == -1)
				{
					if(control.getJazyk() == 1)
						JOptionPane.showMessageDialog(null, "Označte voňavku.");
					else
						JOptionPane.showMessageDialog(null, "Select the perfume.");
				}
				else
				{
					int pridanie = control.pridajDoWishListu(vonavka_id);	
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
			}
		});
		btnNewButton_1.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_1);
		
		//Tlacidlo, ktore vrati aplikaciu na hlavne menu
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false); 
			    dispose(); 
			    Menu.main(null);	
			}
		});
		btnNewButton_2.setBounds(706, 417, 149, 40);
		btnNewButton_2.setFont(new Font("Gabriola", Font.PLAIN, 20));
		contentPane.add(btnNewButton_2);
		
		if(control.getJazyk() == 1) 
		{
			lblNewLabel.setText("Moje parfémy");
			btnNewButton.setText("Odstrániť");
			btnNewButton_1.setText("Pridať do wishlistu");
			btnNewButton_2.setText("Hlavné menu");
			model.addColumn("ID");
			model.addColumn("Názov");
			model.addColumn("Veľkosť");
			model.addColumn("Značka");
		}
		else 
		{
			lblNewLabel.setText("My perfumes");
			btnNewButton.setText("Remove");
			btnNewButton_1.setText("Add to wishlist");
			btnNewButton_2.setText("Menu");
			model.addColumn("ID");
			model.addColumn("Title");
			model.addColumn("Size");
			model.addColumn("Brand");
		}
		control.vypisMojeParfemy(table);
	}
}
