package ServerSocket;

import PanelChat.Chat_Panel;
import PanelChat.MenuChat_Panel;
import PanelChat.PaneNameUser;
import PanelChat.myJScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/*
 * The Client that can be run both as a console or a GUI
 */

//นำโค้ดมาดัดแปลง
//https://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/
//แก้ไขเอง 170-210
//นายยงเกียรติ แสวงสุข 6430300790

public class ClientUa {

    // for I/O
    private ObjectInputStream sInput;        // to read from the socket
    private ObjectOutputStream sOutput;        // to write on the socket
    private Socket socket;

    // if I use a GUI or not
    private Chat_Panel cg;
    private JPanel newAccountlist;
    // the server, the port and the username
    private String server, username;
    private int port;
    HashMap<String,PaneNameUser> ProfileA;
    /*
     *  Constructor called by console mode
     *  server: the server address
     *  port: the port number
     *  username: the username
     */
    public ClientUa(String server, int port, String username) {
        // which calls the common constructor with the GUI set to null
        this(server, port, username, null,null);
    }

    /*
     * Constructor call when used from a GUI
     * in console mode the ClienGUI parameter is null
     */
    public ClientUa(String server, int port, String username, Chat_Panel cg, HashMap<String ,myJScrollPane> acc) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.newAccountlist=acc.get(server+":"+port).getPanel();
        // save if we are in GUI mode or not
        this.cg = cg;
        ProfileA = new HashMap<>();
    }

    /*
     * To start the dialog
     */
    public boolean start() {
        // try to connect to the server
        try {
            socket = new Socket(server, port);
        }
        // if it failed not much I can so
        catch (Exception ec) {
            display("Error connectiong to server:" + ec);
            return false;
        }

        String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
        display(msg);

        /* Creating both Data Stream */
        try {
            sInput = new ObjectInputStream(socket.getInputStream());
            sOutput = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        // creates the Thread to listen from the server
        new ListenFromServer().start();
        // Send our username to the server this is the only message that we
        // will send as a String. All other messages will be ChatMessage objects
        try {
            sOutput.writeObject(username);
        } catch (IOException eIO) {
            display("Exception doing login : " + eIO);
            disconnect();
            return false;
        }
        // success we inform the caller that it worked
        return true;
    }

    /*
     * To send a message to the console or the GUI
     */
    private void display(String msg) {
        if (cg == null)
            System.out.println(msg);      // println in console mode
        else
            cg.getBodyChat().addItemTextLeft(msg, "Server");        // append to the ClientGUI JTextArea (or whatever)
    }

    /*
     * To send a message to the server
     */
    public void sendMessage(ChatMessage msg) {
        try {
            sOutput.writeObject(msg);
        } catch (IOException e) {
            display("Exception writing to server: " + e);
        }
    }

    /*
     * When something goes wrong
     * Close the Input/Output streams and disconnect not much to do in the catch clause
     */
    public void disconnect() {
        try {
            if (sInput != null) sInput.close();
        } catch (Exception e) {
        } // not much else I can do
        try {
            if (sOutput != null) sOutput.close();
        } catch (Exception e) {
        } // not much else I can do
        try {
            if (socket != null) socket.close();
        } catch (Exception e) {
        } // not much else I can do

        // inform the GUI
        if (cg != null)
            cg.getBodyChat().addItemTextLeft("Disconnected", "Server");

    }

    /*
     * To start the Client in console mode use one of the following command
     * > java Client
     * > java Client username
     * > java Client username portNumber
     * > java Client username portNumber serverAddress
     * at the console prompt
     * If the portNumber is not specified 1500 is used
     * If the serverAddress is not specified "localHost" is used
     * If the username is not specified "Anonymous" is used
     * > java Client
     * is equivalent to
     * > java Client Anonymous 1500 localhost
     * are eqquivalent
     *
     * In console mode, if an error occurs the program simply stops
     * when a GUI id used, the GUI is informed of the disconnection
     */
    /*
     * a class that waits for the message from the server and append them to the JTextArea
     * if we have a GUI or simply System.out.println() it in console mode
     */
    class ListenFromServer extends Thread {
        public void run() {
            while (true) {
                try {
                    String msg = (String) sInput.readObject();
                    String[] NewMsg = msg.split(" ###&#: ");
                    String NewMsg2 = NewMsg[2];
                    if(NewMsg[2].endsWith("\n")){
                        NewMsg2 = NewMsg[2].substring(0,NewMsg[2].lastIndexOf('\n'));
                    }
                    switch (NewMsg[0]) {
                        case "1":
                            if (!username.equals(NewMsg[1])) {
                                cg.getBodyChat().addItemTextLeft(NewMsg2, NewMsg[1]);
                            }
                            break;
                        case "2":
                            cg.getBodyChat().addItemTextLeft(NewMsg[1]+" "+NewMsg2, "Server");
                            newAccountlist.remove(ProfileA.get(NewMsg[1]));
                            newAccountlist.revalidate();
                            newAccountlist.repaint();
                            break;
                        case "3":
                            if (!username.equals(NewMsg[1])) {
                                cg.getBodyChat().addItemTextLeft(NewMsg[1]+" "+NewMsg2, "Server");
                                Color color = new Color(65,65,65);
                                if(username.equals(NewMsg[1])){
                                    color = new Color(48, 114, 140);
                                }
                                ProfileA.put(NewMsg[1],new PaneNameUser(NewMsg[1],color));
                                newAccountlist.add(ProfileA.get(NewMsg[1]),"wrap , w :93%");
                                newAccountlist.revalidate();
                                newAccountlist.repaint();
                            }
                            break;
                        case "0":
                            Color color = new Color(65,65,65);
                            if(username.equals(NewMsg[1])){
                                color = new Color(48, 114, 140);
                            }
                            ProfileA.put(NewMsg[1],new PaneNameUser(NewMsg[1],color));
                            newAccountlist.add(ProfileA.get(NewMsg[1]),"wrap , w :93%");
                            newAccountlist.revalidate();
                            newAccountlist.repaint();
                            break;
                    }


            } catch(IOException e){
                display("Server has close the connection: " + e);

                cg.getBodyChat().addItemTextLeft("connectionFailed", "Server");
                break;
            }
            // can't happen with a String object but need the catch anyhow
                catch(ClassNotFoundException e2){
            }
        }
    }
}
}
