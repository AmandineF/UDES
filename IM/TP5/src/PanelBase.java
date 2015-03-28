import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;


public class PanelBase extends JPanel {

	private JLabel textActuel;
	private JLabel rondActuel;
	private JLabel rondPhoto;
	private JLabel rondCom;
	private JLabel rondContact;
	private JLabel rondDep;
	private JLabel rondCarte;
	private JLabel rondCal;
	private JPanel contenu;
	
	public PanelBase(JFrame fen, String Texte) {
		creerBase(fen);
		modifierBase(Texte);
	}
	
	public void creerBase(JFrame fen) {
		setLayout(new MigLayout("insets 0", "[60]0[1]0[600]", "[60]0[1]0[600]"));
		this.setBounds(0, 0, 400, 460);
		
		JLabel home = new JLabel(new ImageIcon("./Images/home.png"));
		home.setBounds(0, 0, 50, 50);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		home.setBorder(paddingBorder);
		this.add(home,"cell 0 0,alignx center,aligny center");
		
		home.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
					
						PanelMenu m = new PanelMenu(fen);						
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
		
		JPanel barreNoireH = new JPanel();
		FlowLayout flowLayout = (FlowLayout) barreNoireH.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		barreNoireH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		add(barreNoireH, "cell 0 1 3 1,growx");
		
		JPanel barreNoireV = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) barreNoireV.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		barreNoireV.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		add(barreNoireV, "cell 1 0 1 3,growy");
		
		JPanel barreVerticale = new JPanel(new MigLayout("","[60]","[60][60][60][60][60][60]"));
		add(barreVerticale, "cell 0 2,alignx left,aligny top");
		
		JPanel barreHorizontale = new JPanel(new MigLayout("insets 0","[60][540]", "[60]"));
		add(barreHorizontale, "cell 2 0,alignx left,aligny center");
		
		this.rondCom = new JLabel(new ImageIcon("./Images/rondCom.png"));
		rondCom.setBounds(0, 0, 50, 50);
		barreVerticale.add(rondCom,"cell 0 0,alignx left,aligny center");
		this.rondCom.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              modifierBase("Communication"); 
	            } 
	          }); 

		this.rondPhoto = new JLabel(new ImageIcon("./Images/rondPhoto.png"));
		rondPhoto.setBounds(0, 0, 50, 50);
		barreVerticale.add(rondPhoto,"cell 0 1,alignx left,aligny center");
		this.rondPhoto.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              modifierBase("Photos"); 
	            } 
	          }); 

		this.rondCal = new JLabel(new ImageIcon("./Images/rondCal.png"));
		rondCal.setBounds(0, 0, 50, 50);
		barreVerticale.add(rondCal,"cell 0 2,alignx left,aligny center");
		this.rondCal.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              modifierBase("Calendrier"); 
	            } 
	          }); 

		this.rondDep = new JLabel(new ImageIcon("./Images/rondDep.png"));
		rondDep.setBounds(0, 0, 50, 50);
		barreVerticale.add(rondDep,"cell 0 3,alignx left,aligny center");
		this.rondDep.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              modifierBase("Dépenses"); 
	            } 
	          }); 

		this.rondCarte = new JLabel(new ImageIcon("./Images/rondCarte.png"));
		rondCarte.setBounds(0, 0, 50, 50);
		barreVerticale.add(rondCarte,"cell 0 4,alignx left,aligny center");
		this.rondCarte.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              modifierBase("Carte"); 
	            } 
	          }); 
		
		this.rondContact = new JLabel(new ImageIcon("./Images/rondContact.png"));
		rondContact.setBounds(0, 0, 50, 50);
		barreVerticale.add(rondContact,"cell 0 5,alignx left,aligny center");
		this.rondContact.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              modifierBase("Contact"); 
	            } 
	          }); 
		
		this.rondActuel = new JLabel();
		rondActuel.setBounds(0, 0, 50, 50);
		Border paddingBorder1 = BorderFactory.createEmptyBorder(10,10,10,10);
		rondActuel.setBorder(paddingBorder1);
		barreHorizontale.add(rondActuel,"cell 0 0,alignx left,aligny center");
		
		this.textActuel = new JLabel("");
		textActuel.setFont(new Font("Mockup", Font.PLAIN, 26));
		barreHorizontale.add(textActuel,"cell 1 0,alignx left,aligny center");
		
		this.contenu = new JPanel();
		this.add(this.contenu, "cell 2 2,alignx left,aligny center");
	}
	
	
	public void modifierBase(String Texte) {
		this.textActuel.setText(Texte);
		String img = "";
		Icon ic = null;
		
		String imgContact;
		if(Texte == "Contact") {
			imgContact = "./Images/rondContactSec.png";
			img = "./Images/rondContact.png";
			this.contenu = new PanelContact();
		}else{
			imgContact = "./Images/rondContact.png";
		}
		ic = new ImageIcon(imgContact);
		this.rondContact.setIcon(ic);

		String imgCarte;
		if(Texte == "Carte") {
			imgCarte = "./Images/rondCarteSec.png";
			img = "./Images/rondCarte.png";
			this.contenu = new PanelCarte();
		}else{
			imgCarte = "./Images/rondCarte.png";
		}
		ic = new ImageIcon(imgCarte);
		this.rondCarte.setIcon(ic);

		String imgDep;
		if(Texte == "Dépenses") {
			imgDep = "./Images/rondDepSec.png";
			img = "./Images/rondDep.png";
			this.contenu = new PanelDepense();
		}else{
			imgDep = "./Images/rondDep.png";
		}
		ic = new ImageIcon(imgDep);
		this.rondDep.setIcon(ic);

		String imgCal;
		if(Texte == "Calendrier") {
			imgCal = "./Images/rondCalSec.png";
			img = "./Images/rondCal.png";
			this.contenu = new PanelCalendrier();
		}else{
			imgCal = "./Images/rondCal.png";
		}
		ic = new ImageIcon(imgCal);
		this.rondCal.setIcon(ic);

		String imgPhoto;
		if(Texte == "Photos") {
			imgPhoto = "./Images/rondPhotoSec.png";
			img = "./Images/rondPhoto.png";
			this.contenu = new PanelPhoto();
		}else{
			imgPhoto = "./Images/rondPhoto.png";
		}
		ic = new ImageIcon(imgPhoto);
		this.rondPhoto.setIcon(ic);

		String imgCom;
		if(Texte == "Communication") {
			imgCom = "./Images/rondComSec.png";
			img = "./Images/rondCom.png";
			this.contenu = new PanelCommunication();
		}else{
			imgCom = "./Images/rondCom.png";
		}
		ic = new ImageIcon(imgCom);
		this.rondCom.setIcon(ic);
		
		ic = new ImageIcon(img);
		this.rondActuel.setIcon(ic);
		//PAS PROPRE
		Border paddingBorder2 = BorderFactory.createEmptyBorder(-15,-15,-15,-15);
		this.contenu.setBorder(paddingBorder2);
		this.contenu.setBounds(0, 0, 50, 50);
		this.add(this.contenu, "cell 2 2,alignx left,aligny center");
	}

}
