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
import javax.swing.border.Border;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PanelBaseContact extends JPanel {

	public PanelBaseContact(final JFrame fen, final Contact contact, final String Texte) {
		setLayout(new MigLayout("insets 0", "[60]0[1]0[399]", "[60]0[1]0[439]"));
		this.setBounds(0, 0, 400, 460);
		JLabel rondActuel = new JLabel();
		JPanel contenu = new JPanel();
		JLabel retour = new JLabel(new ImageIcon("./Images/arrow.png"));
		retour.setBounds(0, 0, 50, 50);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		retour.setBorder(paddingBorder);
		this.add(retour,"cell 0 0,alignx center,aligny center");
		retour.setToolTipText("Retour menu principal");
		retour.addMouseListener(
			new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						PanelBase m = new PanelBase(fen, "Contacts", "");						
						fen.setContentPane(m);
						fen.validate();
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
		
		JPanel barreHorizontale = new JPanel(new MigLayout("","[250][150]", ""));
		add(barreHorizontale, "cell 2 0,alignx left,aligny center");
		
		final JLabel rondInfo = new JLabel();
		if(Texte.equals("Informations")){
			rondInfo.setIcon(new ImageIcon("./Images/rondInfoSec.png"));
			rondActuel.setIcon(new ImageIcon("./Images/rondInfoContact.png"));
		}else{
			rondInfo.setIcon(new ImageIcon("./Images/rondInfo.png"));
			rondInfo.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Informations");						
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
			rondInfo.setToolTipText("Acc\u00E8der aux informations de " + contact.getNom() + ".");
		}
		rondInfo.setBounds(0, 0, 50, 50);
		Border paddingBorderCo = BorderFactory.createEmptyBorder(0,7,0,0);
		rondInfo.setBorder(paddingBorderCo);
		barreVerticale.add(rondInfo,"cell 0 0,alignx left,aligny center");
		
	
		final JLabel rondPhoto = new JLabel();
		if(contact.getAccesPhoto()) {
			if(Texte.equals("Photos")){
				rondPhoto.setIcon(new ImageIcon("./Images/rondPhotoSec.png"));
				rondActuel.setIcon(new ImageIcon("./Images/rondPhoContact.png"));
				contenu = new PanelPhoto();
			}else{
				rondPhoto.setIcon(new ImageIcon("./Images/rondPhoto.png"));	
				rondPhoto.addMouseListener(new MouseAdapter() { 
			          public void mousePressed(MouseEvent me) { 
			        	  removeAll();
			        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Photos");						
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
				rondPhoto.setToolTipText("Acc\u00E8der aux Photos de " + contact.getNom() + ".");
			}
			
		} else {
			rondPhoto.setIcon(new ImageIcon("./Images/rondPhotoGris.png"));
			rondPhoto.setToolTipText("Vous n'avez pas acc\u00E8s aux Photos de " + contact.getNom() + ".");
		}
		rondPhoto.setBounds(0, 0, 50, 50);
		Border paddingBorderP = BorderFactory.createEmptyBorder(0,7,0,0);
		rondPhoto.setBorder(paddingBorderP);
		barreVerticale.add(rondPhoto,"cell 0 1,alignx left,aligny center");
	
		final JLabel rondCal = new JLabel();
		if(contact.getAccesCal()) {
			if(Texte.equals("Calendrier")){
				rondCal.setIcon(new ImageIcon("./Images/rondCalSec.png"));
				rondActuel.setIcon(new ImageIcon("./Images/rondCalContact.png"));
				contenu = new PanelCalendrier();
			}else{
				rondCal.setIcon(new ImageIcon("./Images/rondCal.png"));
			
			rondCal.setToolTipText("Acc\u00E8der au Calendrier de " + contact.getNom() + ".");
			rondCal.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) {
		        	  removeAll();
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Calendrier");						
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
			}
		}else{
			rondCal.setIcon(new ImageIcon("./Images/rondCalGris.png"));
			rondCal.setToolTipText("Vous n'avez pas acc\u00E8s au Calendrier de " + contact.getNom() + ".");
		}
		rondCal.setBounds(0, 0, 50, 50);
		Border paddingBorderCal = BorderFactory.createEmptyBorder(0,7,0,0);
		rondCal.setBorder(paddingBorderCal);
		barreVerticale.add(rondCal,"cell 0 2,alignx left,aligny center");
		
	
		final JLabel rondDep = new JLabel();
		if(contact.getAccesDep()) {
			if(Texte.equals("Dépenses")){
				rondDep.setIcon(new ImageIcon("./Images/rondDepSec.png"));
				rondActuel.setIcon(new ImageIcon("./Images/rondDepContact.png"));
				contenu = new PanelDepense();
			}else{
				rondDep.setIcon(new ImageIcon("./Images/rondDep.png"));
			
			rondDep.setToolTipText("Acc\u00E8der aux Dépenses de " + contact.getNom() + ".");
			rondDep.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) {
		        	  removeAll();
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Dépenses");						
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
			}
		}else{
			rondDep.setIcon(new ImageIcon("./Images/rondDepGris.png"));
			rondDep.setToolTipText("Vous n'avez pas acc\u00E8s aux Dépenses de " + contact.getNom() + ".");
		}
		rondDep.setBounds(0, 0, 50, 50);
		Border paddingBorderD = BorderFactory.createEmptyBorder(0,7,0,0);
		rondDep.setBorder(paddingBorderD);
		barreVerticale.add(rondDep,"cell 0 3,alignx left,aligny center");
		
	
		final JLabel rondCarte = new JLabel();
		if(contact.getAccesDep()) {
			if(Texte.equals("Carte")){
				rondActuel.setIcon(new ImageIcon("./Images/rondCarContact.png"));
				rondCarte.setIcon(new ImageIcon("./Images/rondCarteSec.png"));
				contenu = new PanelCarte();
			} else {
				rondCarte.setIcon(new ImageIcon("./Images/rondCarte.png"));
			
			rondCarte.setToolTipText("Acc\u00E8der à la Carte de " + contact.getNom() + ".");
			rondCarte.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) {
		        	  removeAll();
		        	  PanelBaseContact m = new PanelBaseContact(fen, contact, "Carte");						
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
			}
		}else{
			rondCarte.setIcon(new ImageIcon("./Images/rondCarteGris.png"));
			rondCarte.setToolTipText("Vous n'avez pas acc\u00E8s à la Carte de " + contact.getNom() + ".");
		}
		rondCarte.setBounds(0, 0, 50, 50);
		Border paddingBorderCa = BorderFactory.createEmptyBorder(0,7,0,0);
		rondCarte.setBorder(paddingBorderCa);
		barreVerticale.add(rondCarte,"cell 0 4,alignx left,aligny center");
				
		Color grey = new Color(68,68,68); 
		JLabel imageContact = new JLabel(new ImageIcon(contact.getImage()));
		barreHorizontale.add(imageContact,"cell 0 0,alignx left,aligny center");
		
		JLabel nomContact = new JLabel(contact.getNom() + " "+ contact.getPrenom());
		nomContact.setFont(new Font("Mockup", Font.PLAIN, 22));
		nomContact.setForeground(grey); 
		barreHorizontale.add(nomContact, "cell 0 0,alignx left,aligny center");
		
		JLabel textActuel = new JLabel(Texte);
		textActuel.setFont(new Font("Mockup", Font.PLAIN, 16));
		textActuel.setForeground(grey); 
		barreHorizontale.add(textActuel, "cell 1 0,alignx right,aligny center");
		
		barreHorizontale.add(rondActuel,"cell 1 0,alignx right,aligny center");
		
	    JPanel iconesBas = new JPanel();
			barreVerticale.add(iconesBas, "cell 0 6, alignx left, aligny bottom");
			JLabel param = new JLabel(new ImageIcon("./Images/cogSmall.png"));
			param.setToolTipText("Param\u00E8tres");
			param.setBounds(0, 0, 10, 10);
			param.addMouseListener(new MouseAdapter() { 
		         public void mousePressed(MouseEvent me) { 
		        	 PanelParametres m = new PanelParametres(fen, "des contacts", contact);	
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
			iconesBas.add(param);
			
			JLabel aide = new JLabel(new ImageIcon("./Images/aideSmall.png"));
			aide.setToolTipText("Aide");
			aide.setBounds(0, 0, 10, 10);
			iconesBas.add(aide);
			aide.addMouseListener(new MouseAdapter() { 
		         public void mousePressed(MouseEvent me) { 
		        	 JFrame f = new JFrame();
					 PanelAide m = new PanelAide(f, Texte);	
				   	 f.setContentPane(m);
				   	 f.setResizable(false);
				     f.setBounds(100, 100, 500, 180);
					 f.setVisible(true);
		         } 
			     public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
			     }
			  	 public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
				 } 
			 });
			 
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
			this.add(contenu, "cell 2 2,alignx left,aligny top");
			
	}

}
