package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.Users;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_psw;
	private Hasta hasta = new Hasta();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastahane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_RegisterAdSoyad = new JLabel("Ad Soyad");
		lbl_RegisterAdSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_RegisterAdSoyad.setBounds(10, 10, 66, 13);
		w_pane.add(lbl_RegisterAdSoyad);

		fld_name = new JTextField();
		fld_name.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 27, 96, 19);
		w_pane.add(fld_name);

		JLabel lbl_RegisterAdSoyad_1 = new JLabel("T.C. Numarası");
		lbl_RegisterAdSoyad_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_RegisterAdSoyad_1.setBounds(10, 68, 96, 13);
		w_pane.add(lbl_RegisterAdSoyad_1);

		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 85, 96, 19);
		w_pane.add(fld_tcno);

		JLabel lbl_RegisterAdSoyad_2 = new JLabel("Şifre");
		lbl_RegisterAdSoyad_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_RegisterAdSoyad_2.setBounds(10, 127, 66, 13);
		w_pane.add(lbl_RegisterAdSoyad_2);

		fld_psw = new JPasswordField();
		fld_psw.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 10));
		fld_psw.setBounds(10, 150, 96, 19);
		w_pane.add(fld_psw);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_tcno.getText().length() == 0 || fld_psw.getText().length() == 0
						|| fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_psw.getText(), fld_name.getText(),
								"hasta");
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_register.setBounds(10, 191, 85, 21);
		w_pane.add(btn_register);

		JButton btn_goBack = new JButton("Geri Dön");
		btn_goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_goBack.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_goBack.setBounds(136, 191, 85, 21);
		w_pane.add(btn_goBack);
	}
}
