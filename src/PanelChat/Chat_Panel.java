package PanelChat;
//นายยงเกียรติ แสวงสุข 6430300790

import ServerSocket.ChatMessage;
import ServerSocket.ClientUa;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTextPane;

//นายยงเกียรติ แสวงสุข 6430300790
public class Chat_Panel extends JPanel {
    private BodyChat bodyChat;
    private windowsC WwindowsC;
    private ClientUa client;
    public Chat_Panel(String username,String title) {
        add(new JLabel("mainRealChat"));
        setBackground(new Color(58, 58, 58, 255));

        setLayout(new BorderLayout());
        bodyChat = new BodyChat(getBackground());
        JScrollPane Scroll = new JScrollPane(bodyChat);
        Scroll.getVerticalScrollBar().setUnitIncrement(9);
        add(new titleChat(title), BorderLayout.NORTH);
        add(Scroll,BorderLayout.CENTER);
        add(new sendBoxPanel(bodyChat),BorderLayout.SOUTH);
        Scroll.setBorder(BorderFactory.createEmptyBorder());
        //นายยงเกียรติ แสวงสุข 6430300790
        //นายยงเกียรติ แสวงสุข 6430300790

    }

    public ClientUa getClient() {
        return client;
    }

    public void setClient(ClientUa client) {
        this.client = client;
    }

    public BodyChat getBodyChat() {
        return bodyChat;
    }

    public windowsC getWwindowsC() {
        WwindowsC = new windowsC();
        return WwindowsC;
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    //นายยงเกียรติ แสวงสุข 6430300790

    private class titleChat extends JPanel {
        final private String spec = "      ";
        private JLabel title;

        public titleChat(String Title) {
            title = new JLabel(spec + Title);
            title.setFont(new Font("", Font.BOLD, 13));
            title.setForeground(new Color(126, 126, 126));
            setLayout(new GridLayout(0, 1));
            title.setPreferredSize(new Dimension(300, 45));
            setBackground(new Color(29, 29, 29));
            add(title);

        }

        public void setTitle(String txt) {
            title.setText(txt);
        }
    }

    public class BodyChat extends JPanel {

        public BodyChat(Color color) {
            setLayout(new MigLayout("fillx"));
            setBackground(color);

        }

        public void addItemTextLeft(String txt,String name) {
            Chat_item chat_left = new Chat_item( new Color(126, 127, 131));
            chat_left.setNameProfile(name,FlowLayout.LEFT);
            chat_left.setText(txt);
            add(chat_left, "wrap, w ::40%");
            repaint();
            scrollDown ();
            revalidate();

        }
        public void scrollDown (){
            int height = (int)getPreferredSize().getHeight();
            Rectangle rect = new Rectangle(0,height,20,20);
            scrollRectToVisible(rect);
        }
        public void addItemTextRight(String txt) {
            Chat_item chat_right = new Chat_item( new Color(0, 96, 201, 213));
            chat_right.setText(txt);
            add(chat_right, "wrap, al right, w ::40%");
            repaint();
            scrollDown ();
            revalidate();

        }
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    //นายยงเกียรติ แสวงสุข 6430300790
    public class Chat_item extends JLayeredPane {
        private JTextPane textPane;
        private JLayeredPane jLayeredPane;
        JLabel Name;
        public Chat_item(Color color) {
            jLayeredPane = new JLayeredPane();
            setLayout(new BoxLayout(Chat_item.this, BoxLayout.Y_AXIS));
            textPane = new TextBody();
            textPane.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
            textPane.setEditable(false);
            textPane.setOpaque(false);
            textPane.setForeground(Color.WHITE);
            textPane.setBackground(color);
            Name=new JLabel();
            add(jLayeredPane);
            add(textPane);

        }

        public void setText(String text) {
            textPane.setText(text);
        }
        public void setNameProfile(String NameProfile,int FlowlayoutInt){
            jLayeredPane.setLayout(new FlowLayout(FlowlayoutInt,0,0));
            jLayeredPane.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            Name.setText(NameProfile);
            Name.setForeground(new Color(0, 130, 255));
            Name.setFont(new Font("Cordia New", Font.PLAIN, 20));
            jLayeredPane.add(Name);

        }
    }

    //นายยงเกียรติ แสวงสุข 6430300790
    //นายยงเกียรติ แสวงสุข 6430300790
    class TextBody extends JTextPane {
        private class WarpEditorKit extends StyledEditorKit {

            private ViewFactory defaultFactory = new WarpColumnFactory();

            @Override
            public ViewFactory getViewFactory() {
                return defaultFactory;
            }
        }

        private class WarpColumnFactory implements ViewFactory {

            public View create(Element elem) {
                String kind = elem.getName();
                if (kind != null) {
                    if (kind.equals(AbstractDocument.ContentElementName)) {
                        return new WarpLabelView(elem);
                    } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                        return new ParagraphView(elem);
                    } else if (kind.equals(AbstractDocument.SectionElementName)) {
                        return new BoxView(elem, View.Y_AXIS);
                    } else if (kind.equals(StyleConstants.ComponentElementName)) {
                        return new ComponentView(elem);
                    } else if (kind.equals(StyleConstants.IconElementName)) {
                        return new IconView(elem);
                    }
                }

                return new LabelView(elem);
            }
        }

        private class WarpLabelView extends LabelView {

            public WarpLabelView(Element elem) {
                super(elem);
            }

            @Override
            public float getMinimumSpan(int axis) {
                switch (axis) {
                    case View.X_AXIS:
                        return 0;
                    case View.Y_AXIS:
                        return super.getMinimumSpan(axis);
                    default:
                        throw new IllegalArgumentException("Invalid axis:" + axis);
                }
            }
        }

        public TextBody() {
            setEditorKit(new WarpEditorKit());
            setFont(new Font("Cordia New", Font.PLAIN, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D a = (Graphics2D) g;
            a.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            a.setColor(getBackground());
            a.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            super.paintComponent(g);
        }
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    //นายยงเกียรติ แสวงสุข 6430300790
    private class sendBoxPanel extends JPanel {
        private BodyChat bodyChat;
        private TextBody sendBox;
        private Boolean hint;

        public sendBoxPanel(BodyChat bodyChat) {
            setLayout(new MigLayout("fillx, filly","",""));
            sendBox = new TextBody();
            JScrollPane scrollPane = new JScrollPane(sendBox);
            this.bodyChat = bodyChat;
            sendBox.setFont(new Font("Cordia New", Font.BOLD, 23));
            add(scrollPane,"w 100%");
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            sendBox.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            setLayout(new GridLayout(0, 1));
            setBackground(new Color(11, 26, 40));
            sendBox.setBackground(new Color(11, 26, 40));
            InputMap input = sendBox.getInputMap();
            sendBox.setForeground(new Color(144, 154, 169));
            KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
            KeyStroke shiftEnter = KeyStroke.getKeyStroke("shift ENTER");
            String TEXT_SUBMIT = "text-submit";
            String INSERT_BREAK = "insert-break";
            input.put(shiftEnter, INSERT_BREAK);  // input.get(enter)) = "insert-break"
            input.put(enter, TEXT_SUBMIT);
            ActionMap actions = sendBox.getActionMap();
            sendBox.setText("Send here....");
            hint = true;
            actions.put(INSERT_BREAK, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sendBox.setText(sendBox.getText() + "\n");
                    setSize(getWidth(), getHeight());
                }
            });
            actions.put(TEXT_SUBMIT, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!sendBox.getText().equals("") ) {
                        if(!sendBox.getText().equals("\n")){
                            bodyChat.addItemTextRight(sendBox.getText());
                            client.sendMessage(new ChatMessage(1, sendBox.getText()));
                            sendBox.setText("");
                        }

                    }

                }
            });
            sendBox.setToolTipText("Send here!");
            sendBox.addFocusListener(new focus());
            sendBox.addKeyListener(new keysendBox());

        }
        private class keysendBox extends KeyAdapter{
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                revalidate();
            }
        }
        private class focus extends FocusAdapter {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (hint) {
                    sendBox.setText("");
                    hint = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (sendBox.getText().length() == 0) {
                    sendBox.setText("Send here....");
                    hint = true;
                }
            }
        }
    }
    class windowsC extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            try {
                client.sendMessage(new ChatMessage(2, ""));
            }catch (Exception w){
                ;
            }
        }
    }
}
//นายยงเกียรติ แสวงสุข 6430300790
//นายยงเกียรติ แสวงสุข 6430300790