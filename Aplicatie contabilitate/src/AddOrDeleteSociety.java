import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AddOrDeleteSociety implements ApplyThemeAndFont{

	private static JFrame frame;
	private static JTabbedPane tabbedPane;
	private static JPanel panel_1;
	private static JPanel panel_1_1;
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
	private static BackButton back_button_1;
	private static JButton add_button;
	private static JLabel label, sub_label;
	
	private static Exception[] exceptions;
	
	private static JPanel panel_2;
	private static JScrollPane scrollPane;
	private static JTable table;
	private static JTableHeader header;
	private static MyTableModel model;
	private static JButton delete_button;
	private static BackButton back_button_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AddOrDeleteSociety();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddOrDeleteSociety() {
		initialize();
	}
	
	public static boolean check_fields(Field... fields) {		
		for (Field field : fields)
			if (field.getText().isBlank())
				return false;
		return true;
	}
	
	public static void show_errors() {
		for (Exception e : exceptions) {
			if (e != null)
				JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	public static boolean check_fields_values(Field...fields) {
		if (fields.length != 11)
			return false;
		
		//exceptiile pe care le vom arunca pentru a vedea din ce camp provin erorile..
		exceptions = new Exception[10];
		int k = 0;
		
		//presupunem ca deja am apelat functia check_fields() inainte de a apela aceasta functie
		//deci avem in ipoteza faptul ca toate campurile obligatorii au fost completate
		
		//verificam campul cu numele societatii
		String text_1 = fields[0].getText();
		boolean b_1 = text_1.matches("[A-Z\\.\\s-]{7,}");
		
		if (!b_1)
			exceptions[k ++] = new Exception("C??mpul cu numele trebuie s?? con??in?? doar majuscule, cratim?? sau punct ??i trebuie s?? aibe minim 7 caractere");
		
		//verificam campul C.A.E.N.
		String text_2 = fields[1].getText();
		boolean b_2 = text_2.matches("[0-9]{4}");
		
		if (!b_2)
			exceptions[k ++] = new Exception("C??mpul C.A.E.N. trebuie s?? con??in?? exact 4 cifre..");
			//throw new Exception("C??mpul C.A.E.N. trebuie s?? con??in?? exact 4 cifre..");
		
		//verificam campul C.U.I.(fara 'RO')
		String text_3 = fields[2].getText();
		boolean b_3 = text_3.matches("[0-9]{7,8}");
		
		if (!b_3)
			exceptions[k ++] = new Exception("C??mpul C.U.I. trebuie s?? con??in?? doar 7 sau 8 cifre..");
		
		//verificam campul E.U.I.D.
		String text_4 = fields[3].getText();
		boolean b_4_1, b_4_2;
		if (!text_4.isBlank()) {
			b_4_1 = text_4.matches("[A-Z]+\\.J[0-9]{2}/[0-9]{3,4}/[0-9]{4}");
			b_4_2 = verify_EUID_date(text_4);
		}
		else
			b_4_1 = b_4_2 = true;
		
		boolean b_4 = b_4_1 && b_4_2;
		
		if (!b_4)
			exceptions[k ++] = new Exception("C??mpul E.U.I.D. nu are formatul corect..");
		
		//verificam campul numarului de ordine
		String text_5 = fields[4].getText();
		boolean b_5_1 = text_5.matches("J[0-9]{2}/[0-9]{3,4}/[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}");
		boolean b_5_2 = verify_order_number_date(text_5);
		boolean b_5 = b_5_1 && b_5_2;

			if (!b_5)
			exceptions[k ++] = new Exception("C??mpul num??rului de ordine nu are formatul corect..");
		
		//verificam campul numelui administratorului
		String text_6 = fields[5].getText();
		boolean b_6;
		if (!text_6.isBlank())
			b_6 = text_6.matches("[A-Z]{1}[a-zA-Z\\s-]{3,}");
		else
			b_6 = true;
		
		if (!b_6)
			exceptions[k ++] = new Exception("Numele administratorului trebuie s?? ??nceap?? cu majuscul?? ??i ??n rest poate con??ine litere, cratim?? sau spa??ii\n"
											+ "(minim 3 caractere");
		
		//verificam numarul de telefon
		String text_7 = fields[6].getText();
		boolean b_7;
		if (!text_7.isBlank())
			b_7 = text_7.matches("\\+?[0-9]{11,15}");
		else
			b_7 = true;
		
		if (!b_7)
			exceptions[k ++] = new Exception("C??mpul cu num??rul de telefon poate con??ine ??ntre 11 ??i 15 cifre, op??ional cu + ??nainte..");
		
		//verificam emailul
		String text_8 = fields[7].getText();
		boolean b_8;
		
		if (!text_8.isBlank())
			b_8 = text_8.matches("[^@]+@[a-z\\.]+");
		else
			b_8 = true;
			
		if (!b_8)
			exceptions[k ++] = new Exception("Emailul trebuie s?? fie de forma: exemplu@yahoo.com");
		
		//verific judetul
		String text_9 = fields[8].getText();
		boolean b_9 = text_9.matches("[A-Z]{1}[a-zA-Z\\s-]+");
		
		if (!b_9)
			exceptions[k ++] = new Exception("C??mpul cu jude??ul trebuie s?? ??nceap?? cu majuscul?? ??i ??n rest poate con??ine litere, cratim?? sau spa??ii..");
		
		//verific localitatea
		String text_10 = fields[9].getText();
		boolean b_10 = text_10.matches("[A-Z]{1}[a-zA-Z\\s-]+");
		
		if (!b_10)
			exceptions[k] = new Exception("Localitatea trebuie s?? ??nceap?? cu majuscul?? ??i ??n rest poate con??ine litere, cratim?? sau spa??ii..");

		
		return b_1 && b_2 && b_3 && b_4 && b_5 && b_6 && b_7 && b_8 && b_9 && b_10;
		

	}
	
	public static boolean verify_EUID_date (String EUID) {
		try {
			String[] words = EUID.split("/");
			Integer date = Integer.valueOf(words[2]);
			return (date >= 1500 && date <= 2040 ? true : false);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean verify_order_number_date (String order_number) {
		try {
			String[] words = order_number.split("/");
			String[] date = words[2].split("\\.");
			Integer day = Integer.valueOf(date[0]);
			Integer month = Integer.valueOf(date[1]);
			Integer year = Integer.valueOf(date[2]);
			//verific daca data este una valida
			if (day >= 1 && day <= 31)
				if (month >= 1 && month <= 12)
					if (year >= 1500 && year <= 2040)
						return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	
	}
	
	private void add_society() {
		//daca nu completezi toate campurile obligatorii te scoate din functie
		if (!check_fields(textField,textField_1,textField_2,textField_4,textField_8,textField_9,textField_10)) {
			JOptionPane.showMessageDialog(frame, "Nu a??i completat toate c??mpurile obligatorii, ??ncerca??i din nou..");
			return;
		}
		
		//daca campurile nu sunt completate corect: se afiseaza erorile aferente si se iese din functie
		if (!check_fields_values(textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8, textField_9, textField_10)) {
			show_errors(); 
			return;
		}
		
		String[] data = new String[11];
		String TVA_payer = check_box.isSelected() ? "DA" : "NU";
		
		String[] values_array = get_values_array(textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8, textField_9, textField_10);	
		String[] columns_array = get_columns_array(textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7, textField_8, textField_9, textField_10);
		
		values_array[values_array.length - 1] = TVA_payer;
		columns_array[columns_array.length - 1] = "Platitor_TVA";
		
		String statement = "INSERT INTO `societate` (";
		
		for (int i = 0; i < columns_array.length; i ++) {
			if (columns_array[i].compareTo("C.U.I.") == 0)
				columns_array[i] = "C.U.I";
			statement += "`";
			statement += columns_array[i];
			statement += "`";
			statement += ",";
		}
		
		statement = statement.substring(0, statement.length() - 1);
		statement += ") VALUES (";
		
		for (int i = 0; i < values_array.length; i ++) {
			statement += "'";
			statement += values_array[i];
			statement += "'";
			statement += ",";
		}
		statement = statement.substring(0, statement.length() - 1);
		statement += ");";
		
		System.out.println(statement);
		
		try (PreparedStatement stm = SearchByName.connection.prepareStatement(statement)) {
			stm.execute(statement);
			JOptionPane.showMessageDialog(frame, "Ai adaugat o adresa noua in baza de date..");
			
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
			
			check_box.setSelected(false);
			
			frame.dispose();
			new StartWindow();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e);
			e.printStackTrace();
		} 
	}
	
	private void delete_society() {
		try {
			String statement = "DELETE FROM `societate` WHERE `Nume` = ";
			ListSelectionModel model = table.getSelectionModel();
			PreparedStatement stm = SearchByName.connection.prepareStatement(statement);
			int row = model.getMinSelectionIndex();
			String name = (String) table.getValueAt(row, 0);
			if (name == null) {
				JOptionPane.showMessageDialog(frame, "Nu a??i selectat nicio societate..");
				return;
			}
			statement += "'";
			statement += name;
			statement += "';";
			System.out.println(statement);
			int input = JOptionPane.showConfirmDialog(frame, "E??ti sigur c?? vrei s?? ??tergi societatea selectat???");
			if ((input == JOptionPane.CANCEL_OPTION) || (input == JOptionPane.NO_OPTION) || (input == JOptionPane.CLOSED_OPTION))
				return;
			stm.execute(statement);
			
			String statement_2 = "SELECT `id` from `societate` WHERE `Nume` = '" + name + "';";
			int society_id = -1;
			PreparedStatement stm_2 = SearchByName.connection.prepareStatement(statement_2);
			ResultSet results = stm_2.executeQuery(statement_2);
			while (results.next())
				society_id = results.getInt("id");
			
			String statement_3 = "DELETE FROM `raport` WHERE `id_societate` = '" + String.valueOf(society_id) + "';";
			try (PreparedStatement stm_3 = SearchByName.connection.prepareStatement(statement_3)) {
				stm_3.execute();
				JOptionPane.showMessageDialog(frame, "A??i ??ters cu succes societatea selectat??..");
				frame.dispose();
				new StartWindow();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(scrollPane, e);
				return;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, e);
			return;
		}
	}
	
	private String[] get_values_array (Field... fields) {
		int k = 0;
		for (Field field : fields) 
			if (!field.getText().isBlank())
				k ++;
		String[] return_var = new String[k + 1];
		k = 0;
		for (Field field : fields) 
			if (!field.getText().isBlank())
				return_var[k ++] = field.getText();
		return return_var;
	}
	
	private String[] get_columns_array (Field...fields) {
		int k = 0;
		for (Field field : fields) 
			if (!field.getText().isBlank())
				k ++;
		String[] return_var = new String[k + 1];
		k = 0;
		for (Field field : fields) 
			if (!field.getText().isBlank())
				return_var[k ++] = field.getName();
		return return_var;
	}
	
	private void initialize() {
		
		Settings.read_settings_from_file("src/files/settings.txt");
		
		//tabul de adaugare
		frame = new JFrame();
		frame.setBounds(540, 130, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("ADAUG?? / ??TERGE SOCIETATE");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 47, 464, 501);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);
		
		ImageIcon icon = new ImageIcon("src/images/plus.png");
		ImageIcon icon_2 = new ImageIcon("src/images/minus.png");
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Adaug??", icon, panel_1);
		panel_1.setLayout(null);
	
		panel_1_1 = new JPanel();
		panel_1_1.setBounds(10, 49, 439, 370);
		panel_1.add(panel_1_1);
		panel_1_1.setLayout(new GridLayout(12, 2, 0, 5));
		
		label_1 = new JLabel("Nume *");
		panel_1_1.add(label_1);
		
		textField = new Field("Nume");
		panel_1_1.add(textField);
		textField.setColumns(10);
		
		label_2 = new JLabel("C.A.E.N. *");
		panel_1_1.add(label_2);
		
		textField_1 = new Field("C.A.E.N.");
		panel_1_1.add(textField_1);
		textField_1.setColumns(10);
		
		label_3 = new JLabel("Pl??titor TVA");
		panel_1_1.add(label_3);
		
		check_box = new JCheckBox("");
		check_box.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_1.add(check_box);
		
		label_4 = new JLabel("C.U.I. (f??r?? 'RO') *");
		panel_1_1.add(label_4);
		
		textField_2 = new Field("C.U.I.");
		panel_1_1.add(textField_2);
		textField_2.setColumns(10);
		
		label_5 = new JLabel("E.U.I.D.");
		panel_1_1.add(label_5);
		
		textField_3 = new Field("E.U.I.D.");
		panel_1_1.add(textField_3);
		textField_3.setColumns(10);
		
		label_6 = new JLabel("Num??r de ordine *");
		panel_1_1.add(label_6);
		
		textField_4 = new Field("Nr. ordine");
		panel_1_1.add(textField_4);
		textField_4.setColumns(10);
		
		label_7 = new JLabel("Nume administrator");
		panel_1_1.add(label_7);
		
		textField_5 = new Field("Nume administrator");
		panel_1_1.add(textField_5);
		textField_5.setColumns(10);
		
		label_8 = new JLabel("Telefon");
		panel_1_1.add(label_8);
		
		textField_6 = new Field("Telefon");
		panel_1_1.add(textField_6);
		textField_6.setColumns(10);
		
		label_9 = new JLabel("Email");
		panel_1_1.add(label_9);
		
		textField_7 = new Field("Email");
		panel_1_1.add(textField_7);
		textField_7.setColumns(10);
		
		label_10 = new JLabel("Jude??  ");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1_1.add(label_10);
		
		textField_8 = new Field("Jude??");
		panel_1_1.add(textField_8);
		textField_8.setColumns(10);
		
		label_11 = new JLabel("Adres?? sediu * :                       Localitate");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_1.add(label_11);
		
		textField_9 = new Field("Localitate");
		panel_1_1.add(textField_9);
		textField_9.setColumns(10);
		
		label_12 = new JLabel("Adres?? efectiv??  ");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1_1.add(label_12);
		
		textField_10 = new Field("Adres?? efectiv??");
		panel_1_1.add(textField_10);
		textField_10.setColumns(10);
		
		add_button = new JButton("ADAUG??");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add_society();
			}
		});
		add_button.setBackground(new Color(0, 128, 0));
		add_button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add_button.setBounds(352, 430, 97, 28);
		panel_1.add(add_button);
		
		back_button_1 = new BackButton();
		back_button_1.setLocation(10, 420);
		panel_1.add(back_button_1);
		
		label = new JLabel("Completa??i cu datele societ????ii pe care dori??i s?? o ad??uga??i:\r\n");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 5, 439, 21);
		panel_1.add(label);
		
		sub_label = new JLabel("(c??mpurile notate cu * sunt obilgatorii)");
		sub_label.setBounds(10, 26, 439, 13);
		panel_1.add(sub_label);
		
		//
		SearchByName.connect_to_database("jdbc:mysql://localhost:3306/my_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "bazededate2");
		
		//tabul de stergere
		panel_2 = new JPanel();
		tabbedPane.addTab("??terge", icon_2, panel_2);
		panel_2.setLayout(null);
		
		back_button_2 = new BackButton();
		back_button_2.setLocation(10, 420);
		panel_2.add(back_button_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10,49,439,320);
		panel_2.add(scrollPane);
		
		model = SearchByName.populate_table(false, null, textField, textField_1, textField_2, textField_7, textField_8, textField_9, textField_10);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
	
		header = table.getTableHeader();
		header.setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
		
		delete_button = new JButton("??TERGE");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete_society();
			}
		});
		delete_button.setBackground(new Color(220, 20, 60));
		delete_button.setBounds(352, 430, 97, 28);
		panel_2.add(delete_button);
	
		apply_theme();
		apply_font();
	}

	@Override
	public void apply_theme() {
		Color c1 = new Color(200,200,200);
		Color c2 = new Color(0,0,0);
		if (!Settings.getTheme_state()) {
			frame.getContentPane().setBackground(new Color(35,35,35));
			
			back_button_1.setBackground(new Color(50,50,50));
			back_button_2.setBackground(new Color(50,50,50));
			add_button.setForeground(c1);
			delete_button.setForeground(c1);
			
			panel_1.setBackground(new Color(20,20,20));
			panel_2.setBackground(new Color(20,20,20));
			panel_1_1.setBackground(new Color(20,20,20));
			
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
			
			label.setForeground(c1);
			sub_label.setForeground(c1);
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
			
			check_box.setBackground(new Color(20,20,20));
			
			scrollPane.setBackground(Color.GRAY);
			
			table.setBackground(new Color(150,150,150));
			table.setForeground(Color.WHITE);
			
			header.setBackground(Color.DARK_GRAY);
			header.setForeground(Color.WHITE);
	
		} 
		else {
			frame.getContentPane().setBackground(new Color(240,240,240));
			
			back_button_1.setBackground(new Color(238,238,238));
			back_button_2.setBackground(new Color(238,238,238));
			add_button.setForeground(Color.BLACK);
			delete_button.setForeground(Color.BLACK);
			
			panel_1.setBackground(new Color(240,240,240));
			panel_2.setBackground(new Color(240,240,240));
			panel_1_1.setBackground(new Color(240,240,240));
			
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
			
			label.setForeground(c2);
			sub_label.setForeground(c2);
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
			
			check_box.setBackground(new Color(240,240,240));
			
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
		sub_label.setFont(font_2);
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
		
		add_button.setFont(font);
		delete_button.setFont(font);
		
		table.setFont(font);
		header.setFont(font_2);
		
	}
}