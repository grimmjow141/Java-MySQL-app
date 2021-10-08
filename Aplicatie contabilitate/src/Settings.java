import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class Settings implements ApplyThemeAndFont {
	
	private static JFrame frame;
	private static JLabel label_1;
	private static JLabel label_2;
	private static JLabel label_1_1;
	private static JLabel label_1_2;
	private static JLabel label_2_1;
	private static JLabel label_2_2;
	private static JLabel label_2_3;
	private static JSlider slider;
	private static JToggleButton toggle_button;
	private static BackButton back_button;
	
	public static enum font_state {
		mic,
		mediu,
		mare
	};
	
	private static boolean theme_state;
	private static Settings.font_state font_state;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings window = new Settings();
					//read_settings_from_file("src/files/settings.txt");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Settings() {
		initialize();
	}
	
	@Override
	public void apply_theme () {
		Color c = new Color(200,200,200);
		if (!theme_state) {
			frame.getContentPane().setBackground(new Color(20,20,20));
			
			label_1.setForeground(c);
			label_2.setForeground(c);
			label_1_1.setForeground(c);
			label_1_2.setForeground(c);
			label_2_1.setForeground(c);
			label_2_2.setForeground(c);
			label_2_3.setForeground(c);
			
			toggle_button.setBackground(new Color(50,50,50));
			back_button.setBackground(new Color(50,50,50));
			slider.setBackground(Color.DARK_GRAY);
		}
		else {
			frame.getContentPane().setBackground(new Color(255,255,255));
			
			label_1.setForeground(new Color(0,0,0));
			label_2.setForeground(new Color(0,0,0));
			label_1_1.setForeground(new Color(0,0,0));
			label_1_2.setForeground(new Color(0,0,0));
			label_2_1.setForeground(new Color(0,0,0));
			label_2_2.setForeground(new Color(0,0,0));
			label_2_3.setForeground(new Color(0,0,0));
			
			toggle_button.setBackground(new Color(238,238,238));
			back_button.setBackground(new Color(238,238,238));
			slider.setBackground(new Color(238,238,238));
		}	

	}
	
	@Override
	public void apply_font () {
		Font font = null;
		switch (font_state) {
			case mic:
				font =  new Font("Tahoma", Font.PLAIN, 10);
				break;
			case mediu:
				font = new Font("Tahoma", Font.PLAIN, 12);	
				break;
			case mare:
				font = new Font("Tahoma", Font.PLAIN, 14);	
		}
		label_1.setFont(font);
		label_2.setFont(font);
		label_1_1.setFont(font);
		label_1_2.setFont(font);
		label_2_1.setFont(font);
		label_2_2.setFont(font);
		label_2_3.setFont(font);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		read_settings_from_file("src/files/settings.txt");
		
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("SETĂRI");
		frame.setBounds(600, 260, 247, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//		GenerateRaportRenderer btn = new GenerateRaportRenderer(new ImageIcon("src/images/right_arrow.png"));
//		btn.setLocation(30, 40);
//		frame.getContentPane().add(btn);		
		
		label_1 = new JLabel("Tem\u0103: ");
		label_1.setBounds(25, 70, 45, 13);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("Font: ");
		label_2.setBounds(25, 145, 45, 13);
		frame.getContentPane().add(label_2);
		
		toggle_button = new JToggleButton("");
		toggle_button.setBounds(108, 67, 45, 21);
		frame.getContentPane().add(toggle_button);
		
		toggle_button.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent event) {
				if (!toggle_button.isSelected())
					Settings.theme_state = false;
				else
					Settings.theme_state = true;
				apply_theme();
				write_settings_to_file("src/files/settings.txt");
			}
		});
		
		back_button = new BackButton();
		back_button.setLocation(10, 212);
		frame.getContentPane().add(back_button);
		
		slider = new JSlider();
		slider.setMaximum(2);
		switch (Settings.font_state) {
			case mic:
				slider.setValue(0);
				break;
			case mediu:
				slider.setValue(1);
				break;
			case mare:
				slider.setValue(2);
		}
		slider.setBounds(75, 145, 101, 13);
		frame.getContentPane().add(slider);
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				switch (slider.getValue()) {
					case 0:
						Settings.font_state = font_state.mic;
						break;
					case 1:
						Settings.font_state = font_state.mediu;
						break;
					case 2:
						Settings.font_state = font_state.mare;
				}
				apply_font();
				write_settings_to_file("src/files/settings.txt");
			}
		});
		
		label_2_1 = new JLabel("mic");
		label_2_1.setBounds(68, 165, 22, 13);
		frame.getContentPane().add(label_2_1);
		
		label_2_2 = new JLabel("mediu");
		label_2_2.setBounds(110, 165, 43, 13);
		frame.getContentPane().add(label_2_2);
		
		label_2_3 = new JLabel("mare");
		label_2_3.setBounds(162, 165, 36, 13);
		frame.getContentPane().add(label_2_3);
		
		label_1_1 = new JLabel("light");
		label_1_1.setBounds(75, 70, 32, 13);
		frame.getContentPane().add(label_1_1);
		
		label_1_2 = new JLabel("dark");
		label_1_2.setBounds(157, 70, 32, 13);
		frame.getContentPane().add(label_1_2);
	
		apply_theme();
		apply_font();
		
	}
	
	public static void read_settings_from_file (String file_name) {
		
		try {
			Scanner scanner = new Scanner(new File(file_name));
			try {
				String a = scanner.next();
				String b = scanner.next();
				if (!Settings.verify_values(a, b) )
					throw new Exception("Bad values");
				if (a.compareTo("L") == 0) 
					Settings.theme_state = true; //light theme => true
				else
					Settings.theme_state = false;//dark theme => false
				
				if (b.compareTo("1") == 0)
					Settings.font_state = Settings.font_state.mic;
				else if (b.compareTo("2") == 0)
					Settings.font_state = Settings.font_state.mediu;
				else
					Settings.font_state = Settings.font_state.mare;
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Fișierul setărilor nu are formatul corect..");
				System.exit(0);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fișierul nu există..");
			System.exit(0);
		}
	}
	
	public static void write_settings_to_file (String file_name) {
		try {
			FileWriter writer = new FileWriter(new File(file_name));
			String value = "";
			if (Settings.theme_state)
				value += "L ";
			else
				value += "D ";
			if (Settings.font_state == Settings.font_state.mic)
				value += "1";
			else if (Settings.font_state == Settings.font_state.mediu)
				value += "2";
			else
				value += "3";
			//System.out.println(value);
			writer.write("");
			writer.write(value);
			writer.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fișierul cu setările aplicației nu poate fi găsit..");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Eroare la scrierea în fișierul cu setările aplicației..");
		}
	}
	
	public static boolean verify_values (String a, String b) {
		if ( (a.compareTo("L") == 0 || a.compareTo("D") == 0) && (b.compareTo("1") == 0 || b.compareTo("2") == 0 || b.compareTo("3") == 0) )
			return true;
		return false;
	}
	
	public static font_state getFont_state() {
		return font_state;
	}
	
	public static boolean getTheme_state() {
		return theme_state;
	}
}
