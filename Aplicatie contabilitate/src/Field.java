import javax.swing.JTextField;

public class Field extends JTextField {
	
	private String name;
	private int order;    //cu cat este valoarea mai mica, cu atat are prioritate (este mai in stanga) in tabel 

	private final static String[] my_fields = {"id", "Nume", "C.U.I.","Platitor_TVA", "C.A.E.N.", 
											   "E.U.I.D.", "Nr. ordine", "Nr. angajați", "Proprietar",
											   "Telefon", "Email", "Județ", "Localitate", "Adresă efectivă"};
	private static int nr_of_fields = 0;
	
	Field (String name) {
		
		if (verify_name(name)) {
			nr_of_fields ++;
			this.name = name;
			
			int k = 0;
			for (String s: my_fields)
				if (s.compareTo(name) != 0)
					k ++;
				else
					break;
			this.order = k;
		}
		else {
			name = null;
			order = -1;
		}
	}
	
	public boolean verify_name (String name) {
		for (String s: my_fields)
			if (s.compareTo(name) == 0)
				return true;
		return false;
	}
	
	public String getName() {
		return name;
	}

	public int getOrder() {
		return order;
	}
	
	public static void setNr_of_fields(int nr_of_fields) {
		Field.nr_of_fields = nr_of_fields;
	}

	public static int getNr_of_fields() {
		return nr_of_fields;
	}

	public static String[] getMy_fields() {
		return my_fields;
	}

}
