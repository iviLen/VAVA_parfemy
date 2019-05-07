package view;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.Controller;
import javax.swing.SwingConstants;

/*
 * Trieda Menu, ktora sa zobrazi hned po spusteni aplikacie.
 * Je v nej mozne zmenit farbu a jazyk aplikacie.
 * Farba pozadia je primarne nastavena na ruzovu a jazyk aplikacie na slovensky.
 * Vzdy sa kontroluje jazyk a farba pozadia, ktoru si pouzivatel zvolil a podla toho sa zobrazi.
 */

public class Menu {

	public JFrame frame;
	private Controller control = Controller.getInstance();
	int farba;
	
	public static void main(String[] args) {
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

	public Menu() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245,245,245));
		frame.setBounds(100, 100, 949, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel obrazocek = new JLabel("");
		obrazocek.setBounds(417, 52, 511, 494);
		obrazocek.setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource("/vonavka.png")).getImage();
		frame.getContentPane().setLayout(null);
		obrazocek.setIcon(new ImageIcon(img));
		frame.getContentPane().add(obrazocek);
		
		JPanel panel = new JPanel();
		panel.setBounds(70, 142, 232, 359);
	
		if(control.getFarba() == 1)
			panel.setBackground(new Color(204, 255, 204));
		else if(control.getFarba() == 2)
			panel.setBackground(new Color(250,226,232));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//Tlacidlo pomocou ktoreho sa pouzivatel presmeruje na obrazovku moje parfemy
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MojeParfemy moje = new MojeParfemy(control);
				moje.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Gabriola", Font.PLAIN, 25));
		btnNewButton.setBounds(19, 41, 196, 65);
		panel.add(btnNewButton);
		btnNewButton.setBackground(new Color(245,245,245));
		btnNewButton.setOpaque(true);
		btnNewButton.setBorderPainted(false);
		
		//Tlacidlo pomocou ktoreho sa pouzivatel presmeruje na obrazovku moj wishlist
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MojWishlist wishlist = new MojWishlist(control);
				wishlist.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Gabriola", Font.PLAIN, 25));
		btnNewButton_1.setBounds(19, 147, 196, 65);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(245,245,245));
		btnNewButton_1.setOpaque(true);
		btnNewButton_1.setBorderPainted(false);
		
		//Tlacidlo pomocou ktoreho sa pouzivatel presmeruje na obrazovku zoznam parfemov
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ZoznamParfemov zoznam = new ZoznamParfemov(control);
				zoznam.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Gabriola", Font.PLAIN, 25));
		btnNewButton_2.setBounds(19, 253, 196, 65);
		panel.add(btnNewButton_2);
		btnNewButton_2.setBackground(new Color(245,245,245));
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBorderPainted(false);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(70, 36, 252, 78);
		lblNewLabel.setFont(new Font("Gabriola", Font.PLAIN, 60));
		frame.getContentPane().add(lblNewLabel);
		
		//Tlacidlo, ktore nastavi farbu aplikacie na zelenu
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(872, 13, 34, 25);
		btnNewButton_3.setBackground(new Color(204, 255, 204));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.setFarba(1);
				panel.setBackground(new Color(204, 255, 204));
			}
		});
		frame.getContentPane().add(btnNewButton_3);
		
		//Tlacidlo, ktore nastavi farbu aplikacie na ruzovu
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(826, 13, 34, 25);
		btnNewButton_4.setBackground(new Color(250,226,232));
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setOpaque(true);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.setFarba(2);
				panel.setBackground(new Color(250,226,232));
			}
		});
		frame.getContentPane().add(btnNewButton_4);
		
		//Tlacidlo, ktore nastavi jazyk aplikacie na SK
		JButton btnSk = new JButton("SK");
		btnSk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.setJazyk(1);
				btnNewButton.setText("Moje parfémy");
				btnNewButton_1.setText("Môj wishlist");
				btnNewButton_2.setText("Zoznam parfémov");
				lblNewLabel.setText("Parfémy");
			}
		});
		btnSk.setHorizontalAlignment(SwingConstants.LEFT);
		btnSk.setBounds(708, 13, 47, 25);
		frame.getContentPane().add(btnSk);
		
		//Tlacidlo, ktore nastavi jazyk aplikacie na EN
		JButton btnEn = new JButton("EN");
		btnEn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.setJazyk(2);
				btnNewButton.setText("My perfumes");
				btnNewButton_1.setText("My wishlist");
				btnNewButton_2.setText("List of perfumes");
				lblNewLabel.setText("Perfumes");
			}
		});
		btnEn.setHorizontalAlignment(SwingConstants.LEFT);
		btnEn.setBounds(767, 13, 47, 25);
		frame.getContentPane().add(btnEn);
		
		if(control.getJazyk() == 1) 
		{
			btnNewButton.setText("Moje parfémy");
			btnNewButton_1.setText("Môj wishlist");
			btnNewButton_2.setText("Zoznam parfémov");
			lblNewLabel.setText("Parfémy");
		}
		else
		{
			btnNewButton.setText("My perfumes");
			btnNewButton_1.setText("My wishlist");
			btnNewButton_2.setText("List of perfumes");
			lblNewLabel.setText("Perfumes");
		}
	}
}
