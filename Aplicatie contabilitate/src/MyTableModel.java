import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	public MyTableModel (Object[][] data, Object[] columns) {
		super(data, columns);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) { //nu vreau ca casutele din tabele sa fie editabile
		return false;
	}

	
}
