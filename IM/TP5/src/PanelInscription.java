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
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Gestion du panel inscription
 * Fenetre pour l'inscription d'un nouvel utilisateur
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
public class PanelInscription extends JPanel {

	/**
	 * Constructeur de panel inscription
	 * @param fen La fenetre d'origine
	 */
	public PanelInscription(final JFrame fen) {
		setLayout(new MigLayout("", "[20%]0[60%]0[20%]", "[15%]0[15%]0[15%]0[15%]2[15%]0[20%]"));
		
		JLabel retour = new JLabel( new ImageIcon( "./images/arrow.png"));
		this.add(retour,  "cell 0 0 3 1,alignx left,aligny top");
		
		retour.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					PanelAwai m = new PanelAwai(fen);						
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
		/**
		 * Classe permettant d'arrondir et de colorer un JTextField
		 */
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
		
		final JTextField pseudoField = new JTextField(30);
		TextPrompt tp = new TextPrompt("Pseudo", pseudoField);
		pseudoField.setPreferredSize(new Dimension(200, 60));
		tp.setForeground( Color.GRAY );
		tp.changeStyle(Font.ITALIC);
		tp.setShow(TextPrompt.Show.ALWAYS);
		pseudoField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(pseudoField, "cell 1 1,alignx center,aligny top");
		
		final JPasswordField passField = new JPasswordField(30);
		passField.setPreferredSize(new Dimension(200, 60));
		TextPrompt tp2 = new TextPrompt("Mot de Passe", passField);
		tp2.setForeground( Color.GRAY );
		tp2.changeStyle(Font.ITALIC);
		tp2.setShow(TextPrompt.Show.ALWAYS);
		passField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(passField, "cell 1 2,alignx center,aligny top");
		
		final JTextField mailField = new JTextField(30);
		mailField.setPreferredSize(new Dimension(200, 60));
		TextPrompt tp3 = new TextPrompt("Addresse mail", mailField);
		tp3.setForeground( Color.GRAY );
		tp3.changeStyle(Font.ITALIC);
		tp3.setShow(TextPrompt.Show.ALWAYS);
		mailField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(), 
                new EmptyBorder(new Insets(15, 25, 15, 25))));
		this.add(mailField, "cell 1 3,alignx center,aligny top");

		JLabel btnInscription = new JLabel( new ImageIcon( "./images/inscription.png"));
		this.add(btnInscription, "cell 1 4,alignx center,aligny top");
		
		btnInscription.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					String strMail = mailField.getText();
					String[] strMailSplit = strMail.split("@");
					if(pseudoField.getText().equals("")){
						JFrame jf = new JFrame("Erreur Pseudo");
						jf.setBounds(0, 0, 400, 400);
						PanelAide jp = new PanelAide(jf, "erreurpseudo");
						jf.setContentPane(jp);
						jf.setVisible(true);
					}else if(String.valueOf(passField.getPassword()).isEmpty()){
						JFrame jf = new JFrame("Erreur Mot de passe");
						jf.setBounds(0, 0, 400, 400);
						PanelAide jp = new PanelAide(jf, "erreurmdp");
						jf.setContentPane(jp);
						jf.setVisible(true);
					}else if(strMailSplit.length != 2){
						JFrame jf = new JFrame("Erreur Mail");
						jf.setBounds(0, 0, 400, 400);
						PanelAide jp = new PanelAide(jf, "erreurmail");
						jf.setContentPane(jp);
						jf.setVisible(true);
					}else{
						String strMail2 = strMailSplit[1];
						if(!strMail2.contains(".")){
							JFrame jf = new JFrame("Erreur Mail");
							jf.setBounds(0, 0, 400, 400);
							PanelAide jp = new PanelAide(jf, "erreurmail");
							jf.setContentPane(jp);
							jf.setVisible(true);
						}else{
							Utilisateur user = new Utilisateur(pseudoField.getText(), String.valueOf(passField.getPassword()));
							UtilisateurManager.getInstance().getUtilisateur().put(pseudoField.getText(), user);
							JFrame jf = new JFrame("Inscription reussie");
							jf.setBounds(0, 0, 400, 400);
							PanelAide jp = new PanelAide(jf, "inscrit");
							jf.setContentPane(jp);
							jf.setVisible(true);
							PanelAwai pawai = new PanelAwai(fen);
							fen.setContentPane(pawai);
							fen.validate();
						}
					}
				}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
				}  );
		JPanel paramAide = new JPanel(new MigLayout("", "[20][20][360]", "[100%]"));
		this.add(paramAide, "cell 0 5 3 1,alignx left,aligny center");
		JLabel parametre = new JLabel( new ImageIcon( "./images/parametre.png"));
		parametre.setToolTipText("Param\u00E8tres");
		paramAide.add(parametre,"cell 0 0, alignx center, aligny bottom");
		parametre.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 PanelParametres m = new PanelParametres(fen, "de l'inscription",null);	
			   	 fen.setContentPane(m);
				 fen.validate();
	         } 
		     public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
		     }
		  	 public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
			 } 
		 });

		
		JLabel aide = new JLabel( new ImageIcon( "./images/aide.png"));
		aide.setToolTipText("Aide");
		paramAide.add(aide,"cell 1 0, alignx center, aligny bottom");
		
		aide.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
				JFrame f = new JFrame();
				PanelAide m = new PanelAide(f, "inscription");	
				f.setContentPane(m);
				f.setBounds(100, 100, 500, 250);
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
