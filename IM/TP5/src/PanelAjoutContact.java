import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel ajout contact
 * Permet a l'utilisateur d'ajouter un nouveau contact
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Sénécal-Léonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelAjoutContact extends JPanel {

	/**
	 * Constructeur du panel ajout contact
	 * @param fen La fenêtre d'ou l'on vient
	 */
	public PanelAjoutContact(final JFrame fen) {
			setLayout(new MigLayout("", "[20%]0[60%]0[20%]", "[15%]0[15%]0[15%]0[15%]2[15%]0[20%]"));
			
			JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
			this.add(retour,  "cell 0 0 3 1,alignx left,aligny top");
			
			retour.addMouseListener(
					new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
						removeAll();
						PanelBase m = new PanelBase(fen, "Contacts", null);						
						fen.setContentPane(m);
						fen.validate();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
					}
					}  ); 
			
		    class CustomeBorder extends AbstractBorder{
		        @Override
		        public void paintBorder(Component c, Graphics g, int x, int y,
		                int width, int height) {
		            // TODO Auto-generated method stubs
		            super.paintBorder(c, g, x, y, width, height);
		            Graphics2D g2d = (Graphics2D)g;
		            g2d.setStroke(new BasicStroke(12));
		            Color col = new Color(0xECECEC);
		            g2d.setColor(col);
		            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
		        }   
		    }
			
			final JTextField nomField = new JTextField(30);
			TextPrompt tp = new TextPrompt("Nom du contact", nomField);
			nomField.setPreferredSize(new Dimension(200, 60));
			tp.setForeground( Color.GRAY );
			tp.changeStyle(Font.ITALIC);
			tp.setShow(TextPrompt.Show.ALWAYS);
			nomField.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 25, 15, 25))));
			this.add(nomField, "cell 1 1,alignx center,aligny top");
			
			final JTextField prenomField = new JTextField(30);
			prenomField.setPreferredSize(new Dimension(200, 60));
			TextPrompt tp2 = new TextPrompt("Prénom du contact", prenomField);
			tp2.setForeground( Color.GRAY );
			tp2.changeStyle(Font.ITALIC);
			tp2.setShow(TextPrompt.Show.ALWAYS);
			prenomField.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 25, 15, 25))));
			this.add(prenomField, "cell 1 2,alignx center,aligny top");
			
			final JPanel nums = new JPanel();
			nums.setLayout(new MigLayout("gapx 5","",""));
			final JTextField num1 = new JTextField(5);
			num1.setPreferredSize(new Dimension(40, 60));
			TextPrompt tp3 = new TextPrompt("06", num1);
			tp3.setForeground( Color.GRAY );
			tp3.changeStyle(Font.ITALIC);
			tp3.setShow(TextPrompt.Show.ALWAYS);
			num1.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 5, 15, 0))));
			final JTextField num2 = new JTextField(5);
			num2.setPreferredSize(new Dimension(40, 60));
			TextPrompt tp7 = new TextPrompt("18", num2);
			tp7.setForeground( Color.GRAY );
			tp7.changeStyle(Font.ITALIC);
			tp7.setShow(TextPrompt.Show.ALWAYS);
			num2.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 5, 15, 0))));
			final JTextField num3 = new JTextField(5);
			num3.setPreferredSize(new Dimension(40, 60));
			TextPrompt tp6 = new TextPrompt("20", num3);
			tp6.setForeground( Color.GRAY );
			tp6.changeStyle(Font.ITALIC);
			tp6.setShow(TextPrompt.Show.ALWAYS);
			num3.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 5, 15, 0))));
			final JTextField num4 = new JTextField(5);
			num4.setPreferredSize(new Dimension(40, 60));
			TextPrompt tp4 = new TextPrompt("05", num4);
			tp4.setForeground( Color.GRAY );
			tp4.changeStyle(Font.ITALIC);
			tp4.setShow(TextPrompt.Show.ALWAYS);
			num4.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 5, 15, 0))));
			final JTextField num5 = new JTextField(5);
			num5.setPreferredSize(new Dimension(40, 60));
			TextPrompt tp5 = new TextPrompt("12", num5);
			tp5.setForeground( Color.GRAY );
			tp5.changeStyle(Font.ITALIC);
			tp5.setShow(TextPrompt.Show.ALWAYS);
			num5.setBorder(BorderFactory.createCompoundBorder(
	                new CustomeBorder(), 
	                new EmptyBorder(new Insets(15, 5, 15, 0))));
			
			
			nums.add(num1,"cell 0 0,alignx left,aligny top");
			nums.add(num2,"cell 1 0,alignx left,aligny top");
			nums.add(num3,"cell 2 0,alignx left,aligny top");
			nums.add(num4,"cell 3 0,alignx left,aligny top");
			nums.add(num5,"cell 4 0,alignx left,aligny top");
			this.add(nums, "cell 1 3,alignx center,aligny top");
	
			JLabel btnAjout = new JLabel( new ImageIcon( "./images/ajouter.png"));
			this.add(btnAjout, "cell 1 4,alignx center,aligny top");
			btnAjout.addMouseListener(
					new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
						//Non implemente
						removeAll();
						PanelBase m = new PanelBase(fen, "Contacts", null);						
						fen.setContentPane(m);
						fen.validate();
					}
					public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
					public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
					}
					}  );
			
			JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
			this.add(aide,"cell 0 5 3 1, alignx left, aligny bottom");
			
			aide.addMouseListener(
					new MouseAdapter(){
					public void mouseClicked (MouseEvent e){
					JFrame f = new JFrame();
					PanelAide m = new PanelAide(f, "inscription");	
					f.setContentPane(m);
					f.setBounds(100, 100, 500, 180);
					f.setVisible(true);
				}
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			}
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
			}  );
		}

}
