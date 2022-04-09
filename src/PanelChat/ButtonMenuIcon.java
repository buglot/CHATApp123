package PanelChat;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790
public class ButtonMenuIcon extends JButton {
    private MatteBorder a1, a2;
    private Color b1,b2,b3,b1D;
    private int w,h;

    public ButtonMenuIcon(String File){
        this(File,40,40);
    }
    public ButtonMenuIcon(String File,int width ,int height) {
        w=width;
        h=height;
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(File));
        Icon newIcom = new ImageIcon(imageIcon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        setIcon(newIcom);
        b1 = new Color(50,50,50);
        b2 = new Color(90,90,90);
        b3 = new Color(75,75,75);
        b1D = b1;
        setBackground(b1);
        addMouseListener(new enterMouse());
        a1 = new MatteBorder(0, 0, 3, 0, new Color(70, 70, 70));
        setBorder(a1);
        a2 = new MatteBorder(0, 0, 3, 0, new Color(110, 110, 110));
        setFocusPainted(false);
    }

    public void setColorBG(Color b1) {
        this.b1 = b1;
        this.b1D = b1;
        setBackground(b1);
    }

    public void setColorMouseEnter(Color b2) {
        this.b2 = b2;
    }

    public void setColorMouseClick(Color b3) {
        this.b3 = b3;
    }

    class enterMouse extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(b2);
            setBorder(a2);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(b1);
            setBorder(a1);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            a1 = new MatteBorder(0, 0, 4, 0, new Color(240,240,240));
            setBackground(b3);
            setBorder(a1);
        }
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    public void setBorderV2(boolean t){
        if(t){
            a1 = new MatteBorder(0, 0, 4, 0,new Color(240,240,240));
            a2=a1;
            b1=b3;
            setBackground(b3);
            setBorder(a2);
        }else{
            a1 = new MatteBorder(0, 0, 3, 0, new Color(70,70,70));
            setBorder(a1);
            b1 = b1D;
            a2 = new MatteBorder(0, 0, 3, 0, new Color(110, 110, 110));
            setBackground(b1);
        }
    }

}
//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790