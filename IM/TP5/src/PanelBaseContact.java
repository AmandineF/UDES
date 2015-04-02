import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelBaseContact extends JPanel {

	public PanelBaseContact(final JFrame fen) {
		setLayout(new MigLayout("insets 0", "[60]0[1]0[399]", "[60]0[1]0[439]"));
		this.setBounds(0, 0, 400, 460);
		
		JLabel retour = new JLabel(new ImageIcon("./Images/arrow.png"));
		retour.setBounds(0, 0, 50, 50);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		retour.setBorder(paddingBorder);
		this.add(retour,"cell 0 0,alignx center,aligny center");
		retour.setToolTipText("Retour menu principal");
		retour.addMouseListener(
			new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						//PanelMenu m = new PanelMenu(fen);						
						//fen.setContentPane(m);
						//fen.validate();
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
			}
		);
		
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
		
		JPanel barreVerticale = new JPanel(new MigLayout("","","[14%][14%][14%][14%][14%][14%][16%]"));
		add(barreVerticale, "cell 0 2,alignx left,aligny top");
		
		JPanel barreHorizontale = new JPanel(new MigLayout("insets 0","[60][339][40]", ""));
		add(barreHorizontale, "cell 2 0,alignx left,aligny center");
		
		final JLabel rondCom = new JLabel(new ImageIcon("./Images/rondCom.png"));
		rondCom.setBounds(0, 0, 50, 50);
		Border paddingBorderCo = BorderFactory.createEmptyBorder(0,7,0,0);
		rondCom.setBorder(paddingBorderCo);
		barreVerticale.add(rondCom,"cell 0 0,alignx left,aligny center");
		rondCom.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Communication");						
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
	
		this.rondPhoto = new JLabel(new ImageIcon("./Images/rondPhoto.png"));
		rondPhoto.setBounds(0, 0, 50, 50);
		Border paddingBorderP = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondPhoto.setBorder(paddingBorderP);
		barreVerticale.add(rondPhoto,"cell 0 1,alignx left,aligny center");
		this.rondPhoto.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Photos");						
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
	
		this.rondCal = new JLabel(new ImageIcon("./Images/rondCal.png"));
		rondCal.setBounds(0, 0, 50, 50);
		Border paddingBorderCal = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondCal.setBorder(paddingBorderCal);
		barreVerticale.add(rondCal,"cell 0 2,alignx left,aligny center");
		this.rondCal.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) {
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Calendrier");						
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
	
		this.rondDep = new JLabel(new ImageIcon("./Images/rondDep.png"));
		rondDep.setBounds(0, 0, 50, 50);
		Border paddingBorderD = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondDep.setBorder(paddingBorderD);
		barreVerticale.add(rondDep,"cell 0 3,alignx left,aligny center");
		this.rondDep.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Dépenses");						
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
	
		this.rondCarte = new JLabel(new ImageIcon("./Images/rondCarte.png"));
		rondCarte.setBounds(0, 0, 50, 50);
		Border paddingBorderCa = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondCarte.setBorder(paddingBorderCa);
		barreVerticale.add(rondCarte,"cell 0 4,alignx left,aligny center");
		this.rondCarte.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Carte");						
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
		
		this.rondContact = new JLabel(new ImageIcon("./Images/rondContact.png"));
		rondContact.setBounds(0, 0, 50, 50);
		Border paddingBorderC = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondContact.setBorder(paddingBorderC);
		barreVerticale.add(rondContact,"cell 0 5,alignx left,aligny center");
		this.rondContact.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Contacts");						
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
		
		this.rondActuel = new JLabel();
		rondActuel.setBounds(0, 0, 50, 50);
		Border paddingBorder1 = BorderFactory.createEmptyBorder(10,10,10,10);
		rondActuel.setBorder(paddingBorder1);
		barreHorizontale.add(rondActuel,"cell 0 0,alignx left,aligny center");
		
		this.textActuel = new JLabel("");
		textActuel.setFont(new Font("Mockup", Font.PLAIN, 26));
		Color grey = new Color(68,68,68); 
		textActuel.setForeground(grey); 
		barreHorizontale.add(textActuel,"cell 1 0,alignx left,aligny center");
		
		this.contenu = new JPanel();
	
		
		this.partage = new JLabel();
		Border padding = BorderFactory.createEmptyBorder(20,20,20,20);
		this.partage.setBorder(padding);
		partagelist = new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              //modifierBase(fen, "Contacts"); 
	            } 
	          public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
		  		public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
	          }; 
	    
	    JPanel iconesBas = new JPanel();
			barreVerticale.add(iconesBas, "cell 0 6, alignx left, aligny bottom");
			this.param = new JLabel(new ImageIcon("./Images/cogSmall.png"));
			this.param.setToolTipText("Param\u00E8tres");
			this.param.setBounds(0, 0, 10, 10);
			iconesBas.add(param);
			
			this.aide = new JLabel(new ImageIcon("./Images/aideSmall.png"));
			this.aide.setToolTipText("Aide");
			aide.setBounds(0, 0, 10, 10);
			iconesBas.add(aide);
			this.aideListener = new MouseAdapter(){};
			
			JLabel deco = new JLabel(new ImageIcon("./Images/decoSmall.png"));
			deco.setBounds(0, 0, 10, 10);
			deco.setToolTipText("D\u00E9connexion");
			deco.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	   JFrame decoFrame = new JFrame();	
	        	   decoFrame.setBounds(0, 0, 460, 200);
	        	   decoFrame.setVisible(true);
	        	   decoFrame.setResizable(false);
				   PanelDeconnexion m = new PanelDeconnexion(fen,decoFrame);
	        	   decoFrame.setContentPane(m);
	            } 
		       public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
		  		public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
				}
		          });
			iconesBas.add(deco);
			this.contentScrollPane = new JScrollPane(); 
	}

}
