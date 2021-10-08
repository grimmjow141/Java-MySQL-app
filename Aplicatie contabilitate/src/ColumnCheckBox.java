import javax.swing.JCheckBox;

public class ColumnCheckBox extends JCheckBox {
	
	private Field column;
	
	public ColumnCheckBox(Field column, int x, int y) {
		super("");
		super.setSize(18, 18);
		super.setSelected(true);
		super.setOpaque(false);
		this.column = column;
		super.setLocation(x, y);
	}
	
	public Field getColumn() {
		return column;
	}

}
