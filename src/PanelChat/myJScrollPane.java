package PanelChat;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
//นายยงเกียรติ แสวงสุข 6430300790
public class myJScrollPane extends JScrollPane {
    private JPanel panelAdd ;
    public myJScrollPane(JPanel view) {
        super(view);
        panelAdd = view;
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(6);
        setBorder(BorderFactory.createEmptyBorder());
        setBorder(new MatteBorder(0,0,0,1,new Color(75, 75, 75)));
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    public JPanel getPanel() {
        return panelAdd;
    }
}