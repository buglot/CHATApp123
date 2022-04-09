import zDo.RememberToFile;
import zDo.clientManager;
import zDo.doDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
//นายยงเกียรติ แสวงสุข 6430300790
public class loginChat extends JFrame {
    private JButton bLogin, bRegister;
    private JLabel U_label, P_label, M_label;
    private JTextField textField;
    private JPasswordField passwordField;
    private JCheckBox reMember;
    private zDo.clientManager clientManager;
    public doDB db;
    public loginChat() {
        super("ChatApp : Login");
        setSize(500, 350);
        Container a = getContentPane();
        a.setLayout(new BoxLayout(a, BoxLayout.PAGE_AXIS));
        JPanel panel1 = new JPanel();
        U_label = new JLabel("Username:");
        P_label = new JLabel("Password: ");
        M_label = new JLabel("Login");
        textField = new JTextField(20);
        passwordField = new JPasswordField(20);
        bLogin = new JButton("Login");
        bRegister = new JButton("Register");
        reMember = new JCheckBox("Remember Username & Password");
        M_label.setFont(new Font("Angsana New", Font.BOLD, 40));
        Font font1 = new Font("Angsana New", Font.PLAIN, 25);
        Font font2 = new Font("Angsana New", Font.PLAIN, 20);
        bRegister.setFont(font2);
        bLogin.setFont(font2);
        P_label.setFont(font1);
        U_label.setFont(font1);
        JPanel gUsername = new JPanel();
        JPanel pPassword = new JPanel();
        JPanel bBtn = new JPanel();
        gUsername.add(U_label);
        bBtn.add(bLogin);
        bBtn.add(bRegister);
        gUsername.add(textField);
        pPassword.add(P_label);
        pPassword.add(passwordField);
        panel1.add(M_label);
        a.add(panel1);
        a.add(gUsername);
        a.add(pPassword);
        a.add(reMember);
        a.add(bBtn);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(350, 320));
        setLocationRelativeTo(null);

        String[] LoadTxt = RememberToFile.ReadsFile();
        textField.setText(LoadTxt[0]);
        passwordField.setText(LoadTxt[1]);
        reMember.setSelected(Boolean.valueOf(LoadTxt[2]));

        db =new doDB();

        clientManager = new clientManager(db);
        doButton aButton = new doButton();
        bLogin.addActionListener(aButton);
        bRegister.addActionListener(aButton);
    }

    class doButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bLogin) {
                if (!(textField.getText().equals("") || String.valueOf(passwordField.getPassword()).equals(""))) {
                    clientManager.setBoth(textField.getText(),passwordField.getPassword());
                    if (!ChatApp.OneShow()) {
                        try{
                            if(clientManager.doThis()){
                                String a = textField.getText();
                                new ChatApp(a);
                                RememberToFile.WriterFile(textField.getText(),String.valueOf(passwordField.getPassword()),reMember.isSelected());
                                loginChat.this.setVisible(false);
                            }else{
                                JOptionPane.showMessageDialog(loginChat.this,"Username or Password someone wrong!");
                            }
                        }catch (SQLException Ea){

                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(loginChat.this, "Enter Username & Passwords");
                }
            } else if (e.getSource() == bRegister) {

                new RegisterFrame(db);
            }


        }
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    public static void main(String[] args) {
        new loginChat();

    }

}
