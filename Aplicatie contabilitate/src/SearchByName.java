import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.table.JTableHeader;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JInternalFrame;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextPane; 

public class SearchByName implements ApplyThemeAndFont {

	protected static JFrame frame;
	protected static JTable table;
	protected static JTableHeader header, header_2;
	protected static JScrollPane scrollPane, raport_scrollPane;
	protected static JLabel label_1;
	protected static JButton search_button;
	protected static JButton refresh_button;
	protected static Connection connection;
	protected static JTextField text_field;
	protected static Field[] fields;
	protected static ActionListener button_search_listener;
	protected static KeyListener key_search_listener;
	protected static JPanel panel;
	protected static JLabel label_2_up, label_2_down, label_2_1, label_2_2, label_2_3, label_2_4, label_2_5, label_2_6, label_2_7, label_2_8, label_2_9, label_2_10;
	protected static ColumnCheckBox box_1, box_2, box_3, box_4,box_5, box_6, box_7, box_8, box_9, box_10;
	protected static ActionListener[] listeners, listeners_2;
	protected static BackButton back_button;
	protected static JInternalFrame internalFrame;
	protected static JTable raport_table;
	protected static ListSelectionListener list_listener;
	protected static final int ROW_HEIGHT = 20;
	protected static int nr_of_results;
	protected static String society_name;
	protected static JButton ascending_button, descending_button;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SearchByName();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	public SearchByName() {
		//trebuie comentata linia de cod de mai jos
		//initialize();
	}
	
	public SearchByName(boolean x) {
		if (x)
			initialize();
	}
	
	public static ColumnCheckBox get_box (int i) {
		switch (i) {
			case 1:
				return box_1;
			case 2:
				return box_2;
			case 3:
				return box_3;
			case 4:
				return box_4;
			case 5:
				return box_5;
			case 6:
				return box_6;
			case 7:
				return box_7;
			case 8:
				return box_8;
			case 9:
				return box_9;
			case 10:
				return box_10;
			default:
				return null;
		}
	}
	
	//pentru al i-lea ColumnCheckBox imi returneaza listener-ul specific pentru pagina SearchByName 
	public static ActionListener get_listener (int i) {
		if (get_box(i) == null)
			return null;
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String condition = "`Nume` LIKE '%" + SearchByName.text_field.getText() + "%'";
				if (!get_box(i).isSelected()) 
					fields = remove_item_from_fields(get_box(i).getColumn());
				else 
					fields = add_item_to_fields(get_box(i).getColumn());
				getTable().setModel(populate_table(true, condition, fields));
				getTable().setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
				getTable().getTableHeader().setDefaultRenderer(new MainHeaderRenderer());
			}
		};
	}
	
	//pentru al i-lea ColumnCheckBox imi returneaza listener-ul specific pentru pagina SearchByCUI
	public static ActionListener get_listener_2 (int i) {
		if (get_box(i) == null)
			return null;
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String condition = "`C.U.I.` LIKE '%" + SearchByName.text_field.getText() + "%'";
				if (!get_box(i).isSelected()) 
					fields = remove_item_from_fields(get_box(i).getColumn());
				
				else 
					fields = add_item_to_fields(get_box(i).getColumn());
				getTable().setModel(populate_table(true, condition, fields));
				getTable().setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
				getTable().getTableHeader().setDefaultRenderer(new MainHeaderRenderer());
			}
		};
	}
	
	protected static void connect_to_database (String url, String username, String password) {
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			connection = null;
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	protected static void refresh_internal_frame () {
		raport_scrollPane.setVisible(false);
		ascending_button.setVisible(false);
		descending_button.setVisible(false);
	}
	
	
	protected static void populate_raport_table (String society_name, boolean is_ascending) {
//		internalFrame.getContentPane().add(raport_scrollPane);
		if (society_name == null) {
			refresh_internal_frame();
			return;
		}
		String[] columns = {"An", "CA", "Profit/pierderi"};
		
		
		String[][] data = new String[30][3];
		
		String statement = "SELECT `an`, `cifra_afaceri`, `profit/pierderi` FROM `societate/raport` "
				         + "INNER JOIN (`societate`) ON `societate/raport`.societate = `societate`.id "
				         + "INNER JOIN (`raport`) on `societate/raport`.raport = `raport`.id "
				         + "WHERE `Nume` = '";
		statement += society_name;
		statement += "' ORDER BY `an`";
		if (!is_ascending)
			statement += " DESC;";
		else
			statement += ";";
		
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			ResultSet results = stm.executeQuery(statement);
			internalFrame.setTitle("Raport pentru: " + society_name);
			if (results.next() == false) {
				JOptionPane.showMessageDialog(internalFrame, "Societatea selectat?? nu are salvat niciun raport..");
				refresh_internal_frame();
				return;
			}
			
			//results.beforeFirst();
			
			
			int i = 0;
			do {
				data[i][0] = results.getString("an");
				data[i][1] = results.getString("cifra_afaceri");
				data[i][2] = results.getString("profit/pierderi");
				i ++;
			} while (results.next());
			
			raport_table = new JTable();
			raport_table.setModel(new MyTableModel(data, columns));

			//Thread.sleep(250);
			
			raport_scrollPane.setVisible(true);
			ascending_button.setVisible(true);
			descending_button.setVisible(true);
			
			raport_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			raport_table.setRowHeight(ROW_HEIGHT);
			raport_scrollPane.setViewportView(raport_table);
			header_2 = raport_table.getTableHeader();
			header_2.setReorderingAllowed(false);
			if (!Settings.getTheme_state()) {
				raport_table.setBackground(new Color(150,150,150));
				raport_table.setForeground(Color.WHITE);
				header_2.setBackground(Color.DARK_GRAY);
				header_2.setForeground(Color.WHITE);
			} 
			else {
				raport_table.setBackground(Color.WHITE);
				raport_table.setForeground(Color.BLACK);
				header_2.setBackground(new Color(238,238,238));
				header_2.setForeground(Color.BLACK);
			}
			
			Font font = null, font_2 = null;
			
			switch (Settings.getFont_state()) {
				case mic:
					font =  new Font("Sans Serif", Font.PLAIN, 10);
					font_2 = new Font("Sans Serif", Font.PLAIN, 12);
					break;
				case mediu:
					font = new Font("Sans Serif", Font.PLAIN, 12);	
					font_2 = new Font("Sans Serif", Font.PLAIN, 14);
					break;
				case mare:
					font = new Font("Sans Serif", Font.PLAIN, 14);	
					font_2 = new Font("Sans Serif", Font.PLAIN, 16);
			}
			raport_table.setFont(font);
			header_2.setFont(font);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e);
			return;
		}
	
	}

	protected static MyTableModel populate_table (boolean generate_raport_field, String condition, Field... names) {
		
		Arrays.sort(names, new FieldComparator()); //sortam campurile din tabel: id primul, numele al doilea s.a.m.d.
		
		String[] column_names = new String[names.length];
		int k = 0;
		for (Field obj: names) {
			String name = obj.getName();
			column_names[k ++] = name;
			//System.out.println(name);
		}
		
		String statement = "SELECT ";
		for (Field obj: names) {
			String name = obj.getName();
			if (name != "Platitor_TVA" && name != "C.U.I." && name != "Raport") {
				statement += "`";
				statement += name;
				statement += "`";
				statement += ",";
			}
			if (name == "C.U.I.") { //vrem sa se afiseze C.U.I.-ul complet (cu RO)
				statement += "IF (`Platitor_TVA` = 'DA', CONCAT('RO',`C.U.I`), `C.U.I`) AS `C.U.I.`";
				statement += ",";
			}
		}
		
		statement = statement.substring(0, statement.length() - 1); //tai ultima virgula din propozitie
		statement += " FROM `societate` ";	
		
		if (condition == null) 
			statement += ";";
		else {
			statement += "HAVING ";
			statement += condition;
			statement += ";";
		}
		
		//testare
		//System.out.println(statement);
		
		Object [][] data = new Object[75][names.length];
		ImageIcon icon = new ImageIcon("src/images/right_arrow.png");
		nr_of_results = 0;
		try {
			try  (PreparedStatement stm = connection.prepareStatement(statement)) {
				ResultSet results = stm.executeQuery(statement);
				if (results.next() == false) {
					JOptionPane.showMessageDialog(frame, "C\u0103utarea nu a furnizat niciun rezultat..");
					text_field.setText("");
				}
				else {
					int i = 0, j = 0;	
					do {
						j = 0;
						for (Field field: names) {
							String value = results.getString(field.getName());
							if (value != null) {
								if ( (!generate_raport_field) || (j != 0)  ) {
									data[i][j] = value;
								} else {
									if (j == 0)
										data[i][j] = new JButton(icon);
								}
							}
							else {
								nr_of_results = results.getRow();
								System.out.println("Nr. of results: " + nr_of_results);
							}
							j ++;
						}
						i ++;
					} while (results.next());
				}
	
			}	catch (SQLException e) {
					//JOptionPane.showMessageDialog(null, e.toString());
				JOptionPane.showMessageDialog(frame, "C\u0103utarea nu a furnizat niciun rezultat..");
			}
		} catch (NullPointerException e2) {
			return new MyTableModel(data, column_names);
		}
		return new MyTableModel(data, column_names);
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	
	protected void initialize() {	
		Settings.read_settings_from_file("src/files/settings.txt");

		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("CAUT?? DUP?? NUME");
		frame.setBounds(190, 185, 1175, 451);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label_1 = new JLabel("Caut\u0103 dup\u0103 nume: ");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(27, 39, 157, 16);
		frame.getContentPane().add(label_1);
		
		Cursor hand_cursor = new Cursor(Cursor.HAND_CURSOR);
		
		refresh_button = new JButton(new ImageIcon(SearchByName.class.getResource("/images/refresh_button.png")));
		refresh_button.setBounds(530, 31, 32, 32);
		refresh_button.setToolTipText("Afi??eaz?? toate societa??ile..");
		refresh_button.setCursor(hand_cursor);
		refresh_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					refresh_button.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					text_field.setText("");
					table.setModel(populate_table(true, null, fields));
					table.setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
					table.getTableHeader().setDefaultRenderer(new MainHeaderRenderer());
					internalFrame.setTitle("RAPORT SOCIETATE");
					refresh_internal_frame();
					Thread.sleep(250);
					refresh_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(refresh_button);
		
		search_button = new JButton("CAUT\u0102");
		
		button_search_listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = text_field.getText();
				if (name.isBlank())
					table.setModel(populate_table(true, null, fields));
				else {
					String condition = "`Nume` LIKE '%" + name + "%'";
					table.setModel(populate_table(true, condition, fields));
				}
				table.setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
				table.getTableHeader().setDefaultRenderer(new MainHeaderRenderer());
			}
		};
		
		search_button.addActionListener(button_search_listener);
		search_button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		search_button.setBounds(587, 31, 115, 32);
		search_button.setCursor(hand_cursor);
		frame.getContentPane().add(search_button);
		
		text_field = new JTextField();
		text_field.setBounds(190, 34, 312, 28);
		frame.getContentPane().add(text_field);
		text_field.setColumns(10);
		
		key_search_listener = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == event.VK_ENTER) { //daca apas ENTER
					String name = text_field.getText();
					if (name.isBlank())
						table.setModel(populate_table(true, null, fields));
					else {
						String condition = "`Nume` LIKE '%" + name + "%'";
						table.setModel(populate_table(true, condition, fields));
					}
					table.setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
					table.getTableHeader().setDefaultRenderer(new MainHeaderRenderer());
				}
			}
		};
		
		text_field.addKeyListener(key_search_listener);
		
		fields = new Field[13];
		
		fields[0] = new Field("id");
		fields[1] = new Field("Nume");
		fields[2] = new Field("C.A.E.N.");
		fields[3] = new Field("C.U.I.");
		fields[4] = new Field("E.U.I.D.");
		fields[5] = new Field("Nr. ordine");
		fields[6] = new Field("Nr. angaja??i");
		fields[7] = new Field("Proprietar");
		fields[8] = new Field("Telefon");
		fields[9] = new Field("Email");
		fields[10] = new Field("Jude??");
		fields[11] = new Field("Localitate");
		fields[12] = new Field("Adres?? efectiv??");
		
		back_button = new BackButton();
		back_button.setSize(43, 43);
		back_button.setLocation(1092, 350);
		frame.getContentPane().add(back_button);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(727, 73, 165, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(10, 2, 10, 0));
		
		label_2_1 = new JLabel("C.A.E.N.");
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_1);
		
		box_1 = new ColumnCheckBox(fields[2], 261, 66);
		box_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_1);
		
		label_2_2 = new JLabel("E.U.I.D.");
		label_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_2);
		
		box_2 = new ColumnCheckBox(fields[4], 261, 66);
		box_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_2);
		
		label_2_3 = new JLabel("Nr. ordine");
		label_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_3);
		
		box_3 = new ColumnCheckBox(fields[5], 261, 66);
		box_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_3);
		
		label_2_4 = new JLabel("Nr. angaja??i");
		label_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_4);

		box_4 = new ColumnCheckBox(fields[6],261,66);
		box_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_4);
		
		label_2_5 = new JLabel("Proprietar");
		label_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_5);
		
		box_5 = new ColumnCheckBox(fields[7],261,66);
		box_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_5);
		
		label_2_6 = new JLabel("Telefon");
		label_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_6);
		
		box_6 = new ColumnCheckBox(fields[8],261,66);
		box_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_6);
		
		label_2_7 = new JLabel("Email");
		label_2_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_7);
		
		box_7 = new ColumnCheckBox(fields[9],261,66);
		box_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_7);
		
		label_2_8 = new JLabel("Jude??");
		label_2_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_8);
		
		box_8 = new ColumnCheckBox(fields[10],261,66);
		box_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_8);
		
		label_2_9 = new JLabel("Localitate");
		label_2_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_9);
		
		box_9 = new ColumnCheckBox(fields[11],261,66);
		box_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_9);
		
		label_2_10 = new JLabel("Adres??");
		label_2_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_10);
		
		box_10 = new ColumnCheckBox(fields[12],261,66);
		box_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(box_10);
		
		listeners = new ActionListener[11];
		for (int i = 1; i <= 10; i ++)
			listeners[i] = get_listener(i);
		
		box_1.addActionListener(listeners[1]);
		box_2.addActionListener(listeners[2]);
		box_3.addActionListener(listeners[3]);
		box_4.addActionListener(listeners[4]);
		box_5.addActionListener(listeners[5]);
		box_6.addActionListener(listeners[6]);
		box_7.addActionListener(listeners[7]);
		box_8.addActionListener(listeners[8]);
		box_9.addActionListener(listeners[9]);
		box_10.addActionListener(listeners[10]);
		
		internalFrame = new JInternalFrame("InternalFrame");
		internalFrame.setBounds(920, 31, 215, 300);
		internalFrame.setTitle("RAPORT SOCIETATE");
		frame.getContentPane().add(internalFrame);
		
		raport_scrollPane = new JScrollPane();
		raport_scrollPane.setBounds(6, 6, 191, 217);
		internalFrame.getContentPane().add(raport_scrollPane);
		
		Point d = internalFrame.getLocation();

		internalFrame.setVisible(true);
		internalFrame.setResizable(false);
		internalFrame.getContentPane().setLayout(null);
		
		ascending_button = new JButton("");
		ascending_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populate_raport_table(society_name, true);
			}
		});
		ascending_button.setBounds(21, 233, 55, 25);
		ascending_button.setIcon(new ImageIcon(SearchByName.class.getResource("/images/ascending_button.png")));
		internalFrame.getContentPane().add(ascending_button);
		
		descending_button = new JButton("");
		descending_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populate_raport_table(society_name, false);
			}
		});
		descending_button.setBounds(127, 233, 55, 25);
		descending_button.setIcon(new ImageIcon(SearchByName.class.getResource("/images/descending_button.png")));
		internalFrame.getContentPane().add(descending_button);
		
		internalFrame.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) { }
			@Override
			public void componentResized(ComponentEvent arg0) { }
			@Override
			public void componentMoved(ComponentEvent arg0) {
				internalFrame.setLocation(d);}
			@Override
			public void componentHidden(ComponentEvent arg0) { }
		});
		
		internalFrame.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				internalFrame.setLocation(d);}
			@Override
			public void mousePressed(MouseEvent arg0) {
				internalFrame.setLocation(d);}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				internalFrame.setLocation(d);}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				internalFrame.setLocation(d);}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				internalFrame.setLocation(d);}
		});
		// jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
		connect_to_database("jdbc:mysql://localhost:3306/my_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "bazededate2");
		
		MyTableModel model = populate_table(true, null, fields);
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));		
		table.setRowHeight(ROW_HEIGHT);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ListSelectionModel list_model = table.getSelectionModel();
		list_model.addListSelectionListener( new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				//row = event.getFirstIndex();
				int row = table.getSelectionModel().getMinSelectionIndex();
				int column = table.getSelectedColumn();
				if ( row == -1)
					return;
				//System.out.println("Row: " + row);
				//System.out.println("Column: " + column);
				try {
					if (column != 0) {
						list_model.clearSelection();
						return;
					}
					society_name = (String) table.getValueAt(row, 1);
					System.out.println(society_name);
					if (society_name != null) {
						populate_raport_table(society_name, true);
					}
					//else 
						//refresh_internal_frame();
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(e);
					//aici imi intra inainte dupa ce selectam prima coloana de la o societate
				}
			}
		});
		
		header = table.getTableHeader();
		header.setDefaultRenderer(new MainHeaderRenderer());
		header.setReorderingAllowed(false);
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(675, 320);
		scrollPane.setLocation(27, 74);

		frame.getContentPane().add(scrollPane);
		
		label_2_up = new JLabel("Selecteaz?? / deselecteaz??");
		label_2_up.setHorizontalAlignment(SwingConstants.CENTER);
		label_2_up.setBounds(714, 26, 194, 24);
		frame.getContentPane().add(label_2_up);
		
		label_2_down = new JLabel("c??mpurile din tabel??: ");
		label_2_down.setHorizontalAlignment(SwingConstants.CENTER);
		label_2_down.setBounds(714, 46, 194, 24);
		frame.getContentPane().add(label_2_down);
		
		apply_theme();
		apply_font();
		
		refresh_internal_frame();
	}
	
	protected static Field[] remove_item_from_fields (Field item) {
		int pos = -1;
		for (int i = 0; i < fields.length; i ++)
			if (fields[i].getName() == item.getName())
				pos = i;
		
		for (int i = pos; i < fields.length - 1; i ++)
			fields[i] = fields[i + 1];
		
		Field.setNr_of_fields(Field.getNr_of_fields() - 1);
		
		Field[] aux = new Field[Field.getNr_of_fields()];
		
		for (int i = 0; i < Field.getNr_of_fields(); i ++)
			aux[i] = fields[i];
		
		return aux;
	}
	
	protected static Field[] add_item_to_fields (Field item) {
		
		Field.setNr_of_fields(Field.getNr_of_fields() + 1);
		Field[] aux = new Field[Field.getNr_of_fields()];
		
		for (int i = 0; i < Field.getNr_of_fields() - 1; i ++)
			aux[i] = fields[i];
		
		aux[Field.getNr_of_fields() - 1] = item;
		return aux;
	}

	@Override
	public void apply_theme() {
		Color c1 = new Color(200,200,200);
		Color c2 = new Color(0,0,0);
		if (!Settings.getTheme_state()) {
			frame.getContentPane().setBackground(new Color(20,20,20));
			internalFrame.getContentPane().setBackground(new Color(40,20,25));
			refresh_button.setForeground(c1);
			refresh_button.setBackground(new Color(50,50,50));
			search_button.setForeground(c1);
			search_button.setBackground(new Color(50,50,50));
			back_button.setForeground(c1);
			back_button.setBackground(new Color(50,50,50));
			ascending_button.setForeground(c1);
			ascending_button.setBackground(new Color(50,50,50));
			descending_button.setForeground(c1);
			descending_button.setBackground(new Color(50,50,50));
			
			label_1.setForeground(c1);
			label_2_up.setForeground(c1);
			label_2_down.setForeground(c1);
			label_2_1.setForeground(c1);
			label_2_2.setForeground(c1);
			label_2_3.setForeground(c1);
			label_2_4.setForeground(c1);
			label_2_5.setForeground(c1);
			label_2_6.setForeground(c1);
			label_2_7.setForeground(c1);
			label_2_8.setForeground(c1);
			label_2_9.setForeground(c1);
			label_2_10.setForeground(c1);
			
			panel.setBackground(Color.DARK_GRAY);
			
			text_field.setBackground(Color.DARK_GRAY);
			text_field.setForeground(c1);
			text_field.setCaretColor(Color.WHITE);
			
			scrollPane.setBackground(Color.GRAY);
			
			table.setBackground(new Color(150,150,150));
			table.setForeground(Color.WHITE);	
			
			header.setBackground(Color.DARK_GRAY);
			header.setForeground(Color.WHITE);
		}
		else {
			frame.getContentPane().setBackground(Color.WHITE);
			internalFrame.getContentPane().setBackground(Color.GRAY);
			refresh_button.setForeground(c2);	
			refresh_button.setBackground(new Color(238,238,238));
			search_button.setForeground(c2);	
			search_button.setBackground(new Color(238,238,238));
			back_button.setForeground(c2);	
			back_button.setBackground(new Color(238,238,238));
			ascending_button.setForeground(c2);	
			ascending_button.setBackground(new Color(238,238,238));
			descending_button.setForeground(c2);	
			descending_button.setBackground(new Color(238,238,238));
			
			label_1.setForeground(c2);
			label_2_up.setForeground(c2);
			label_2_down.setForeground(c2);
			label_2_1.setForeground(c2);
			label_2_2.setForeground(c2);
			label_2_3.setForeground(c2);
			label_2_4.setForeground(c2);
			label_2_5.setForeground(c2);
			label_2_6.setForeground(c2);
			label_2_7.setForeground(c2);
			label_2_8.setForeground(c2);
			label_2_9.setForeground(c2);
			label_2_10.setForeground(c2);
			
			panel.setBackground(Color.LIGHT_GRAY);
			
			text_field.setBackground(Color.WHITE);
			text_field.setForeground(Color.BLACK);
			text_field.setCaretColor(Color.BLACK);
			
			scrollPane.setBackground(new Color(238,238,238));
			
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);
			
			header.setBackground(new Color(238,238,238));
			header.setForeground(Color.BLACK);
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
		}
		label_1.setFont(font_2);
		label_2_up.setFont(font_2);
		label_2_down.setFont(font_2);
		label_2_1.setFont(font);
		label_2_2.setFont(font);
		label_2_3.setFont(font);
		label_2_4.setFont(font);
		label_2_5.setFont(font);
		label_2_6.setFont(font);
		label_2_7.setFont(font);
		label_2_8.setFont(font);
		label_2_9.setFont(font);
		label_2_10.setFont(font);

		search_button.setFont(font_3);
		table.setFont(font);
		if (raport_table != null)
			raport_table.setFont(font);
		if (header != null)
			header.setFont(font_2);
		if (header_2 != null)
			header_2.setFont(font_2);
	}
	
	public static JScrollPane getScollPane() {
		return scrollPane;
	}
	
	public static JTable getTable() {
		return table;
	}
	
	public static Field[] getFields() {
		return fields;
	}
}