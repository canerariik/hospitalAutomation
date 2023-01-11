package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_clinicAdı = new JLabel("Poliklinik Adı");
		lbl_clinicAdı.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_clinicAdı.setBounds(10, 10, 96, 13);
		contentPane.add(lbl_clinicAdı);

		fld_clinicName = new JTextField();
		fld_clinicName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(10, 27, 96, 19);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);

		JButton btn_updateClinic = new JButton("Güncelle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_updateClinic.setBounds(10, 56, 96, 21);
		contentPane.add(btn_updateClinic);
	}

}
