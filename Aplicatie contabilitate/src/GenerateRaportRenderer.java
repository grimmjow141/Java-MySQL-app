import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class GenerateRaportRenderer implements TableCellRenderer {
	
	private JButton button;
	private int nr_of_results;
	
	private Font font;
	private Color c1, c2;
	
	public GenerateRaportRenderer(int nr_of_results) {
		super();
		//super.setOpaque(false);
		this.nr_of_results = nr_of_results;
		button = new JButton("");
		button.setIcon(new ImageIcon(SearchByName.class.getResource("/images/right_arrow.png")));
		button.setSize(16, 16);
		c1 = Color.WHITE;
		c2 = Color.BLACK;
		switch (Settings.getFont_state()) {
			case mic:
				font = new Font("Sans Serif", Font.PLAIN, 10);
				break;
			case mediu:
				font =  new Font("Sans Serif", Font.PLAIN, 12);
				break;
			case mare:
				font =  new Font("Sans Serif", Font.PLAIN, 14);
				break;
		}
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object object, boolean is_selected, boolean has_focus, int row, int column) {
		if (row >= nr_of_results)
			return null;
		if (column == 0) {
			return button;
		}
		//adaptez label-ul returnat la tema si font
		String value = (String) table.getValueAt(row, column);
		JLabel cell = new JLabel(value, SwingConstants.CENTER);
		cell.setFont(font);
		if (!Settings.getTheme_state()) 
			cell.setForeground(c1);
		else
			cell.setForeground(c2);
		return cell;
	}
}
 