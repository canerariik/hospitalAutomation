package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;

import Helper.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class BasHekimGUI extends JFrame {

	static BasHekim basHekim = new BasHekim();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_doktorAdSoyad;
	private JTextField fld_doktorTCno;
	private JTextField fld_doktorPsw;
	private JTextField fld_doktorID;
	private JTable table_doktor;
	private JTable table_clinic;
	private JTextField fld_clinicAdı;
	private JPopupMenu clinicMenu;

	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;

	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTable table_worker;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(basHekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BasHekimGUI(BasHekim basHekim) throws SQLException {

		// DoktorListeleme
		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[4];
		colDoktorName[0] = "Id";
		colDoktorName[1] = "Ad Soyad";
		colDoktorName[2] = "TC";
		colDoktorName[3] = "Şifre";

		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[4];

		for (int i = 0; i < basHekim.getDoctorList().size(); i++) {
			doktorData[0] = basHekim.getDoctorList().get(i).getId();
			doktorData[1] = basHekim.getDoctorList().get(i).getName();
			doktorData[2] = basHekim.getDoctorList().get(i).getTcno();
			doktorData[3] = basHekim.getDoctorList().get(i).getPsw();

			doktorModel.addRow(doktorData);
		}
		//

		// ClinicListeleme
		clinicModel = new DefaultTableModel();
		Object[] colClinicName = new Object[2];
		colClinicName[0] = "Id";
		colClinicName[1] = "Poliklinik Adı";

		clinicModel.setColumnIdentifiers(colClinicName);
		clinicData = new Object[2];

		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();

			clinicModel.addRow(clinicData);
		}
		//

		// WorkerListeleme
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		//

		setTitle("Hastahane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 477);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın" + " " + basHekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel.setBounds(52, 10, 195, 21);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnNewButton.setBounds(610, 10, 85, 21);
		w_pane.add(btnNewButton);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(47, 86, 650, 341);
		w_pane.add(w_tabpane);

		JPanel w_doktorPanel = new JPanel();
		w_tabpane.addTab("Doktor Yönetimi", null, w_doktorPanel, null);
		w_doktorPanel.setLayout(null);

		JLabel lbl_doktorAdSoyad = new JLabel("Ad Soyad");
		lbl_doktorAdSoyad.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_doktorAdSoyad.setBounds(359, 10, 66, 13);
		w_doktorPanel.add(lbl_doktorAdSoyad);

		fld_doktorAdSoyad = new JTextField();
		fld_doktorAdSoyad.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_doktorAdSoyad.setBounds(359, 27, 96, 19);
		w_doktorPanel.add(fld_doktorAdSoyad);
		fld_doktorAdSoyad.setColumns(10);

		JLabel lbl_doktorTCno = new JLabel("T.C. No");
		lbl_doktorTCno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_doktorTCno.setBounds(359, 72, 66, 13);
		w_doktorPanel.add(lbl_doktorTCno);

		fld_doktorTCno = new JTextField();
		fld_doktorTCno.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_doktorTCno.setColumns(10);
		fld_doktorTCno.setBounds(359, 88, 96, 19);
		w_doktorPanel.add(fld_doktorTCno);

		JLabel lbl_doktorPsw = new JLabel("Şifre");
		lbl_doktorPsw.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_doktorPsw.setBounds(359, 131, 45, 13);
		w_doktorPanel.add(lbl_doktorPsw);

		JButton btn_doktorEkle = new JButton("Ekle");
		btn_doktorEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorAdSoyad.getText().length() == 0 || fld_doktorPsw.getText().length() == 0
						|| fld_doktorTCno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = basHekim.addDoktor(fld_doktorTCno.getText(), fld_doktorPsw.getText(),
								fld_doktorAdSoyad.getText());
						if (control) {
							Helper.showMsg("success");
							fld_doktorAdSoyad.setText(null);
							fld_doktorTCno.setText(null);
							fld_doktorPsw.setText(null);

							updateDoktorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btn_doktorEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_doktorEkle.setBounds(359, 175, 96, 21);
		w_doktorPanel.add(btn_doktorEkle);

		fld_doktorPsw = new JTextField();
		fld_doktorPsw.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_doktorPsw.setColumns(10);
		fld_doktorPsw.setBounds(359, 146, 96, 19);
		w_doktorPanel.add(fld_doktorPsw);

		fld_doktorID = new JTextField();
		fld_doktorID.setBounds(359, 243, 96, 19);
		w_doktorPanel.add(fld_doktorID);
		fld_doktorID.setColumns(10);

		JLabel lbl_doktorID = new JLabel("Kullanıcı ID");
		lbl_doktorID.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_doktorID.setBounds(359, 225, 82, 13);
		w_doktorPanel.add(lbl_doktorID);

		JButton btn_doktorSil = new JButton("Sil");
		btn_doktorSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doktorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz.");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doktorID.getText());

						try {
							boolean control = basHekim.deleteDoktor(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_doktorID.setText(null);
								updateDoktorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_doktorSil.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_doktorSil.setBounds(359, 272, 96, 21);
		w_doktorPanel.add(btn_doktorSil);

		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 10, 342, 294);
		w_doktorPanel.add(w_scrollDoktor);

		table_doktor = new JTable(doktorModel);
		w_scrollDoktor.setViewportView(table_doktor);
		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});

		table_doktor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
					String selectName = table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString();
					String selectTCno = table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString();
					String selectPsw = table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString();

					try {
						boolean control = basHekim.updateDoktor(selectID, selectTCno, selectPsw, selectName);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tabpane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 250, 294);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selectedID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());

				try {
					if (clinic.deleteClinic(selectedID)) {
						Helper.showMsg("success");
						updateClinicModel();
					} else {
						Helper.showMsg("error");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrollClinic.setViewportView(table_clinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(383, 10, 250, 294);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JLabel lbl_clinicAdı = new JLabel("Poliklinik Adı");
		lbl_clinicAdı.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_clinicAdı.setBounds(270, 10, 96, 13);
		w_clinic.add(lbl_clinicAdı);

		fld_clinicAdı = new JTextField();
		fld_clinicAdı.setFont(new Font("Tahoma", Font.PLAIN, 10));
		fld_clinicAdı.setColumns(10);
		fld_clinicAdı.setBounds(270, 27, 96, 19);
		w_clinic.add(fld_clinicAdı);

		JButton btn_clinicEkle = new JButton("Ekle");
		btn_clinicEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicAdı.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = clinic.addClinic(fld_clinicAdı.getText());
						if (control) {
							Helper.showMsg("success");
							fld_clinicAdı.setText(null);

							updateClinicModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_clinicEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_clinicEkle.setBounds(270, 53, 96, 21);
		w_clinic.add(btn_clinicEkle);

		JComboBox select_doktor = new JComboBox();
		select_doktor.setBounds(270, 242, 96, 31);
		for (int i = 0; i < basHekim.getDoctorList().size(); i++) {
			select_doktor.addItem(
					new Item(basHekim.getDoctorList().get(i).getId(), basHekim.getDoctorList().get(i).getName()));
		}
		select_doktor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + ":" + item.getValue());
		});
		w_clinic.add(select_doktor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doktor.getSelectedItem();
					try {
						boolean control = basHekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);

							for (int i = 0; i < basHekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0] = basHekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = basHekim.getClinicDoctorList(selClinicID).get(i).getName();

								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz.");
				}
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_addWorker.setBounds(270, 283, 96, 21);
		w_clinic.add(btn_addWorker);

		JLabel lbl_clinicAdı_1 = new JLabel("Poliklinik Adı");
		lbl_clinicAdı_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lbl_clinicAdı_1.setBounds(270, 131, 96, 13);
		w_clinic.add(lbl_clinicAdı_1);

		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selRow = table_clinic.getSelectedRow();

				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < basHekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = basHekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = basHekim.getClinicDoctorList(selClinicID).get(i).getName();

							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçin.");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_workerSelect.setBounds(270, 154, 96, 21);
		w_clinic.add(btn_workerSelect);

	}

	public void updateDoktorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < basHekim.getDoctorList().size(); i++) {
			doktorData[0] = basHekim.getDoctorList().get(i).getId();
			doktorData[1] = basHekim.getDoctorList().get(i).getName();
			doktorData[2] = basHekim.getDoctorList().get(i).getTcno();
			doktorData[3] = basHekim.getDoctorList().get(i).getPsw();

			doktorModel.addRow(doktorData);
		}

	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);

		for (int i = 0; i < clinic.getClinicList().size(); i++) {
			clinicData[0] = clinic.getClinicList().get(i).getId();
			clinicData[1] = clinic.getClinicList().get(i).getName();

			clinicModel.addRow(clinicData);
		}

	}
}
