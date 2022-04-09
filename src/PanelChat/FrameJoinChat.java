package PanelChat;

//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790

import ServerSocket.ClientUa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FrameJoinChat extends JFrame {

    //นายยงเกียรติ แสวงสุข 6430300790
    //นายยงเกียรติ แสวงสุข 6430300790
    private ClientUa client;
    private JTextField textField,textField1;
    private HashMap<String ,myJScrollPane> CollectAccount1;
    public FrameJoinChat(String title){
        this(title,null,null,"Unknows",null,null,null,null);
    }
    public FrameJoinChat(String title, JPanel chat, HashMap<String ,myJScrollPane> CollectAccount,
                         String username,
                         HashMap<String,Chat_Panel> chat_panel1,JPanel cardLayoutChat_panel,
                         JPanel FollowChatWithAccount,HashMap<String ,ConectPaneMenu> conectPaneMenuHashMap)  {
        super(title);
        setLayout(new FlowLayout());
        JLabel ServerName = new JLabel("ServerName :");
        JLabel Port1 = new JLabel("Port :");
        textField = new JTextField("localhost", 20);
        textField1 = new JTextField(10);
        setLocation(60, 60);
        setSize(600, 100);
        add(ServerName);
        add(textField);
        add(Port1);
        add(textField1);
        JButton joinButton = new JButton("Join");
        add(joinButton);
        this.CollectAccount1 = CollectAccount;
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!conectPaneMenuHashMap.containsKey(textField.getText()+":"+textField1.getText())){
                    conectPaneMenuHashMap.put(textField.getText()+":"+textField1.getText(),new ConectPaneMenu(textField.getText(),Integer.valueOf(textField1.getText()),chat,
                            chat.getBackground(),CollectAccount1,username,chat_panel1,cardLayoutChat_panel,FollowChatWithAccount));
                    chat.add(conectPaneMenuHashMap.get(textField.getText()+":"+textField1.getText()),"wrap, w :99%:93%");
                    chat.revalidate();
                    chat.repaint();
                    CollectAccount.put(textField.getText()+":"+textField1.getText(),
                            new myJScrollPane(new NewPanelMe(chat.getBackground())));
                    conectPaneMenuHashMap.get(textField.getText()+":"+textField1.getText()).setConectPaneMenuHashMap(conectPaneMenuHashMap);
                }else{
                    JOptionPane.showMessageDialog(FrameJoinChat.this,
                            "You joined Server.",
                            "warning",
                            JOptionPane.WARNING_MESSAGE);
                }

                FrameJoinChat.this.dispose();
            }
        });

    }
    //นายยงเกียรติ แสวงสุข 6430300790
    //นายยงเกียรติ แสวงสุข 6430300790
    public static void main(String[] args) {
        new FrameJoinChat("join");
    }
}
