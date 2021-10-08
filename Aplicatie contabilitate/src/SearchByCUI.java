import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchByCUI extends SearchByName {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SearchByCUI();
			}
		});
	}

	public SearchByCUI() {
		//super.frame.dispose();
		initialize();
	}

	protected void initialize() {
		super.initialize();
		frame.setTitle("CAUTĂ DUPĂ C.U.I.");
		label_1.setText("Caut\u0103 dup\u0103 C.U.I.: ");
		
		search_button.removeActionListener(button_search_listener);
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = text_field.getText();
				if (name.isBlank())
					getTable().setModel(populate_table(true, null, fields));
				else {
					String condition = "`C.U.I.` LIKE '%" + name + "%'";
					getTable().setModel(populate_table(true, condition, fields));
				}
				getTable().setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
			}
		});
		
		text_field.removeKeyListener(key_search_listener);
		text_field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed (KeyEvent event) {
				if (event.getKeyCode() == event.VK_ENTER) { //daca apas ENTER
					String name = text_field.getText();
					if (name.isBlank())
						table.setModel(populate_table(true, null, fields));
					else {
						String condition = "`C.U.I.` LIKE '%" + name + "%'";
						table.setModel(populate_table(true, condition, fields));
					}
					table.setDefaultRenderer(Object.class, new GenerateRaportRenderer(nr_of_results));
					table.getTableHeader().setDefaultRenderer(new MainHeaderRenderer());
				}
			}
		});
		
		box_1.removeActionListener(listeners[1]);
		box_2.removeActionListener(listeners[2]);
		box_3.removeActionListener(listeners[3]);
		box_4.removeActionListener(listeners[4]);
		box_5.removeActionListener(listeners[5]);
		box_6.removeActionListener(listeners[6]);
		box_7.removeActionListener(listeners[7]);
		box_8.removeActionListener(listeners[8]);
		box_9.removeActionListener(listeners[9]);
		box_10.removeActionListener(listeners[10]);
		
		box_1.addActionListener(get_listener_2(1));
		box_2.addActionListener(get_listener_2(2));
		box_3.addActionListener(get_listener_2(3));
		box_4.addActionListener(get_listener_2(4));
		box_5.addActionListener(get_listener_2(5));
		box_6.addActionListener(get_listener_2(6));
		box_7.addActionListener(get_listener_2(7));
		box_8.addActionListener(get_listener_2(8));
		box_9.addActionListener(get_listener_2(9));
		box_10.addActionListener(get_listener_2(10));
	}

}