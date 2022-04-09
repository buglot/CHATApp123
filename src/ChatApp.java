import PanelChat.Chat_Panel;
import PanelChat.MenuChat_Panel;
import PanelChat.blankMenu_Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
//นายยงเกียรติ แสวงสุข 6430300790

public class ChatApp extends JFrame {
    private static boolean defOne = false;
    private static ChatApp chatApp;
    private HashMap<String,Chat_Panel> CollectChat_Panel;
    public ChatApp(String username) {
        super("ChatApp");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        defOne = isVisible();
        setLocation(60, 50);
        addWindowListener(new doWindow());
        Container e = getContentPane();
        e.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());

        CollectChat_Panel  = new HashMap<>();
        MenuChat_Panel menuChat_panel= new MenuChat_Panel(username,CollectChat_Panel,panel);
        CollectChat_Panel.put("0",new Chat_Panel(username,"None Connection"));
        panel.add(CollectChat_Panel.get("0"),"0");
        e.add(panel, BorderLayout.CENTER);
        e.add(menuChat_panel, BorderLayout.WEST);
        e.add(new blankMenu_Panel(), BorderLayout.EAST);
        CardLayout cl = (CardLayout)panel.getLayout();
        cl.show(panel,"0");
        menuChat_panel.addAccount(username,"0");
        setVisible(true);
        addWindowListener(CollectChat_Panel.get("0").getWwindowsC());
    }

    class doWindow extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            super.windowClosed(e);
            defOne = false;
        }
    }

    public static boolean OneShow() {
        return defOne;
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                chatApp = new ChatApp("UnknownsName_test");
                chatApp.setVisible(true);
            }
        });

    }
}
