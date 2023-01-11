package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.*;

import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.wHour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_wHour;
	private wHour whour = new wHour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;

	private Appointment appoint = new Appointment();
	private DefaultTableModel appointModel;
	private Object[] appointData = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HastaGUI(Hasta hasta) throws SQLException {

		// DoktorListeleme
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "Id";
		colDoctor[1] = "Ad Soyad";

		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];
		//

		// wHourListeleme
		whourModel = new DefaultTableModel();
		Object[] colWHour = new Object[2];
		colWHour[0] = "Id";
		colWHour[1] = "Tarih";

		whourModel.setColumnIdentifiers(colWHour);
		whourData = new Object[2];
		//

		// randevuListeleme
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "Id";
		colAppoint[1] = "Doktor";
		colAppoint[2] = "Tarih";

		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];

		for (int i = 0; i < appoint.getHastaList(hasta.getId()).size(); i++) {
			appointData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
		//

		setResizable(false);
		setTitle("Hastahane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 477);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel.setBounds(36, 23, 195, 21);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnNewButton.setBounds(594, 23, 85, 21);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(36, 68, 700, 341);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 34, 243, 270);
		w_appointment.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel lbl_doktorListesi = new JLabel("Doktor Listesi");
		lbl_doktorListesi.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_doktorListesi.setBounds(10, 10, 195, 21);
		w_appointment.add(lbl_doktorListesi);

		JLabel lbl_klinikAdı = new JLabel("Poliklinik Adı");
		lbl_klinikAdı.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_klinikAdı.setBounds(298, 38, 81, 21);
		w_appointment.add(lbl_klinikAdı);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(298, 72, 99, 21);
		select_clinic.addItem("--Poliklinik Seç--");
		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			select_clinic
					.addItem(new Item(clinic.getClinicList().get(i).getId(), clinic.getClinicList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}

		});
		w_appointment.add(select_clinic);

		JLabel lbl_klinikAdı_1 = new JLabel("Doktor Seç");
		lbl_klinikAdı_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_klinikAdı_1.setBounds(298, 129, 81, 21);
		w_appointment.add(lbl_klinikAdı_1);

		JButton btn_selectDoctor = new JButton("Seç");
		btn_selectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_wHour.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < whour.getWHourList(id).size(); i++) {
							whourData[0] = whour.getWHourList(id).get(i).getId();
							whourData[1] = whour.getWHourList(id).get(i).getwDate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();

				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz!!");
				}
			}
		});
		btn_selectDoctor.setBounds(298, 154, 85, 21);
		w_appointment.add(btn_selectDoctor);

		JLabel lbl_wHourList = new JLabel("Uygun Randevu");
		lbl_wHourList.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_wHourList.setBounds(429, 10, 195, 21);
		w_appointment.add(lbl_wHourList);

		JScrollPane w_scrollWHours = new JScrollPane();
		w_scrollWHours.setBounds(429, 34, 243, 270);
		w_appointment.add(w_scrollWHours);

		table_wHour = new JTable(whourModel);
		w_scrollWHours.setViewportView(table_wHour);

		JLabel lbl_RandevuAlımı = new JLabel("Randevu Alımı");
		lbl_RandevuAlımı.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_RandevuAlımı.setBounds(298, 212, 81, 21);
		w_appointment.add(lbl_RandevuAlımı);

		JButton btn_addAppoint = new JButton("Randevu Alımı");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = table_wHour.getSelectedRow();

				if (selRow >= 0) {
					String date = table_wHour.getModel().getValueAt(selRow, 1).toString();

					try {
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
								hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.updateWHourStatus(selectDoctorID, date);
							updateWHourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz.");
				}
			}
		});
		btn_addAppoint.setBounds(298, 237, 99, 21);
		w_appointment.add(btn_addAppoint);

		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevular", null, w_appoint, null);
		w_appoint.setLayout(null);

		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 10, 675, 294);
		w_appoint.add(w_scrollAppoint);

		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
	}

	public void updateWHourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_wHour.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < whour.getWHourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWHourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWHourList(doctor_id).get(i).getwDate();
			whourModel.addRow(whourData);
		}
	}

	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < appoint.getHastaList(hasta_id).size(); i++) {
			appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
