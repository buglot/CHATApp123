package PanelChat;

import ServerSocket.ClientUa;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

//นายยงเกียรติ แสวงสุข 6430300790
public class MenuChat_Panel extends JPanel {
    JPanel MainMenu,cardLayoutChat_panel,Chat,FollowChatWithAccount;
    HashMap<String,PaneNameUser> ProfileA;
    HashMap<String ,myJScrollPane> CollectAccount;
    HashMap<String , ClientUa> conectionClient;
    HashMap<String,Chat_Panel> chat_panel1;
    private HashMap<String ,ConectPaneMenu> conectPaneMenuHashMap;
    private String UsernameA;
    //นายยงเกียรติ แสวงสุข 6430300790
    private ButtonMenuIcon MenuB_Messenger, MenuB_Account, MenuB_HashtagRoom;
    public MenuChat_Panel(String username,HashMap<String,Chat_Panel> chat_panel1,JPanel cardLayoutChat_panel) {
        JPanel TopMenuUI = new JPanel();
        MenuB_Account = new ButtonMenuIcon("/pic/Accountv2.png");
        MenuB_Messenger = new ButtonMenuIcon("/pic/Messengev2.png");
        MenuB_HashtagRoom = new ButtonMenuIcon("/pic/HashtagRoomv2.png");

        MenuB_HashtagRoom.addActionListener(new doButton());
        MenuB_Account.addActionListener(new doButton());
        MenuB_Messenger.addActionListener(new doButton());

        TopMenuUI.add(MenuB_Account);
        TopMenuUI.add(MenuB_Messenger);
        TopMenuUI.add(MenuB_HashtagRoom);

        TopMenuUI.setLayout(new GridLayout(1, 3, 2, 2));
        UsernameA = username;
        MenuB_Account.setBorderV2(true);
        setLayout(new BorderLayout());

        add(TopMenuUI, BorderLayout.NORTH);
        TopMenuUI.setBackground(new Color(50, 50, 50));
        setBackground(new Color(65, 65, 65));

        MainMenu = new JPanel();
        MainMenu.setLayout(new CardLayout());
        add(MainMenu, BorderLayout.CENTER);

        Chat = new NewPanelMe(getBackground());
        JPanel Room = new NewPanelMe(getBackground());
        Chat.add(new LabelTitle("Chat"),"wrap");
        Room.add(new LabelTitle("Hashtag Room"),"wrap");

        JScrollPane EChat= new myJScrollPane(Chat);
        JScrollPane ERoom = new myJScrollPane(Room);

        FollowChatWithAccount = new JPanel();
        FollowChatWithAccount.setLayout(new CardLayout());
        CollectAccount = new HashMap<>();
        conectPaneMenuHashMap= new HashMap<>();
        CollectAccount.put("0",new myJScrollPane(new NewPanelMe(getBackground())));
        FollowChatWithAccount.add(CollectAccount.get("0"),"0");
        CollectAccount.get("0").getPanel().add(new LabelTitle("Account"),"wrap");

        MainMenu.add(FollowChatWithAccount, "Account");
        MainMenu.add(EChat, "Chat");
        MainMenu.add(ERoom, "Room");

        ProfileA = new HashMap<>();
        conectionClient = new HashMap<>();
        this.chat_panel1= chat_panel1;
        this.cardLayoutChat_panel = cardLayoutChat_panel;

        Chat.add(new menuAddChat(Chat,CollectAccount,FollowChatWithAccount),"wrap");

    }
    //นายยงเกียรติ แสวงสุข 6430300790
    public void addAccount(String name,String serverPort) {
        Color color = getBackground();
        if(UsernameA.equals(name)){
            color = new Color(48, 114, 140);
        }
        ProfileA.put(name,new PaneNameUser(name,color));
        CollectAccount.get(serverPort).getPanel().add(ProfileA.get(name),"wrap , w :93%");
        CollectAccount.get(serverPort).getPanel().revalidate();
        CollectAccount.get(serverPort).getPanel().repaint();
    }
    public void RemoveAccount(String name,String serverPort){
        CollectAccount.get(serverPort).getPanel().remove(ProfileA.get(name));
        CollectAccount.get(serverPort).getPanel().revalidate();
        CollectAccount.get(serverPort).getPanel().repaint();
    }
    class doButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout) (MainMenu.getLayout());
            if (e.getSource() == MenuB_Account) {
                cl.show(MainMenu, "Account");
                MenuB_Account.setBorderV2(true);
                MenuB_HashtagRoom.setBorderV2(false);
                MenuB_Messenger.setBorderV2(false);
            } else if (e.getSource() == MenuB_Messenger) {
                cl.show(MainMenu, "Chat");
                MenuB_Account.setBorderV2(false);
                MenuB_HashtagRoom.setBorderV2(false);
                MenuB_Messenger.setBorderV2(true);
            } else if (e.getSource() == MenuB_HashtagRoom) {
                cl.show(MainMenu, "Room");
                MenuB_Account.setBorderV2(false);
                MenuB_HashtagRoom.setBorderV2(true);
                MenuB_Messenger.setBorderV2(false);
            }
        }
    }

    //นายยงเกียรติ แสวงสุข 6430300790
    class menuAddChat extends JPanel{
        private Color color;
        private HashMap<String ,myJScrollPane> CollectAccount1;
        public menuAddChat(JPanel Chat1,HashMap<String ,myJScrollPane> CollectAccount,JPanel FollowChatWithAccount) {
            color = new Color(54,54,54);
            setLayout(new BorderLayout());
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/pic/ADDChat.png"));
            Icon newPIC = new ImageIcon(imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            JLabel pic = new JLabel(newPIC);
            add(pic,BorderLayout.WEST);
            setBackground(color);
            this.CollectAccount1 =CollectAccount;
            setToolTipText("Join Chat");
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    setBackground(new Color(132, 203, 255));
                    new FrameJoinChat("Join Chat",Chat1,CollectAccount1,UsernameA,chat_panel1,cardLayoutChat_panel,FollowChatWithAccount,conectPaneMenuHashMap);

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    setBackground(new Color(42,42,42));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    setBackground(color);
                }
            });
        }
    }

}


//นายยงเกียรติ แสวงสุข 6430300790
class NewPanelMe extends JPanel {
        public NewPanelMe(Color color) {
            setBackground(color);
            setLayout(new MigLayout(""));

        }
}

//นายยงเกียรติ แสวงสุข 6430300790
class LabelTitle extends JLabel {
    public LabelTitle(String text) {
        super(text);
        setForeground(Color.WHITE);
        setOpaque(true);
        setBorder(new MatteBorder(0, 0, 2, 0, new Color(90, 90, 90)));
        setBackground(new Color(54, 54, 54));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setFont(new Font("", Font.BOLD, 13));
        setHorizontalTextPosition(0);
        setPreferredSize(new Dimension(200,50));
        setHorizontalAlignment(0);
    }

}