/**
 * @author Frank CHASSING 14 153 710
 * @author Amandine FOUILLET 14 130 638
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class PanelAjout extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAjout(final MainFrame oldFen, final JFrame newFen, ArrayList<String> ids, final JList j) {
		
		final JCheckBox ift209 = new JCheckBox("IFT209");
		if(ids.contains("IFT 209")){
			ift209.setSelected(true);
		}else{
			ift209.setSelected(false);
		}
		setLayout(new MigLayout("", "[60px][60px][10px][60px]", "[30px][30px][30px]"));
		add(ift209, "cell 0 0,alignx left,aligny top");
		
		final JCheckBox ift215 = new JCheckBox("IFT215");
		if(ids.contains("IFT 215")){
			ift215.setSelected(true);
		}else{
			ift215.setSelected(false);
		}
		add(ift215, "cell 1 0,alignx left,aligny top");
		
		final JCheckBox ift232 = new JCheckBox("IFT232");
		if(ids.contains("IFT 232")){
			ift232.setSelected(true);
		}else{
			ift232.setSelected(false);
		}
		add(ift232, "cell 2 0,alignx left,aligny top");
		
		final JCheckBox ift287 = new JCheckBox("IFT287");
		if(ids.contains("IFT 287")){
			ift287.setSelected(true);
		}else{
			ift287.setSelected(false);
		}
		add(ift287, "cell 3 0,alignx left,aligny top");
		
		final JCheckBox ift313 = new JCheckBox("IFT313");
		if(ids.contains("IFT 313")){
			ift313.setSelected(true);
		}else{
			ift313.setSelected(false);
		}
		add(ift313, "cell 4 0,alignx left,aligny top");
		
		final JCheckBox ift320 = new JCheckBox("IFT320");
		if(ids.contains("IFT 320")){
			ift320.setSelected(true);
		}else{
			ift320.setSelected(false);
		}
		add(ift320, "cell 0 1,alignx left,aligny center");
		
		final JCheckBox ift339 = new JCheckBox("IFT339");
		if(ids.contains("IFT 339")){
			ift339.setSelected(true);
		}else{
			ift339.setSelected(false);
		}
		add(ift339, "cell 1 1,alignx left,aligny center");
		
		final JCheckBox ift359 = new JCheckBox("IFT359");
		if(ids.contains("IFT 359")){
			ift359.setSelected(true);
		}else{
			ift359.setSelected(false);
		}
		add(ift359, "cell 2 1,alignx left,aligny center");
		
		final JCheckBox mat193 = new JCheckBox("MAT193");
		if(ids.contains("MAT 193")){
			mat193.setSelected(true);
		}else{
			mat193.setSelected(false);
		}
		add(mat193, "cell 3 1,alignx left,aligny center");
		
		JButton valider = new JButton("Valider");
		add(valider, "cell 2 3,alignx left,aligny top");
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
					oldFen.validate();
					newFen.dispose();
				}}  );
	}

}
