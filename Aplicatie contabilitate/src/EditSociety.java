import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.JTableHeader;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSociety implements ApplyThemeAndFont {

	private static JFrame frame;
	private static JLabel label, sub_label_1, sub_label_2;
	private static JLabel label_1;
	private static JLabel label_2;
	private static JLabel label_3;
	private static JLabel label_4;
	private static JLabel label_5;
	private static JLabel label_6;
	private static JLabel label_7;
	private static JLabel label_8;
	private static JLabel label_9;
	private static JLabel label_10;
	private static JLabel label_11;
	private static JLabel label_12;
	private static Field textField;
	private static Field textField_1;
	private static Field textField_2;
	private static Field textField_3;
	private static Field textField_4;
	private static Field textField_5;
	private static Field textField_6;
	private static Field textField_7;
	private static Field textField_8;
	private static Field textField_9;
	private static Field textField_10;
	private static JCheckBox check_box;
	private static JComboBox <String> item_list;
	private static BackButton back_button;
	private static JButton add_raport_button, delete_raport_button, edit_button;
	private static JPanel society_data_panel, raport_data_panel;
	private static JPanel fields_panel, table_panel;
	private static JLabel label_2_1, label_2_2, label_2_3;
	private static JTextField textField_2_1, textField_2_2, textField_2_3;
	private static JScrollPane scrollPane;
	private static JTable raport_table;
	private static JTableHeader header;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new EditSociety();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditSociety() {
		initialize();
	}

	private void initialize() {
		Settings.read_settings_from_file("src/files/settings.txt");
		
		SearchByName.connect_to_database("jdbc:mysql://localhost:3306/my_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "bazededate2");
		
		frame = new JFrame();
		frame.setBounds(400, 150, 877, 520);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("EDITARE SOCIETATE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		back_button = new BackButton();
		back_button.setLocation(10, 430);
		frame.getContentPane().add(back_button);
		
		label = new JLabel("Selecta??i societatea pe care\n dori??i s?? o edita??i: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(27, 40, 373, 37);
		frame.getContentPane().add(label);
		
		item_list = new JComboBox<String>();
		item_list.setBounds(27, 80, 280, 21);
		frame.getContentPane().add(item_list);
		
		society_data_panel = new JPanel();
		society_data_panel.setBounds(410, 35, 439, 370);
		frame.getContentPane().add(society_data_panel);
		society_data_panel.setLayout(new GridLayout(12, 2, 0, 5));
		society_data_panel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		
		label_1 = new JLabel("Nume *");
		society_data_panel.add(label_1);
		
		textField = new Field("Nume");
		society_data_panel.add(textField);
		textField.setColumns(10);
		
		label_2 = new JLabel("C.A.E.N. *");
		society_data_panel.add(label_2);
		
		textField_1 = new Field("C.A.E.N.");
		society_data_panel.add(textField_1);
		textField_1.setColumns(10);
		
		label_3 = new JLabel("Pl??titor TVA");
		society_data_panel.add(label_3);
		
		check_box = new JCheckBox("");
		check_box.setHorizontalAlignment(SwingConstants.CENTER);
		society_data_panel.add(check_box);
		
		label_4 = new JLabel("C.U.I. (f??r?? 'RO') *");
		society_data_panel.add(label_4);
		
		textField_2 = new Field("C.U.I.");
		society_data_panel.add(textField_2);
		textField_2.setColumns(10);
		
		label_5 = new JLabel("E.U.I.D.");
		society_data_panel.add(label_5);
		
		textField_3 = new Field("E.U.I.D.");
		society_data_panel.add(textField_3);
		textField_3.setColumns(10);
		
		label_6 = new JLabel("Num??r de ordine *");
		society_data_panel.add(label_6);
		
		textField_4 = new Field("Nr. ordine");
		society_data_panel.add(textField_4);
		textField_4.setColumns(10);
		
		label_7 = new JLabel("Nume administrator");
		society_data_panel.add(label_7);
		
		textField_5 = new Field("Nume administrator");
		society_data_panel.add(textField_5);
		textField_5.setColumns(10);
		
		label_8 = new JLabel("Telefon");
		society_data_panel.add(label_8);
		
		textField_6 = new Field("Telefon");
		society_data_panel.add(textField_6);
		textField_6.setColumns(10);
		
		label_9 = new JLabel("Email");
		society_data_panel.add(label_9);
		
		textField_7 = new Field("Email");
		society_data_panel.add(textField_7);
		textField_7.setColumns(10);
		
		label_10 = new JLabel("Jude??  ");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		society_data_panel.add(label_10);
		
		textField_8 = new Field("Jude??");
		society_data_panel.add(textField_8);
		textField_8.setColumns(10);
		
		label_11 = new JLabel("Adres?? sediu * :                       Localitate");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		society_data_panel.add(label_11);
		
		textField_9 = new Field("Localitate");
		society_data_panel.add(textField_9);
		textField_9.setColumns(10);
		
		label_12 = new JLabel("Adres?? efectiv??  ");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		society_data_panel.add(label_12);
		
		textField_10 = new Field("Adres?? efectiv??");
		society_data_panel.add(textField_10);
		textField_10.setColumns(10);
		
		check_box.setOpaque(false);
		
		raport_data_panel = new JPanel();
		raport_data_panel.setBounds(47, 162, 230, 243);
		raport_data_panel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		frame.getContentPane().add(raport_data_panel);
		raport_data_panel.setLayout(null);
		
		fields_panel = new JPanel();
		fields_panel.setBackground(Color.WHITE);
		fields_panel.setOpaque(false);
		fields_panel.setBounds(10, 10, 181, 79);
		raport_data_panel.add(fields_panel);
		fields_panel.setLayout(new GridLayout(3, 2, 0, 5));
		
		label_2_1 = new JLabel("An");
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		fields_panel.add(label_2_1);
		
		textField_2_1 = new JTextField();
		fields_panel.add(textField_2_1);
		textField_2_1.setColumns(10);
		
		label_2_2 = new JLabel("Cifr?? afaceri");
		label_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		fields_panel.add(label_2_2);
		
		textField_2_2 = new JTextField();
		fields_panel.add(textField_2_2);
		textField_2_2.setColumns(10);
		
		label_2_3 = new JLabel("Profit/pierderi");
		label_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		fields_panel.add(label_2_3);
		
		textField_2_3 = new JTextField();
		fields_panel.add(textField_2_3);
		textField_2_3.setColumns(10);
		
		table_panel = new JPanel();
		table_panel.setBackground(Color.WHITE);
		table_panel.setBounds(10, 99, 181, 134);
		raport_data_panel.add(table_panel);
		table_panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 181, 134);
		table_panel.add(scrollPane);
		
		raport_table = new JTable();
		header = raport_table.getTableHeader();
		header.setReorderingAllowed(false);
		scrollPane.setViewportView(raport_table);
		raport_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		add_raport_button = new JButton("");
		add_raport_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!check_raport_fields()) {
					JOptionPane.showMessageDialog(scrollPane, "C??mpurile nu au formatul corect..");
					return;
				}
				
				String society_name = (String) item_list.getSelectedItem();
				String statement = "SELECT `id` FROM `societate` WHERE `Nume` = '" + society_name + "';";
				int society_id = -1, raport_id = -1;
				try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
					ResultSet results = stm.executeQuery(statement);
					while (results.next())
						society_id = results.getInt("id");
					
					if (!is_year_unique(society_id, textField_2_1.getText())) {
						JOptionPane.showMessageDialog(scrollPane, "C??mpul cu anul trebuie s?? fie unic pe societate..");
						return;
					}
					
					String statement_2 = "INSERT INTO `raport` (`id_societate`,`an`,`cifra_afaceri`,`profit/pierderi`) VALUES ('"
							 + String.valueOf(society_id) + "','"
			                 + textField_2_1.getText() + "','"
			                 + textField_2_2.getText() + "','"
			                 + textField_2_3.getText() + "');";
					
					try (PreparedStatement stm_2 = SearchByName.connection.prepareStatement(statement_2)) {
						stm_2.execute();
						String statement_3 = "SELECT `id` FROM `raport` WHERE `id_societate` = '" + String.valueOf(society_id) + "' AND `an` = '" + textField_2_1.getText() + "';";
						
						try (PreparedStatement stm_3 = SearchByName.connection.prepareStatement(statement_3)) {
							ResultSet results_2 = stm_3.executeQuery(statement_3);
							while (results_2.next()) {
								raport_id = results_2.getInt("id");
							}
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(scrollPane, e);
							return;
						}
						String statement_4 = "INSERT INTO `societate/raport` (`societate`,`raport`) VALUES ('" + String.valueOf(society_id) + "','" + String.valueOf(raport_id) + "');";
						try (PreparedStatement stm_4 = SearchByName.connection.prepareStatement(statement_4)) {
							stm_4.execute();
							JOptionPane.showMessageDialog(scrollPane, "A??i ad??ugat cu succes raportul..");
							textField_2_1.setText("");
							textField_2_2.setText("");
							textField_2_3.setText("");
							populate_raport_table(society_id);
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(scrollPane, e);
							return;
						}
						return;
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(scrollPane, e);
						return;
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(scrollPane, e);
					return;
				}			
				
				
				
//				try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement_2)) {
//					stm.execute();
//					String statement_3 = "SELECT `id` FROM `raport` WHERE `nume_societate` = '" + society_name + "' AND `an` = '" + textField_2_1.getText() + "';";
					
//					try (PreparedStatement stm_2 = SearchByName.connection.prepareStatement(statement_3)) {
//						ResultSet results = stm_2.executeQuery(statement_3);
//						while (results.next()) {
//							raport_id = results.getString("id");
//						}
//						System.out.println("Raport id: " + raport_id);
//					} catch (SQLException e) {
//						JOptionPane.showMessageDialog(scrollPane, e);
//					}
//					String statement_4 = "INSERT INTO `societate/raport` (`societate`,`raport`) VALUES ('" + society_id + "','" + raport_id + "');";
//					try (PreparedStatement stm_3 = SearchByName.connection.prepareStatement(statement_4)) {
//						stm_3.execute();
//						JOptionPane.showMessageDialog(scrollPane, "A??i ad??ugat cu succes raportul..");
//						textField_2_1.setText("");
//						textField_2_2.setText("");
//						textField_2_3.setText("");
//						populate_raport_table(society_name);
//					} catch (SQLException e) {
//						JOptionPane.showMessageDialog(scrollPane, e);
//						return;
//					}
//					return;
//				} catch (SQLException e) {
//					JOptionPane.showMessageDialog(scrollPane, e);
//					return;
//				}
				
				//System.out.println(statement);
				
			}
		});
		add_raport_button.setBounds(198, 37, 24, 24);
		raport_data_panel.add(add_raport_button);
		add_raport_button.setIcon(new ImageIcon(EditSociety.class.getResource("/images/plus.png")));
		
		delete_raport_button = new JButton("");
//		delete_raport_button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				int row = raport_table.getSelectionModel().getMinSelectionIndex();
//				try {
//					String year = (String) raport_table.getValueAt(row, 0);
//					if (year == null) {
//						JOptionPane.showMessageDialog(scrollPane, "Nu a??i selectat niciun raport..");
//						return;
//					}
//					String statement = "DELETE FROM `raport` WHERE `an` = '" + year + "';";
//					try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
//						int input = JOptionPane.showConfirmDialog(scrollPane, "E??ti sigur c?? vrei s?? ??tergi raportul selectat?");
//						if ((input == JOptionPane.CANCEL_OPTION) || (input == JOptionPane.NO_OPTION) || (input == JOptionPane.CLOSED_OPTION))
//							return;
//						stm.execute();
//						JOptionPane.showMessageDialog(scrollPane, "A??i ??ters cu succes raportul selectat..");
//						String society_name = (String) item_list.getSelectedItem();
//						
//						String statement_2 = "SELECT `id` FROM `societate` WHERE `Nume` = '" + society_name + "';";
//						int society_id = -1;
//						try (PreparedStatement stm_2 = SearchByName.connection.prepareStatement(statement_2)) {
//							ResultSet results_2 = stm.executeQuery(statement);
//							while (results_2.next())
//								society_id = results_2.getInt("id");
//							populate_raport_table(society_id);
//						} catch (SQLException e) {
//							JOptionPane.showMessageDialog(scrollPane, e);
//							return;
//						}
//						
//					} catch (SQLException e) {
//						JOptionPane.showMessageDialog(scrollPane, e);
//						return;
//					}
//				} catch (ArrayIndexOutOfBoundsException e) {
//					return;
//				}
//				//System.out.println(statement);
//			}
//		});
		
		delete_raport_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = raport_table.getSelectionModel().getMinSelectionIndex();
				try {
					String year = (String) raport_table.getValueAt(row, 0);
					String society_name = (String) item_list.getSelectedItem();
			
					if (year == null) {
						JOptionPane.showMessageDialog(scrollPane, "Nu a??i selectat niciun raport..");
						return;
					}
					
					String statement = "SELECT `id` FROM `societate` WHERE `Nume` = '" + society_name + "';";
					int society_id = -1;
					try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
						ResultSet results = stm.executeQuery(statement);
						while (results.next())
							society_id = results.getInt("id");
//						populate_raport_table(society_id);
						
						String statement_2 = "DELETE FROM `raport` WHERE `an` = '" + year + "' AND `id_societate` = " + String.valueOf(society_id) + ";";
						try (PreparedStatement stm_2 = SearchByName.connection.prepareStatement(statement_2)) {
							int input = JOptionPane.showConfirmDialog(scrollPane, "E??ti sigur c?? vrei s?? ??tergi raportul selectat?");
							if ((input == JOptionPane.CANCEL_OPTION) || (input == JOptionPane.NO_OPTION) || (input == JOptionPane.CLOSED_OPTION))
								return;
							
							stm_2.execute(statement_2);
							JOptionPane.showMessageDialog(scrollPane, "A??i ??ters cu succes raportul selectat..");
							populate_raport_table(society_id);
							System.out.println(statement_2);
							
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(scrollPane, e);
							return;
						}
						
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(scrollPane, e);
						return;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					return;
				}
			}
		});
		
		delete_raport_button.setBounds(198, 153, 24, 24);
		raport_data_panel.add(delete_raport_button);
		delete_raport_button.setIcon(new ImageIcon(EditSociety.class.getResource("/images/minus.png")));
		
		sub_label_1 = new JLabel("Mai jos pute??i ad??uga / ??terge un raport");
		sub_label_1.setBounds(27, 110, 280, 21);
		frame.getContentPane().add(sub_label_1);
		
		sub_label_2 = new JLabel("dintr-un an al societ????ii selectate: ");
		sub_label_2.setBounds(27, 130, 280, 21);
		frame.getContentPane().add(sub_label_2);
		
		item_list.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getItem() != "") {
					String society_name = (String) event.getItem();
					fill_fields(society_name);
					enable_or_disable_fields(true);
					
					String statement = "SELECT `id` FROM `societate` WHERE `Nume` = '" + society_name + "';";
					int society_id = -1;
					try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
						ResultSet results = stm.executeQuery(statement);
						while (results.next())
							society_id = results.getInt("id");
						populate_raport_table(society_id);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(scrollPane, e);
						return;
					}
					
				}
				else {
					enable_or_disable_fields(false);
					return;
				}
			}
		});
		
		add_items_to_list(item_list);
		
		edit_button = new JButton("EDITEAZ??");
		edit_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String name = (String) item_list.getSelectedItem();
				if (!name.isBlank()) {
					if (edit_society(name)) {
						item_list.removeAllItems();
						add_items_to_list(item_list);
					}
				}
			}
		});
		edit_button.setBounds(721, 442, 128, 29);
		frame.getContentPane().add(edit_button);
		
		apply_theme();
		apply_font();
	}
	
	private boolean is_year_unique (int society_id, String year) {
		String statement = "SELECT `an` FROM `raport` WHERE `id_societate` = '" + society_id + "';";
		//System.out.println(statement);
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			ResultSet results = stm.executeQuery(statement);
			ArrayList<String> years = new ArrayList<String>(75);
			while (results.next()) {
				years.add(results.getString("an"));
			}
			for (String i : years)
				if (i.compareTo(year) == 0)
					return false;
			return true;
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(scrollPane, e);
			System.out.println("Eroare..");
			return true;
		}
	}
	
	private boolean check_raport_fields() {
		try {
			Integer year = Integer.valueOf(textField_2_1.getText());
			Integer CA = Integer.valueOf(textField_2_2.getText());
			float PP = Float.valueOf(textField_2_3.getText());
			if (year >= 1500 && year <= 2040)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println("Eroare..");
			return false;
		}
	}
	
	private void populate_raport_table (int society_id) {
		String statement = "SELECT `an`,`cifra_afaceri`,`profit/pierderi` FROM `raport` WHERE `id_societate` = '" + society_id + "' ORDER BY `an`;";
		//System.out.println(statement);
		String[] columns = {"An","CA","Profit/pierderi"};
		String[][] data = new String[75][3];
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			int i = 0;
			ResultSet results = stm.executeQuery(statement);
			if (results.next() == false) {
				raport_table.setModel(new MyTableModel(data, columns));
				raport_table.getColumnModel().getColumn(0).setPreferredWidth(45);
				return;
			}
			do {
				data[i][0] = results.getString("an");
				data[i][1] = results.getString("cifra_afaceri");
				data[i][2] = results.getString("profit/pierderi");
				i ++;
			} while (results.next());
			raport_table.setModel(new MyTableModel(data, columns));
			raport_table.getColumnModel().getColumn(0).setPreferredWidth(45);
			header = raport_table.getTableHeader();
			header.setReorderingAllowed(false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(scrollPane, e);
		}
	}
	
	private void enable_or_disable_fields (boolean are_enabled) {
		textField.setEditable(are_enabled);
		textField_1.setEditable(are_enabled);
		textField_2.setEditable(are_enabled);
		textField_3.setEditable(are_enabled);
		textField_4.setEditable(are_enabled);
		textField_5.setEditable(are_enabled);
		textField_6.setEditable(are_enabled);
		textField_7.setEditable(are_enabled);
		textField_8.setEditable(are_enabled);
		textField_9.setEditable(are_enabled);
		textField_10.setEditable(are_enabled);
		textField_2_1.setEditable(are_enabled);
		textField_2_2.setEditable(are_enabled);
		textField_2_3.setEditable(are_enabled);
		check_box.setEnabled(are_enabled);
		raport_data_panel.setVisible(are_enabled);
		sub_label_1.setVisible(are_enabled);
		sub_label_2.setVisible(are_enabled);
		
		if (!are_enabled) {
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
			textField_4.setText("");
			textField_5.setText("");
			textField_6.setText("");
			textField_7.setText("");
			textField_8.setText("");
			textField_9.setText("");
			textField_10.setText("");
			textField_2_1.setText("");
			textField_2_2.setText("");
			textField_2_3.setText("");
			check_box.setSelected(false);
		}
	}
	
	private void fill_fields (String society_name) {
		String statement = "SELECT * FROM `societate` WHERE `Nume` = '";
		statement += society_name;
		statement += "';";
		
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			ResultSet results = stm.executeQuery(statement);
			if (results.next() == false)
				return;
			
			textField.setText(results.getString("Nume"));
			textField_1.setText(results.getString("C.A.E.N."));
			textField_2.setText(results.getString("C.U.I"));
			textField_3.setText(results.getString("E.U.I.D."));	
			textField_4.setText(results.getString("Nr. ordine"));
			textField_5.setText(results.getString("Proprietar"));
			textField_6.setText(results.getString("Telefon"));
			textField_7.setText(results.getString("Email"));
			textField_8.setText(results.getString("Jude??"));
			textField_9.setText(results.getString("Localitate"));
			textField_10.setText(results.getString("Adres?? efectiv??"));
			
			if (results.getString("Platitor_TVA").compareTo("DA") == 0)
				check_box.setSelected(true);
			else
				check_box.setSelected(false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e);
		}
	}
	
	private boolean edit_society (String society_name) {
		if (!AddOrDeleteSociety.check_fields(textField,textField_2,textField_4,textField_8,textField_9,textField_10)) {
			JOptionPane.showMessageDialog(frame, "Nu a??i completat toate c??mpurile obligatorii, ??ncerca??i din nou..");
			return false;
		}
		
		if (!AddOrDeleteSociety.check_fields_values(textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8, textField_9, textField_10)) {
			AddOrDeleteSociety.show_errors(); 
			return false;
		}
		
		String statement = "UPDATE `societate` SET `Platitor_TVA` = ";
		if (check_box.isSelected())
			statement += "'DA',";
		else
			statement += "'NU',";
		
		String[] my_fields = {"Nume","C.A.E.N.","C.U.I","E.U.I.D.","Nr. ordine","Proprietar","Telefon","Email","Jude??","Localitate","Adres?? efectiv??"};
		
		for (String field : my_fields) {
			if (field.compareTo("Nume") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField.getText();
				statement += "',";
			}
			if (field.compareTo("C.A.E.N.") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField_1.getText();
				statement += "',";
			}
			if (field.compareTo("C.U.I") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField_2.getText();
				statement += "',";
			}
			if (field.compareTo("E.U.I.D.") == 0)
				if (!textField_3.getText().isBlank()) {
					statement += "`";
					statement += field;
					statement += "` = '";
					statement += textField_3.getText();
					statement += "',";
				}
				else {
					statement += "`";
					statement += field;
					statement += "` = NULL,";
				}
			if (field.compareTo("Nr. ordine") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField_4.getText();
				statement += "',";
			}
			if (field.compareTo("Proprietar") == 0)
				if (!textField_5.getText().isBlank()) {
					statement += "`";
					statement += field;
					statement += "` = '";
					statement += textField_5.getText();
					statement += "',";
				}
				else {
					statement += "`";
					statement += field;
					statement += "` = NULL,";
				}
			if (field.compareTo("Telefon") == 0)
				if (!textField_6.getText().isBlank()) {
					statement += "`";
					statement += field;
					statement += "` = '";
					statement += textField_6.getText();
					statement += "',";
				}
				else {
					statement += "`";
					statement += field;
					statement += "` = NULL,";
				}
			if (field.compareTo("Email") == 0)
				if (!textField_7.getText().isBlank()) {
					statement += "`";
					statement += field;
					statement += "` = '";
					statement += textField_7.getText();
					statement += "',";
				}
				else {
					statement += "`";
					statement += field;
					statement += "` = NULL,";
				}
			if (field.compareTo("Jude??") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField_8.getText();
				statement += "',";
			}
			if (field.compareTo("Localitate") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField_9.getText();
				statement += "',";
			}
			if (field.compareTo("Adres?? efectiv??") == 0) {
				statement += "`";
				statement += field;
				statement += "` = '";
				statement += textField_10.getText();
				statement += "',";
			}
		}
		
		statement = statement.substring(0, statement.length() - 1); //taiem ultima virgula
		
		statement += " WHERE `Nume` = '";
		statement += society_name;
		statement += "';";
//		System.out.println(statement);
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			stm.execute();
			item_list.setSelectedItem("");
			JOptionPane.showMessageDialog(society_data_panel, "A??i editat societatea " + society_name + " cu succes!");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(society_data_panel, e);
			return false;
		}
	}
	
	private void add_items_to_list (JComboBox item_list) {
		String statement = "SELECT `Nume` FROM `societate`";
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			ResultSet results = stm.executeQuery(statement);
			if (results.next() == false) {    //daca nu este nicio societate in baza de date, se va iesi din fereastra si intra in meniul principal
				JOptionPane.showMessageDialog(frame, "Nu exist?? nicio societate ??n baza de date, se va reveni la meniul principal..");
				frame.dispose();
				new StartWindow();
			}
			item_list.addItem("");
			do {
				String value = results.getString("Nume");
				item_list.addItem(value);
			} while (results.next());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e);
		}
	}

	@Override
	public void apply_theme() {
		Color c1 = new Color(200,200,200);
		Color c2 = new Color(0,0,0);
		if (!Settings.getTheme_state()) {
			frame.getContentPane().setBackground(new Color(20,20,20));
			
			society_data_panel.setBackground(new Color(35,35,35));
			raport_data_panel.setBackground(new Color(35,35,35));
			
			item_list.setBackground(Color.DARK_GRAY);
			item_list.setForeground(c1);
			
			scrollPane.setBackground(Color.GRAY);
			
			raport_table.setBackground(new Color(150,150,150));
			raport_table.setForeground(Color.WHITE);	
			
			header.setBackground(Color.DARK_GRAY);
			header.setForeground(Color.WHITE);
			
			back_button.setBackground(new Color(50,50,50));
			edit_button.setBackground(new Color(50,50,50));
			add_raport_button.setBackground(new Color(50,50,50));
			delete_raport_button.setBackground(new Color(50,50,50));
			edit_button.setForeground(c1);
			
			textField.setBackground(Color.DARK_GRAY);
			textField.setForeground(c1);
			textField.setCaretColor(Color.WHITE);
			
			textField_1.setBackground(Color.DARK_GRAY);
			textField_1.setForeground(c1);
			textField_1.setCaretColor(Color.WHITE);
			
			textField_2.setBackground(Color.DARK_GRAY);
			textField_2.setForeground(c1);
			textField_2.setCaretColor(Color.WHITE);
			
			textField_3.setBackground(Color.DARK_GRAY);
			textField_3.setForeground(c1);
			textField_3.setCaretColor(Color.WHITE);
			
			textField_4.setBackground(Color.DARK_GRAY);
			textField_4.setForeground(c1);
			textField_4.setCaretColor(Color.WHITE);
			
			textField_5.setBackground(Color.DARK_GRAY);
			textField_5.setForeground(c1);
			textField_5.setCaretColor(Color.WHITE);
			
			textField_6.setBackground(Color.DARK_GRAY);
			textField_6.setForeground(c1);
			textField_6.setCaretColor(Color.WHITE);
			
			textField_7.setBackground(Color.DARK_GRAY);
			textField_7.setForeground(c1);
			textField_7.setCaretColor(Color.WHITE);
			
			textField_8.setBackground(Color.DARK_GRAY);
			textField_8.setForeground(c1);
			textField_8.setCaretColor(Color.WHITE);
			
			textField_9.setBackground(Color.DARK_GRAY);
			textField_9.setForeground(c1);
			textField_9.setCaretColor(Color.WHITE);
			
			textField_10.setBackground(Color.DARK_GRAY);
			textField_10.setForeground(c1);
			textField_10.setCaretColor(Color.WHITE);
			
			textField_2_1.setBackground(Color.DARK_GRAY);
			textField_2_1.setForeground(c1);
			textField_2_1.setCaretColor(Color.WHITE);
			
			textField_2_2.setBackground(Color.DARK_GRAY);
			textField_2_2.setForeground(c1);
			textField_2_2.setCaretColor(Color.WHITE);
			
			textField_2_3.setBackground(Color.DARK_GRAY);
			textField_2_3.setForeground(c1);
			textField_2_3.setCaretColor(Color.WHITE);
			
			label.setForeground(c1);
			sub_label_1.setForeground(c1);
			sub_label_2.setForeground(c1);
			label_1.setForeground(c1);
			label_2.setForeground(c1);
			label_3.setForeground(c1);
			label_4.setForeground(c1);
			label_5.setForeground(c1);
			label_6.setForeground(c1);
			label_7.setForeground(c1);
			label_8.setForeground(c1);
			label_9.setForeground(c1);
			label_10.setForeground(c1);
			label_11.setForeground(c1);
			label_12.setForeground(c1);
			label_2_1.setForeground(c1);
			label_2_2.setForeground(c1);
			label_2_3.setForeground(c1);
			
			check_box.setBackground(new Color(20,20,20));
		} 
		else {
			frame.getContentPane().setBackground(new Color(240,240,240));
			
			society_data_panel.setBackground(new Color(215,215,215));
			raport_data_panel.setBackground(new Color(215,215,215));
			
			item_list.setBackground(new Color(238,238,238));
			item_list.setForeground(Color.BLACK);
			
			scrollPane.setBackground(new Color(238,238,238));
			
			raport_table.setBackground(Color.WHITE);
			raport_table.setForeground(Color.BLACK);
			
			header.setBackground(new Color(238,238,238));
			header.setForeground(Color.BLACK);
			
			back_button.setBackground(new Color(238,238,238));
			edit_button.setBackground(new Color(238,238,238));
			add_raport_button.setBackground(new Color(238,238,238));
			delete_raport_button.setBackground(new Color(238,238,238));
			edit_button.setForeground(Color.BLACK);
			
			textField.setBackground(Color.WHITE);
			textField.setForeground(Color.BLACK);
			textField.setCaretColor(Color.BLACK);
			
			textField_1.setBackground(Color.WHITE);
			textField_1.setForeground(Color.BLACK);
			textField_1.setCaretColor(Color.BLACK);
			
			textField_2.setBackground(Color.WHITE);
			textField_2.setForeground(Color.BLACK);
			textField_2.setCaretColor(Color.BLACK);
			
			textField_3.setBackground(Color.WHITE);
			textField_3.setForeground(Color.BLACK);
			textField_3.setCaretColor(Color.BLACK);
			
			textField_4.setBackground(Color.WHITE);
			textField_4.setForeground(Color.BLACK);
			textField_4.setCaretColor(Color.BLACK);
			
			textField_5.setBackground(Color.WHITE);
			textField_5.setForeground(Color.BLACK);
			textField_5.setCaretColor(Color.BLACK);
			
			textField_6.setBackground(Color.WHITE);
			textField_6.setForeground(Color.BLACK);
			textField_6.setCaretColor(Color.BLACK);
			
			textField_7.setBackground(Color.WHITE);
			textField_7.setForeground(Color.BLACK);
			textField_7.setCaretColor(Color.BLACK);
			
			textField_8.setBackground(Color.WHITE);
			textField_8.setForeground(Color.BLACK);
			textField_8.setCaretColor(Color.BLACK);
			
			textField_9.setBackground(Color.WHITE);
			textField_9.setForeground(Color.BLACK);
			textField_9.setCaretColor(Color.BLACK);
			
			textField_10.setBackground(Color.WHITE);
			textField_10.setForeground(Color.BLACK);
			textField_10.setCaretColor(Color.BLACK);
			
			textField_2_1.setBackground(Color.WHITE);
			textField_2_1.setForeground(Color.BLACK);
			textField_2_1.setCaretColor(Color.BLACK);
			
			textField_2_2.setBackground(Color.WHITE);
			textField_2_2.setForeground(Color.BLACK);
			textField_2_2.setCaretColor(Color.BLACK);
			
			textField_2_3.setBackground(Color.WHITE);
			textField_2_3.setForeground(Color.BLACK);
			textField_2_3.setCaretColor(Color.BLACK);
			
			label.setForeground(c2);
			sub_label_1.setForeground(c2);
			sub_label_2.setForeground(c2);
			label_1.setForeground(c2);
			label_2.setForeground(c2);
			label_3.setForeground(c2);
			label_4.setForeground(c2);
			label_5.setForeground(c2);
			label_6.setForeground(c2);
			label_7.setForeground(c2);
			label_8.setForeground(c2);
			label_9.setForeground(c2);
			label_10.setForeground(c2);
			label_11.setForeground(c2);
			label_12.setForeground(c2);
			label_2_1.setForeground(c2);
			label_2_2.setForeground(c2);
			label_2_3.setForeground(c2);
			
			check_box.setBackground(new Color(240,240,240));
		}
		
	}

	@Override
	public void apply_font() {
		Font font = null, font_2 = null, font_3 = null;
		
		switch (Settings.getFont_state()) {
			case mic:
				font =  new Font("Sans Serif", Font.PLAIN, 10);
				font_2 = new Font("Sans Serif", Font.PLAIN, 12);
				font_3 = new Font("Sans Serif", Font.PLAIN, 14);
				label_11.setText("Adres?? sediu * :                            Localitate");
				label_12.setText("Adres?? efectiv??  ");
				break;
			case mediu:
				font = new Font("Sans Serif", Font.PLAIN, 12);	
				font_2 = new Font("Sans Serif", Font.PLAIN, 14);
				font_3 = new Font("Sans Serif", Font.PLAIN, 16);
				break;
			case mare:
				font = new Font("Sans Serif", Font.PLAIN, 14);	
				font_2 = new Font("Sans Serif", Font.PLAIN, 16);
				font_3 = new Font("Sans Serif", Font.PLAIN, 18);
				label_11.setText("Adres?? sediu * :           Localitate");
				label_12.setText("Adres?? efectiv??  ");
		}
		label.setFont(font_2);
		sub_label_1.setFont(font_2);
		sub_label_2.setFont(font_2);
		label_1.setFont(font);
		label_2.setFont(font);
		label_3.setFont(font);
		label_4.setFont(font);
		label_5.setFont(font);
		label_6.setFont(font);
		label_7.setFont(font);
		label_8.setFont(font);
		label_9.setFont(font);
		label_10.setFont(font);
		label_11.setFont(font);
		label_12.setFont(font);
		label_2_1.setFont(font);
		label_2_2.setFont(font);
		label_2_3.setFont(font);
		item_list.setFont(font);
		textField.setFont(font);
		textField_1.setFont(font);
		textField_2.setFont(font);
		textField_3.setFont(font);
		textField_4.setFont(font);
		textField_5.setFont(font);
		textField_6.setFont(font);
		textField_7.setFont(font);
		textField_8.setFont(font);
		textField_9.setFont(font);
		textField_10.setFont(font);
		edit_button.setFont(font_2);
		header.setFont(font);
		raport_table.setFont(font);
	}
}