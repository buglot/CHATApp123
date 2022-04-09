package PanelChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaneNameUser extends JPanel {
    public PaneNameUser(String Name, Color color) {
        setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/pic/Profile.png"));
        Icon newPIC = new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel pic = new JLabel(newPIC);
        add(pic,BorderLayout.WEST);
        JLabel USERNAME = new JLabel(Name);
        USERNAME.setFont(new Font("",Font.BOLD,13));
        USERNAME.setForeground(new Color(200,200,200));
        JPanel mid = new JPanel();
        mid.setLayout(new GridLayout(0,1));
        mid.add(USERNAME);
        mid.setBackground(color);
        JLabel ON = new JLabel("online");
        ON.setForeground(new Color(80,255,180));
        mid.add(ON);
        add(mid);
        setBackground(color);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(new Color(50,50,50));
                mid.setBackground(new Color(50,50,50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(color);
                mid.setBackground(color);
            }
        });
    }
}