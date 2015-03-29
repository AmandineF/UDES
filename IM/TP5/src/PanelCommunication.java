import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class PanelCommunication extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelCommunication() {
		setLayout(new MigLayout("", "[60%][20%][20%]", "[10%][1%][10%]"));

		this.setBackground(Color.WHITE);
		
		//Maman
		JLabel contactMaman = new JLabel( new ImageIcon( "./images/contactMaman.png"));
		this.add(contactMaman, "cell 0 0, alignx left, aligny center" );
		
		contactMaman.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
										
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					//contactMaman.setIcon(new ImageIcon( "./images/aproposHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					//contactMaman.setIcon(new ImageIcon( "./images/apropos.png"));
				}
				}  );
		
		final JLabel btnAppelMaman = new JLabel( new ImageIcon( "./images/btnAppel.png"));
		this.add(btnAppelMaman, "cell 1 0,alignx left,aligny center");
		
		btnAppelMaman.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnAppelMaman.setIcon(new ImageIcon( "./images/btnAppelHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnAppelMaman.setIcon(new ImageIcon( "./images/btnAppel.png"));
				}
				}  );
		
		final JLabel btnVideoMaman = new JLabel( new ImageIcon( "./images/btnVideo.png"));
		this.add(btnVideoMaman, "cell 2 0,alignx left,aligny center");
		
		btnVideoMaman.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnVideoMaman.setIcon(new ImageIcon( "./images/btnVideoHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnVideoMaman.setIcon(new ImageIcon( "./images/btnVideo.png"));
				}
				}  );
		
		
		JPanel barreNoireH = new JPanel();
		FlowLayout flowLayout = (FlowLayout) barreNoireH.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		barreNoireH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		add(barreNoireH, "cell 0 1 3 1,growx");
		
		//Papa
		JLabel contactPapa = new JLabel( new ImageIcon( "./images/contactPapa.png"));
		this.add(contactPapa, "cell 0 2,alignx left,aligny center");
		
		contactPapa.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
										
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					//contactMaman.setIcon(new ImageIcon( "./images/aproposHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					//contactMaman.setIcon(new ImageIcon( "./images/apropos.png"));
				}
				}  );
		final JLabel btnAppelPapa = new JLabel( new ImageIcon( "./images/btnAppel.png"));
		this.add(btnAppelPapa, "cell 1 2,alignx left,aligny center");

		
		btnAppelPapa.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnAppelPapa.setIcon(new ImageIcon( "./images/btnAppelHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnAppelPapa.setIcon(new ImageIcon( "./images/btnAppel.png"));
				}
				}  );
		
		final JLabel btnVideoPapa = new JLabel( new ImageIcon( "./images/btnVideo.png"));
		this.add(btnVideoPapa, "cell 2 2,alignx left,aligny center");
		

		
		btnVideoPapa.addMouseListener(
				new MouseAdapter(){
				public void mouseClicked (MouseEvent e){
						
											
					}
				public void mouseEntered(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
					btnVideoPapa.setIcon(new ImageIcon( "./images/btnVideoHover.png"));
				}
				public void mouseExited(MouseEvent e) {
					setCursor(Cursor.getDefaultCursor());
					btnVideoPapa.setIcon(new ImageIcon( "./images/btnVideo.png"));
				}
				}  );
	}

}
