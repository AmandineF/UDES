/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					MainFrame frame = new MainFrame("Awaï");
					frame.setTitle("Awaï");
					//contentPane = new PanelAwai(frame);
					contentPane = new PanelAwai(frame);
					frame.setContentPane(contentPane);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @param string 
	 */
	public MainFrame(String string) {
		//super(string);
		setIconImage(Toolkit.getDefaultToolkit().getImage("airplane.png")); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}
