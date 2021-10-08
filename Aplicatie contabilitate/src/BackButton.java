import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BackButton extends JButton {
	BackButton() {
		super.setSize(43, 43);
		super.setToolTipText("Înapoi..");
		super.setCursor(new Cursor(Cursor.HAND_CURSOR));
		super.setIcon(new ImageIcon(SearchByName.class.getResource("/images/back_button.png")));
		super.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Container frame = getParent();
				do {
					frame = frame.getParent();
				} while (!(frame instanceof JFrame));
				((JFrame) frame).dispose();
				StartWindow start_window = new StartWindow();
			}
		});
	}
}
