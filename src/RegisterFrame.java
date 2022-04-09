import zDo.clientRegister;
import zDo.doDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
//นายยงเกียรติ แสวงสุข 6430300790
public class RegisterFrame extends JFrame {
    private JButton regisCommit;
    private JTextField textFieldUsername;
    private JPasswordField passwordField, Re_passwordField;
    private JLabel label_register, label_username, label_password,label_RePasswords;
    private doDB db;
    public RegisterFrame(doDB db) {
        super("Register : ChatApp");
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        JPanel panel1 = new JPanel();
        label_register = new JLabel("Register");
        label_password = new JLabel("Password : ");
        label_username = new JLabel("Username : ");
        label_RePasswords = new JLabel("Re-Password : ");
        Font font1 = new Font("Angsana New", Font.PLAIN, 25);
        label_username.setFont(font1);
        label_password.setFont(font1);
        label_RePasswords.setFont(font1);
        label_register.setFont(new Font("Angsana New", Font.BOLD, 32));
        setSize(350, 270);
        panel1.add(label_register);
        textFieldUsername = new JTextField(20);
        passwordField = new JPasswordField(20);
        Re_passwordField = new JPasswordField(18);
        JPanel panel2 = new JPanel();
        panel2.add(label_username);
        panel2.add(textFieldUsername);
        JPanel panel3 = new JPanel();
        panel3.add(label_password);
        panel3.add(passwordField);
        JPanel panel4 = new JPanel();
        panel4.add(label_RePasswords);
        panel4.add(Re_passwordField);
        JPanel panel5 = new JPanel();
        regisCommit = new JButton("Register Now");
        panel5.add(regisCommit);
        c.add(panel1,BorderLayout.WEST);
        c.add(panel2);
        c.add(panel3);
        c.add(panel4);
        c.add(panel5);
        doButton aButton = new doButton();
        regisCommit.addActionListener(aButton);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.db=db;

    }
    //นายยงเกียรติ แสวงสุข 6430300790
    class doButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (textFieldUsername.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(RegisterFrame.this, "Enter Username and Password");
            } else {
                clientRegister register = new clientRegister(db,textFieldUsername.getText(),
                        passwordField.getPassword(),Re_passwordField.getPassword());
                try {
                    if(register.checkRePasswords()){
                        if(register.doThis()){
                            JOptionPane.showMessageDialog(RegisterFrame.this, "Successful registration");
                            RegisterFrame.this.setVisible(false);
                            RegisterFrame.this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(RegisterFrame.this, "Exists UserName");
                            textFieldUsername.setText("");
                        }
                    }else{
                        JOptionPane.showMessageDialog(RegisterFrame.this, "Passwords & Re-Passwords not same!");
                        passwordField.setText("");
                        Re_passwordField.setText("");
                    }

                }catch (SQLException sqlException){

                }

            }

        }
    }
    //นายยงเกียรติ แสวงสุข 6430300790
    public static void main(String[] args) {

    }
}
