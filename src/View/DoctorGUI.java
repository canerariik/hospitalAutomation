package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;
import Model.wHour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DoctorGUI extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_wHour;
	private DefaultTableModel wHourModel;
	private Object[] wHourData = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoctorGUI(Doctor doctor) throws SQLException {

		// wHour Listeleme
		wHourModel = new DefaultTableModel();
		Object[] colWHour = new Object[2];
		colWHour[0] = "ID";
		colWHour[1] = "Tarih";
		wHourModel.setColumnIdentifiers(colWHour);
		wHourData = new Object[2];

		for (int i = 0; i < doctor.getWHourList(doctor.getId()).size(); i++) {
			wHourData[0] = doctor.getWHourList(doctor.getId()).get(i).getId();
			wHourData[1] = doctor.getWHourList(doctor.getId()).get(i).getwDate();

			wHourModel.addRow(wHourData);
		}
		//

		setResizable(false);
		setTitle("Hastahane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + doctor.getName());
		lblNewLabel.setBounds(41, 20, 195, 21);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		w_pane.add(lblNewLabel);

		JButton btn_CikisYap = new JButton("Çıkış Yap");
		btn_CikisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btn_CikisYap.setBounds(599, 20, 85, 21);
		btn_CikisYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		w_pane.add(btn_CikisYap);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(34, 78, 650, 341);
		w_pane.add(w_tab);

		JPanel w_wHour = new JPanel();
		w_wHour.setBackground(Color.WHITE);
		w_tab.addTab("Çalışma Saatleri", null, w_wHour, null);
		w_wHour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 23, 130, 20);
		w_wHour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		select_time.setModel(new DefaultComboBoxModel(new String[] { "10.00", "10.30", "11.00", "11.30", "13.30",
				"14.00", "14.30", "15.00", "15.30", "16.00", "16.30", "17.00" }));
		select_time.setBounds(164, 22, 59, 21);
		w_wHour.add(select_time);

		JButton btn_addWHour = new JButton("Ekle");
		btn_addWHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";

				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {

				}

				if (date.length() == 0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz.");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;

					try {
						boolean control = doctor.addwHour(doctor.getId(), doctor.getName(), selectDate);

						if (control) {
							Helper.showMsg("success");
							updateWHourModel(doctor);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addWHour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_addWHour.setBounds(241, 23, 70, 20);
		w_wHour.add(btn_addWHour);

		JScrollPane w_scrollWHour = new JScrollPane();
		w_scrollWHour.setBounds(10, 58, 625, 246);
		w_wHour.add(w_scrollWHour);

		table_wHour = new JTable(wHourModel);
		w_scrollWHour.setViewportView(table_wHour);

		JButton btn_deleteWHour = new JButton("Sil");
		btn_deleteWHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_wHour.getSelectedRow();
				if (selRow >= 0) {
					String selectRow = table_wHour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);

					boolean control;
					try {
						control = doctor.deleteWHour(selID);
						if (control) {
							Helper.showMsg("success");
							updateWHourModel(doctor);
						} else {
							Helper.showMsg("error");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen silmek istediğiniz bir randevu seçiniz.");
				}
			}
		});
		btn_deleteWHour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_deleteWHour.setBounds(565, 23, 70, 20);
		w_wHour.add(btn_deleteWHour);
	}

	public void updateWHourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_wHour.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < doctor.getWHourList(doctor.getId()).size(); i++) {
			wHourData[0] = doctor.getWHourList(doctor.getId()).get(i).getId();
			wHourData[1] = doctor.getWHourList(doctor.getId()).get(i).getwDate();

			wHourModel.addRow(wHourData);
		}

	}
}
