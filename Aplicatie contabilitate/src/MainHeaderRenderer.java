import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.table.TableCellRenderer;

public class MainHeaderRenderer implements TableCellRenderer{
	private Font font;
	
	MainHeaderRenderer() {
		super();
		switch (Settings.getFont_state()) {
			case mic:
				font = new Font("Sans Serif", Font.PLAIN, 12);
				break;
			case mediu:
				font =  new Font("Sans Serif", Font.PLAIN, 14);
				break;
			case mare:
				font =  new Font("Sans Serif", Font.PLAIN, 16);
				break;
		}
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object object, boolean arg2, boolean arg3, int row, int column) {
		String value = table.getColumnName(column);
		JLabel cell = new JLabel(value);
		ToolTipManager.sharedInstance().setInitialDelay(100);
		ToolTipManager.sharedInstance().setDismissDelay(2000);
		cell.setFont(font);
		cell.setHorizontalAlignment(SwingConstants.CENTER);
		cell.setToolTipText(value);
		if (column == 0) {
			cell.setToolTipText("Generează raport..");
			cell.setText("");
			cell.setIcon(new ImageIcon("src/images/raport_icon.png"));
		}
		if (!Settings.getTheme_state()) {
			cell.setBackground(Color.DARK_GRAY);
			cell.setForeground(Color.WHITE);
			cell.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.DARK_GRAY));
		}
		else {
			cell.setBackground(new Color(238,238,238));
			cell.setForeground(Color.BLACK);
			cell.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.WHITE));
		}

		return cell;

	}
}
