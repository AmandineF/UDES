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

/**
 * Gestion du panel base
 * Base de la navigation et gestion des fonctionnalites
 * @author Amandine Fouillet - 14 130 638
 * @author Frank Chassing - 14 153 710
 * @author Laurent Senecal-Leonard - 14 143 484
 */
@SuppressWarnings("serial")
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
	private JLabel partage;
	private JPanel barreHorizontale;
	private MouseListener partagelist;
	private MouseListener aideListener;
	private JLabel aide;
	private JLabel param;
	private JLabel home;
	
	/**
	 * Constructeur du panel base
	 * @param fen La fenetre d'origine
	 * @param Texte Le texte de la fonctionnalite actuelle
	 * @param contact Le contact selectionne
	 */
	
	public PanelBase(JFrame fen, String Texte, Contact contact) {
		creerBase(fen);
		modifierBase(fen, Texte, contact);
	}

	
	public void creerBase(final JFrame fen) {
		setLayout(new MigLayout("insets 0", "[60]0[1]0[399]", "[60]0[1]0[439]"));
		this.setBounds(0, 0, 400, 460);
		
		this.home = new JLabel(new ImageIcon("./Images/home.png"));
		home.setBounds(0, 0, 50, 50);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		home.setBorder(paddingBorder);
		this.add(home,"cell 0 0,alignx center,aligny center");
		home.setToolTipText("Retour menu principal");
		
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
		
		this.barreHorizontale = new JPanel(new MigLayout("insets 0","[60][339][40]", ""));
		add(barreHorizontale, "cell 2 0,alignx left,aligny center");
		
		this.rondCom = new JLabel(new ImageIcon("./Images/rondCom.png"));
		rondCom.setBounds(0, 0, 50, 50);
		rondCom.setToolTipText("Communication");
		Border paddingBorderCo = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondCom.setBorder(paddingBorderCo);
		barreVerticale.add(rondCom,"cell 0 0,alignx left,aligny center");
		this.rondCom.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Communication", null);						
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
		rondPhoto.setToolTipText("Photo");
		Border paddingBorderP = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondPhoto.setBorder(paddingBorderP);
		barreVerticale.add(rondPhoto,"cell 0 1,alignx left,aligny center");
		this.rondPhoto.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Photos", null);						
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
		rondCal.setToolTipText("Calendrier");
		Border paddingBorderCal = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondCal.setBorder(paddingBorderCal);
		barreVerticale.add(rondCal,"cell 0 2,alignx left,aligny center");
		this.rondCal.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) {
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Calendrier", null);						
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
		rondDep.setToolTipText("D\u00E9penses");
		Border paddingBorderD = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondDep.setBorder(paddingBorderD);
		barreVerticale.add(rondDep,"cell 0 3,alignx left,aligny center");
		this.rondDep.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "D\u00E9penses", null);						
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
		rondCarte.setToolTipText("Carte");
		Border paddingBorderCa = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondCarte.setBorder(paddingBorderCa);
		barreVerticale.add(rondCarte,"cell 0 4,alignx left,aligny center");
		this.rondCarte.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	        	  removeAll();  
	        	  PanelBase m = new PanelBase(fen, "Carte", null);						
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
		rondContact.setToolTipText("Contacts");
		Border paddingBorderC = BorderFactory.createEmptyBorder(0,7,0,0);
		this.rondContact.setBorder(paddingBorderC);
		barreVerticale.add(rondContact,"cell 0 5,alignx left,aligny center");
		this.rondContact.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
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
		        	  removeAll();  
		        	  PanelBase m = new PanelBase(fen, "Partage", null);						
		        	  fen.setContentPane(m);
		        	  fen.validate(); 
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
  		
	}
	
	
	/**
	 * Gere le changement de fonctionnalite dans le panel base
	 * @param fen La fenetre d'origine
	 * @param Texte Le texte de la fonctionnalite
	 * @param contact Le contact actuel
	 */
	public void modifierBase(final JFrame fen, final String Texte, Contact contact) {

		this.textActuel.setText(Texte);
		
		String img = "";
		Icon ic = null;
		Icon ip = new ImageIcon("./Images/partage.png");
		String imgContact;
		if(Texte == "Contacts") {
			imgContact = "./Images/rondContactSec.png";
			img = "./Images/rondContactSec.png";
			this.contenu = new PanelContacts(fen);
			this.partage.setIcon(new ImageIcon("./Images/plus.png"));
			this.partage.setToolTipText("Ajouter un contact");
			MouseAdapter contactList = new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		        	  removeAll();  
		        	  PanelAjoutContact m = new PanelAjoutContact(fen);						
		        	  fen.setContentPane(m);
		        	  fen.validate();
		            } 
		          public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
			  		public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
					}
		          }; 
			this.partage.addMouseListener(contactList);
		}else{
			imgContact = "./Images/rondContact.png";
		}
		ic = new ImageIcon(imgContact);
		this.rondContact.setIcon(ic);

		String imgCarte;
		if(Texte == "Carte") {
			imgCarte = "./Images/rondCarteSec.png";
			img = "./Images/rondCarteSec.png";
			this.contenu = new PanelCarte();
			this.partage.setIcon(ip);
			this.partage.setToolTipText("Partage");
			this.partage.addMouseListener(partagelist);
		}else{
			imgCarte = "./Images/rondCarte.png";
		}
		ic = new ImageIcon(imgCarte);
		this.rondCarte.setIcon(ic);

		String imgDep;
		if(Texte == "D\u00E9penses") {
			imgDep = "./Images/rondDepSec.png";
			img = "./Images/rondDepSec.png";
			this.contenu = new PanelDepense();
			this.partage.setToolTipText("Partage");
			this.partage.setIcon(ip);
			this.partage.addMouseListener(partagelist);
		}else{
			imgDep = "./Images/rondDep.png";
		}
		ic = new ImageIcon(imgDep);
		this.rondDep.setIcon(ic);

		String imgCal;
		if(Texte == "Calendrier") {
			imgCal = "./Images/rondCalSec.png";
			img = "./Images/rondCalSec.png";
			this.contenu = new PanelCalendrier();
			this.partage.setToolTipText("Partage");
			this.partage.setIcon(ip);
			this.partage.addMouseListener(partagelist);
		}else{
			imgCal = "./Images/rondCal.png";
		}
		ic = new ImageIcon(imgCal);
		this.rondCal.setIcon(ic);

		String imgPhoto;
		if(Texte == "Photos") {
			imgPhoto = "./Images/rondPhotoSec.png";
			img = "./Images/rondPhotoSec.png";
			this.contenu = new PanelPhoto(fen);
			this.partage.setIcon(ip);
			this.partage.setToolTipText("Partage");
			this.partage.addMouseListener(partagelist);
		}else{
				imgPhoto = "./Images/rondPhoto.png";
		}
		ic = new ImageIcon(imgPhoto);
		this.rondPhoto.setIcon(ic);

		String imgCom;
		if(Texte.equals("Communication")) {
			imgCom = "./Images/rondComSec.png";
			img = "./Images/rondComSec.png";
			this.contenu = new PanelCommunication(fen);
			this.partage.setIcon(new ImageIcon("./Images/plus.png"));
			this.partage.setToolTipText("Nouvelle conversation");
			MouseAdapter comList = new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		            } 
		          public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
			  		public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
					}
		          }; 
			this.partage.addMouseListener(comList);
		}else{
			if(!Texte.equals("Messages"))
				imgCom = "./Images/rondCom.png";
			else
				imgCom = "./Images/rondComSec.png";
		}
		ic = new ImageIcon(imgCom);
		this.rondCom.setIcon(ic);
		
		String imgMes;
		if(Texte.equals("Messages")) {
			imgMes = "./Images/rondComSec.png";
			img = "./Images/rondComSec.png";
			this.contenu = new PanelChat(contact, fen);
			this.partage.setIcon(new ImageIcon("./Images/plus.png"));
			this.partage.setToolTipText("Nouvelle conversation");
			MouseAdapter comList = new MouseAdapter() { 
		          public void mousePressed(MouseEvent me) { 
		            } 
		          public void mouseEntered(MouseEvent e) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					}
			  		public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
					}
		          }; 
			this.partage.addMouseListener(comList);
		}else{
			if(!Texte.equals("Communication"))
				imgMes = "./Images/rondCom.png";
			else
				imgMes = "./Images/rondComSec.png";
		}
		ic = new ImageIcon(imgMes);
		this.rondCom.setIcon(ic);

		if(Texte.equals("Partage")) {
			this.contenu = new PanelPartage(fen);
			this.partage.setIcon(new ImageIcon("./Images/plus.png"));
			this.partage.setToolTipText("Ajouter un contact à la liste de partage");
			this.partage.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
			  	 public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
				 } 
			});
		}
		if(Texte.equals("Album photo")) {
			imgPhoto = "./Images/rondPhotoSec.png";
			img = "./Images/rondPhoto.png";
			this.home.setIcon(new ImageIcon("./Images/arrow.png"));
			this.home.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent me) { 
					removeAll();  
		        	PanelBase m = new PanelBase(fen, "Photos", null);						
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
			this.contenu = new PanelAlbum(fen);
			this.partage.setIcon(new ImageIcon("./Images/plus.png"));
			this.partage.setToolTipText("Ajouter des photos à l'album");
			this.partage.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
				}
			  	 public void mouseExited(MouseEvent e) {
						setCursor(Cursor.getDefaultCursor());
				 } 
			});
		} else {
			if(!Texte.equals("Photos"))
				imgPhoto = "./Images/rondPhoto.png";
		}
		ic = new ImageIcon(imgPhoto);
		this.rondPhoto.setIcon(ic);
		
		this.aide.removeMouseListener(this.aideListener);
		this.aideListener = new MouseAdapter() { 
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
		 };
		this.aide.addMouseListener(this.aideListener);
		
		this.param.addMouseListener(new MouseAdapter() { 
	         public void mousePressed(MouseEvent me) { 
	        	 PanelParametres m = new PanelParametres(fen, Texte,null);	
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
		 
		ic = new ImageIcon(img);
		this.rondActuel.setIcon(ic);
		this.add(this.contenu, "cell 2 2,alignx left,aligny top");
		barreHorizontale.add(this.partage, "cell 2 0,alignx left,aligny center");
	}

}
