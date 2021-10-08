import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class StartWindow implements ApplyThemeAndFont {

	private static JFrame frame;
	private static JButton button_1;
	private static JButton button_2;
	private static JButton button_3;
	private static JButton button_4;
	private static JButton button_5;
	private static JLabel background_label;
	private static JPanel panel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new StartWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartWindow() {
		initialize();
	}

	private void initialize() {
		Settings.read_settings_from_file("src/files/settings.txt");
		
		frame = new JFrame();
		frame.setBounds(540, 260, 450, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("MENIU PRINCIPAL");
		ImageIcon background =  new ImageIcon("src/images/background.png");
		
		GridLayout layout = new GridLayout(5,1);
		layout.setVgap(10);
		layout.setHgap(10);
		panel = new JPanel(layout);
		panel.setBounds(220, 55, 211, 200);
		panel.setOpaque(false);
		frame.getContentPane().add(panel);

		button_1 = new JButton("Caut\u0103 dup\u0103 nume");
		panel.add(button_1);
		
		button_2 = new JButton("Caută după C.U.I.");
		panel.add(button_2);
		
		button_3 = new JButton("Adaug\u0103 / sterge societate");
		panel.add(button_3);
		
		button_4 = new JButton("Editare societate");
		panel.add(button_4);
		
		button_5 = new JButton("Set\u0103ri");
		panel.add(button_5);
		
		background_label = new JLabel("");
		background_label.setHorizontalAlignment(SwingConstants.LEFT);
		background_label.setBounds(0, -22, 446, 294);
		background_label.setIcon(new ImageIcon(StartWindow.class.getResource("/images/background.png")));
		
		frame.getContentPane().add(background_label);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new Settings();
			}
		});
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new EditSociety();
			}
		});
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new AddOrDeleteSociety();
			}
		});
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new SearchByCUI();
			}
		});
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new SearchByName(true);
			}
		});
		//reinitalizam fields cand intram in meniul principal pt a nu avea duplicate
		SearchByName.fields = null;
		SearchByCUI.fields = null;
		Field.setNr_of_fields(0);
		
		apply_theme();
		apply_font();
		
	}
	
	@Override
	public void apply_theme() {
		Color c1 = new Color(200,200,200);
		if (!Settings.getTheme_state()) {
			frame.getContentPane().setBackground(new Color(20,20,20));
			button_1.setForeground(c1);
			button_2.setForeground(c1);
			button_3.setForeground(c1);
			button_4.setForeground(c1);
			button_5.setForeground(c1);
			
			button_1.setBackground(new Color(50,50,50));
			button_2.setBackground(new Color(50,50,50));
			button_3.setBackground(new Color(50,50,50));
			button_4.setBackground(new Color(50,50,50));
			button_5.setBackground(new Color(50,50,50));
		}
		else {
			frame.getContentPane().setBackground(new Color(255,255,255));
			
			button_1.setForeground(new Color(0,0,0));
			button_2.setForeground(new Color(0,0,0));
			button_3.setForeground(new Color(0,0,0));
			button_4.setForeground(new Color(0,0,0));
			button_5.setForeground(new Color(0,0,0));
			
			button_1.setBackground(new Color(238,238,238));
			button_2.setBackground(new Color(238,238,238));
			button_3.setBackground(new Color(238,238,238));
			button_4.setBackground(new Color(238,238,238));
			button_5.setBackground(new Color(238,238,238));
		}
	}
	
	@Override
	public void apply_font() {
		Font font = null;
		switch (Settings.getFont_state()) {
			case mic:
				font =  new Font("Arial", Font.PLAIN, 10);
				break;
			case mediu:
				font = new Font("Arial", Font.PLAIN, 12);	
				break;
			case mare:
				font = new Font("Arial", Font.PLAIN, 14);	
		}
		button_1.setFont(font);
		button_2.setFont(font);
		button_3.setFont(font);
		button_4.setFont(font);
		button_5.setFont(font);
	}
}
