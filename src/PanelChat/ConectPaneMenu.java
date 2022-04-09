package PanelChat;

//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790

import ServerSocket.ChatMessage;
import ServerSocket.ClientUa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790

public class ConectPaneMenu extends JPanel {
    private int port;
    private ClientUa clientUa;
    private JPanel cardLayoutChat;
    private  HashMap<String ,ConectPaneMenu> conectPaneMenuHashMap;
    public ConectPaneMenu(String Server, int port, JPanel doAdd, Color color
            , HashMap<String ,myJScrollPane> CollectAccount
            ,String Username
            ,HashMap<String,Chat_Panel> chat_panel1
            ,JPanel cardLayoutChat_panel
            ,JPanel FollowChatWithAccount) {
        this.port=port;
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/pic/Chat-1.png"));
        Icon newPICs = new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        ButtonMenuIcon Remove = new ButtonMenuIcon("/pic/RemoveChat.png", 30, 20);
        Remove.setColorBG(new Color(44, 44, 44));
        Remove.setColorMouseEnter(new Color(255, 60, 30));
        Remove.setColorMouseClick(new Color(255, 20, 30));
        JLabel IconChat = new JLabel(newPICs);
        setLayout(new BorderLayout());
        JLabel USERNAME = new JLabel(Server + " : " + port);
        USERNAME.setFont(new Font("", Font.BOLD, 13));
        USERNAME.setForeground(new Color(200, 200, 200));
        JPanel mid = new JPanel();
        mid.setLayout(new GridLayout(0, 1));
        mid.add(USERNAME);
        mid.setBackground(color);
        add(mid);
        this.cardLayoutChat = cardLayoutChat_panel;
        add(IconChat, BorderLayout.WEST);
        add(Remove, BorderLayout.EAST);
        setBackground(color);

        //นายยงเกียรติ แสวงสุข 6430300790
        //นายยงเกียรติ แสวงสุข 6430300790

        CollectAccount.put(Server+":"+port,new myJScrollPane(new NewPanelMe(getBackground())));
        FollowChatWithAccount.add(CollectAccount.get(Server+":"+port),Server+":"+port);
        CollectAccount.get(Server+":"+port).getPanelAdd().add(new LabelTitle("Account"),"wrap");
        chat_panel1.put(Server+":"+port,new Chat_Panel(Username,Server+":"+port));
        cardLayoutChat_panel.add(chat_panel1.get(Server+":"+port),Server+":"+port);
        this.clientUa = new ClientUa(Server,port,Username,chat_panel1.get(Server+":"+port),CollectAccount);
        if(!this.clientUa.start()){
            return;
        }
        clientUa.sendMessage(new ChatMessage(3,"3"));
        clientUa.sendMessage(new ChatMessage(0,"3"));
        CardLayout cl = (CardLayout) (FollowChatWithAccount.getLayout());
        cl.show(FollowChatWithAccount,String.valueOf(Server+":"+port));
        CardLayout cl2 = (CardLayout) cardLayoutChat_panel.getLayout();
        cl2.show(cardLayoutChat_panel,String.valueOf(Server+":"+port));
        chat_panel1.get(Server+":"+port).setClient(clientUa);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(new Color(50, 50, 50));
                mid.setBackground(new Color(50, 50, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(color);
                mid.setBackground(color);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cl.show(FollowChatWithAccount,Server+":"+port);
                cl2.show(cardLayoutChat_panel,Server+":"+port);
            }
        });
        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Remove.setBorderV2(true);
                doAdd.remove(ConectPaneMenu.this);
                doAdd.revalidate();
                doAdd.repaint();
                clientUa.sendMessage(new ChatMessage(2,"3"));
                cardLayoutChat_panel.remove(chat_panel1.get(Server+":"+port));
                chat_panel1.remove(Server+":"+port);
                cardLayoutChat_panel.revalidate();
                cardLayoutChat_panel.repaint();
                conectPaneMenuHashMap.remove(Server+":"+port);
            }
        });



    }

    public HashMap<String, ConectPaneMenu> getConectPaneMenuHashMap() {
        return conectPaneMenuHashMap;
    }

    public void setConectPaneMenuHashMap(HashMap<String, ConectPaneMenu> conectPaneMenuHashMap) {
        this.conectPaneMenuHashMap = conectPaneMenuHashMap;
    }

    public ClientUa getClientUa() {
        return clientUa;
    }

}
