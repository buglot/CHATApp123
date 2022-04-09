package PanelChat;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790
public class blankMenu_Panel extends JPanel {
    public blankMenu_Panel() {
        JLabel a = new JLabel("........");
        setBorder(new MatteBorder(0,1,0,0,new Color(72, 106, 154)));
        add(a);
        setBackground(new Color(58, 50, 50));
        a.setForeground(getBackground());
    }
}
