package View;

import java.awt.EventQueue;
import Helper.*;
import Model.BasHekim;
import Model.Doctor;
import Model.Hasta;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_doktorTC;
	private JTextField fld_hastaTC;
	private JPasswordField fld_doktorPsw;

	private DBConnection conDb = new DBConnection();
	private JPasswordField fld_hastaPsw;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginGUI() {
		setTitle("Hastahane Yönetim Sistemi");
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("logo.PNG")));
		lbl_logo.setBounds(152, 28, 171, 43);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastahane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(79, 81, 324, 33);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(27, 128, 428, 208);
		w_pane.add(w_tabpane);

		JPanel w_HastaLogin = new JPanel();
		w_HastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girişi", null, w_HastaLogin, null);
		w_HastaLogin.setLayout(null);

		JLabel lbl_hastaTC = new JLabel("T.C. Numarınız:");
		lbl_hastaTC.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_hastaTC.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_hastaTC.setBounds(49, 29, 99, 33);
		w_HastaLogin.add(lbl_hastaTC);

		fld_hastaTC = new JTextField();
		fld_hastaTC.setColumns(10);
		fld_hastaTC.setBounds(272, 38, 96, 19);
		w_HastaLogin.add(fld_hastaTC);

		JLabel lbl_hastaPsw = new JLabel("Şifre:");
		lbl_hastaPsw.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_hastaPsw.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_hastaPsw.setBounds(49, 72, 99, 33);
		w_HastaLogin.add(lbl_hastaPsw);

		JButton btn_hastaKayit = new JButton("Kayıt Ol");
		btn_hastaKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register = new RegisterGUI();
				register.setVisible(true);
				dispose();
			}
		});
		btn_hastaKayit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_hastaKayit.setBounds(98, 132, 85, 21);
		w_HastaLogin.add(btn_hastaKayit);

		JButton btn_hastaGiris = new JButton("Giriş Yap");
		btn_hastaGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTC.getText().length() == 0 || fld_hastaPsw.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;
					
					try {
						Connection con = conDb.conDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM users");

						while (rs.next()) {
							if (fld_hastaTC.getText().equals(rs.getString("tcno"))
									&& fld_hastaPsw.getText().equals(rs.getString("psw"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPsw("psw");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hastaGUI = new HastaGUI(hasta);
									hastaGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					if (key) {
						Helper.showMsg("Böyle bir kullanıcı bulunamadı, lütfen kayıt olunuz.");
					}

				}
			}
		});
		btn_hastaGiris.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_hastaGiris.setBounds(249, 132, 85, 21);
		w_HastaLogin.add(btn_hastaGiris);

		fld_hastaPsw = new JPasswordField();
		fld_hastaPsw.setBounds(272, 81, 96, 19);
		w_HastaLogin.add(fld_hastaPsw);

		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);

		JLabel lbl_doktorTC = new JLabel("T.C. Numarınız:");
		lbl_doktorTC.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_doktorTC.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doktorTC.setBounds(49, 29, 99, 33);
		w_doktorLogin.add(lbl_doktorTC);

		fld_doktorTC = new JTextField();
		fld_doktorTC.setColumns(10);
		fld_doktorTC.setBounds(272, 38, 96, 19);
		w_doktorLogin.add(fld_doktorTC);

		JLabel lbl_doktorPsw = new JLabel("Şifre:");
		lbl_doktorPsw.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_doktorPsw.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lbl_doktorPsw.setBounds(49, 72, 99, 33);
		w_doktorLogin.add(lbl_doktorPsw);

		JButton btn_doktorGiris = new JButton("Giriş Yap");
		btn_doktorGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorTC.getText().length() == 0 || fld_doktorPsw.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = conDb.conDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("Select * From users");

						while (rs.next()) {
							if (fld_doktorTC.getText().equals(rs.getString("tcno"))
									&& fld_doktorPsw.getText().equals(rs.getString("psw"))) {
								if (rs.getString("type").equals("basHekim")) {
									BasHekim bHekim = new BasHekim();
									bHekim.setId(rs.getInt("id"));
									bHekim.setPsw("psw");
									bHekim.setTcno(rs.getString("tcno"));
									bHekim.setName(rs.getString("name"));
									bHekim.setType(rs.getString("type"));
									BasHekimGUI bhGUI = new BasHekimGUI(bHekim);
									bhGUI.setVisible(true);
									dispose();
								}

								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPsw("psw");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorGiris.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_doktorGiris.setBounds(159, 131, 85, 21);
		w_doktorLogin.add(btn_doktorGiris);

		fld_doktorPsw = new JPasswordField();
		fld_doktorPsw.setBounds(272, 81, 96, 19);
		w_doktorLogin.add(fld_doktorPsw);
	}
}
