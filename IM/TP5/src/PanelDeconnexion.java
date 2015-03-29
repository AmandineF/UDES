import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelDeconnexion extends JPanel {
	public PanelDeconnexion(final JFrame fenBase, final JFrame decoFrame) {
		setLayout(new MigLayout("", "[15%][35%][35%][15%]", "[10%][40%][40%][10%]"));
		setBounds(0, 0, 460, 200);
		
		final JLabel btnDeco = new JLabel(new ImageIcon("./Images/btnDeco.png"));
		add(btnDeco, "cell 2 2,alignx center,aligny top");
		btnDeco.addMouseListener(
				new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
						decoFrame.dispose();
						PanelAwai pa = new PanelAwai(fenBase);
						fenBase.setContentPane(pa);
						fenBase.validate();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
						//btnDeco.setIcon(new ImageIcon( "./images/btnDecoHover.png"));
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
						//btnDeco.setIcon(new ImageIcon( "./images/btnDeco.png"));
					}
				});
		
		final JLabel btnAnnuler = new JLabel(new ImageIcon("./Images/btnAnnuler.png"));
		add(btnAnnuler, "cell 1 2,alignx center,aligny top");
		
		JLabel text = new JLabel("Souhaitez-vous vraiment vous déconnecter ?");
		text.setFont(new Font("Mockup",Font.PLAIN, 16));
		Color grey = new Color(68,68,68); 
		text.setForeground(grey); 
		add(text, "cell 1 1 2 1,alignx left,aligny center");
		btnAnnuler.addMouseListener(
				new MouseAdapter(){
					public void mouseClicked (MouseEvent e){	
						decoFrame.dispose();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
						//btnAnnuler.setIcon(new ImageIcon( "./images/btnAnnulerHover.png"));
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
						//btnAnnuler.setIcon(new ImageIcon( "./images/btnAnnuler.png"));
					}
				});
	}

}
