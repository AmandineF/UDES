import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;


public class PanelAjout extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAjout(MainFrame oldFen, JFrame newFen, ArrayList<String> ids, JList j) {
		
		
		JCheckBox ift209;
		if(ids.contains("IFT 209")){
			ift209 = new JCheckBox("IFT209", true);
			add(ift209);
		}else{
			ift209 = new JCheckBox("IFT209", false);
			add(ift209);
		}
		
		JCheckBox ift215;
		if(ids.contains("IFT 215")){
			ift215 = new JCheckBox("IFT215", true);
			add(ift215);
		}else{
			ift215 = new JCheckBox("IFT215", false);
			add(ift215);
		}
		
		JCheckBox ift232;
		if(ids.contains("IFT 232")){
			ift232 = new JCheckBox("IFT232", true);
			add(ift232);
		}else{
			ift232 = new JCheckBox("IFT232", false);
			add(ift232);
		}
		
		JCheckBox ift287;
		if(ids.contains("IFT 287")){
			ift287 = new JCheckBox("IFT287", true);
			add(ift287);
		}else{
			ift287 = new JCheckBox("IFT287", false);
			add(ift287);
		}
		
		JCheckBox ift313;
		if(ids.contains("IFT 313")){
			ift313 = new JCheckBox("IFT313", true);
			add(ift313);
		}else{
			ift313 = new JCheckBox("IFT313", false);
			add(ift313);
		}
		
		JCheckBox ift320;
		if(ids.contains("IFT 320")){
			ift320 = new JCheckBox("IFT320", true);
			add(ift320);
		}else{
			ift320 = new JCheckBox("IFT320", false);
			add(ift320);
		}
		
		JCheckBox ift339;
		if(ids.contains("IFT 339")){
			ift339 = new JCheckBox("IFT339", true);
			add(ift339);
		}else{
			ift339 = new JCheckBox("IFT339", false);
			add(ift339);
		}
		
		JCheckBox ift359;
		if(ids.contains("IFT 359")){
			ift359 = new JCheckBox("IFT359", true);
			add(ift359);
		}else{
			ift359 = new JCheckBox("IFT359", false);
			add(ift359);
		}
		
		JCheckBox mat193;
		if(ids.contains("MAT 193")){
			mat193 = new JCheckBox("MAT193", true);
			add(mat193);
		}else{
			mat193 = new JCheckBox("MAT193", false);
			add(mat193);
		}
		
		JButton valider = new JButton("Valider");
		add(valider);
		valider.addActionListener(
				new ActionListener(){
				public void actionPerformed (ActionEvent e){
					DefaultListModel listModel = new DefaultListModel();
					if(ift209.isSelected()){
						String s = "IFT 209 - Programmation système";
						listModel.addElement(s);
					}
					if(ift215.isSelected()){
						String s = "IFT 215 - Interfaces et multimédia";
						listModel.addElement(s);
					}
					if(ift232.isSelected()){
						String s = "IFT 232 - Méthodes de conception orientées objet";
						listModel.addElement(s);
					}
					if(ift287.isSelected()){
						String s = "IFT 287 - Exploitation de BD relationnelles et OO";
						listModel.addElement(s);
					}
					if(ift313.isSelected()){
						String s = "IFT 313 - Introduction aux langages formels";
						listModel.addElement(s);
					}
					if(ift320.isSelected()){
						String s = "IFT 320 - Systèmes d'exploitation";
						listModel.addElement(s);
					}
					if(ift339.isSelected()){
						String s = "IFT 339 - Structures de données";
						listModel.addElement(s);
					}
					if(ift359.isSelected()){
						String s = "IFT 359 - Programmation fonctionnelle";
						listModel.addElement(s);
					}
					if(mat193.isSelected()){
						String s = "MAT 193 - Algèbre linéaire";
						listModel.addElement(s);
					}
					JList newList = new JList(listModel);
					DefaultListModel d = (DefaultListModel)j.getModel();
					d.removeAllElements();
					j.setModel(listModel);
					oldFen.revalidate();
					newFen.dispose();
				}}  );
	}

}
