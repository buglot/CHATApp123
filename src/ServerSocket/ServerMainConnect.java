package ServerSocket;


import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
//นำโค้ดมาดัดแปลง
//https://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/
//แก้ไขเอง 198-215
//main นำมาใช้เอง
//นายยงเกียรติ แสวงสุข 6430300790

public class ServerMainConnect {
    private static int uniqueId;
    // an ArrayList to keep the list of the Client
    private ArrayList<ClientThread> al;
    private int port;
    // the boolean that will be turned of to stop the server
    private boolean keepGoing;
    private static ServerMainConnect server;

    public ServerMainConnect(int port) {
        this.port = port;
        al = new ArrayList<ClientThread>();
    }

    public void start() {
        keepGoing = true;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (keepGoing) {

                display("Server waiting for Clients on port " + port + ".");
                Socket socket = serverSocket.accept();
                if (!keepGoing)
                    break;
                ClientThread t = new ClientThread(socket);  // make a thread of it
                al.add(t);                                    // save it in the ArrayList
                t.start();
            }
            try {
                serverSocket.close();
                for (int i = 0; i < al.size(); ++i) {
                    ClientThread tc = al.get(i);
                    try {
                        tc.sInput.close();
                        tc.sOutput.close();
                        tc.socket.close();
                    } catch (IOException ioE) {
                        // not much I can do
                    }
                }
            } catch (Exception e) {
                display("Exception closing the server and clients: " + e);
            }
        }
        // something went bad
        catch (IOException e) {
            String msg = " Exception on new ServerSocket: " + e + "\n";
            display(msg);
        }
    }

    /*
     * For the GUI to stop the server
     */
    protected void stop() {
        keepGoing = false;
        try {
            new Socket("localhost", port);
        } catch (Exception e) {
        }
    }

    private void display(String msg) {
        System.out.println(msg);

    }

    private synchronized void broadcast(String message) {
        // add HH:mm:ss and \n to the message

        String messageLf = message;
        // display message on console or GUI

        System.out.print(messageLf);


        for (int i = al.size(); --i >= 0; ) {
            ClientThread ct = al.get(i);
            if (!ct.writeMsg(messageLf)) {
                al.remove(i);
                display("Disconnected Client " + ct.username + " removed from list.");
            }
        }
    }

    synchronized void remove(int id) {
        // scan the array list until we found the Id
        for (int i = 0; i < al.size(); ++i) {
            ClientThread ct = al.get(i);
            // found it
            if (ct.id == id) {
                al.remove(i);
                return;
            }
        }
    }



    /**
     * One instance of this thread will run for each client
     */
    class ClientThread extends Thread {
        Socket socket;
        ObjectInputStream sInput;
        ObjectOutputStream sOutput;
        int id;
        String username;
        // the only type of message a will receive
        ChatMessage cm;

        ClientThread(Socket socket) {

            id = ++uniqueId;
            this.socket = socket;
            System.out.println("Thread trying to create Object Input/Output Streams");
            try {
                sOutput = new ObjectOutputStream(socket.getOutputStream());
                sInput = new ObjectInputStream(socket.getInputStream());
                // read the username
                username = (String) sInput.readObject();
                display(username + " just connected.");
            } catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            }
            // have to catch ClassNotFoundException
            // but I read a String, I am sure it will work
            catch (ClassNotFoundException e) {
            }

        }

        // what will run forever
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while (keepGoing) {
                // read a String (which is an object)
                try {
                    cm = (ChatMessage) sInput.readObject();
                } catch (IOException e) {
                    display(username + " Exception reading Streams: " + e);
                    break;
                } catch (ClassNotFoundException e2) {
                    break;
                }
                // the messaage part of the ChatMessage
                String message = cm.getMessage();

                // Switch on the type of message receive
                switch (cm.getType()) {
                    case ChatMessage.MESSAGE:
                        broadcast(ChatMessage.MESSAGE + " ###&#: " + username + " ###&#: " + message + "\n");
                        break;
                    case ChatMessage.LOGOUT:
                        broadcast(ChatMessage.LOGOUT + " ###&#: " + username + " ###&#: " + "disconnected with a LOGOUT message.\n");
                        keepGoing = false;
                        break;
                    case ChatMessage.LOGIN:
                        broadcast(ChatMessage.LOGIN + " ###&#: " + username + " ###&#: " + "connected with join message.\n");
                        break;
                    case ChatMessage.WHOISIN:
                        // scan al the users connected
                        for (int i = 0; i < al.size(); ++i) {
                            ServerMainConnect.ClientThread ct = al.get(i);
                            writeMsg(ChatMessage.WHOISIN + " ###&#: " + ct.username + " ###&#: " + "o");
                        }
                        break;
                }
            }

            remove(id);
            close();
        }

        // try to close everything
        private void close() {
            // try to close the connection
            try {
                if (sOutput != null) sOutput.close();
            } catch (Exception e) {
            }
            try {
                if (sInput != null) sInput.close();
            } catch (Exception e) {
            }
            ;
            try {
                if (socket != null) socket.close();
            } catch (Exception e) {
            }
        }

        /*
         * Write a String to the Client output stream
         */
        private boolean writeMsg(String msg) {
            // if Client is still connected send the message to it
            if (!socket.isConnected()) {
                close();
                return false;
            }
            // write the message to the stream
            try {
                sOutput.writeObject(msg);
            }
            // if an error occurs, do not abort just inform the user
            catch (IOException e) {
                display("Error sending message to " + username);
                display(e.toString());
            }
            return true;
        }
    }
    //นำโค้ดมาดัดแปลง
    //https://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/
    //แก้ไขเอง 198-215
    //main นำมาใช้เอง
    //นายยงเกียรติ แสวงสุข 6430300790

    public static void main(String[] args) {
        System.out.println("Port Server What do you want\n------------");
        System.out.print("Port Server :");
        Scanner scanner = new Scanner(System.in);
        int port = scanner.nextInt();
        while (port < 0) {
            System.out.print("Port Server :");
            port = scanner.nextInt();
        }
        server = new ServerMainConnect(port);
        // and start it as a thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.start();         // should execute until if fails
                // the server failed

                server = null;
            }
        }).start();
    }
}

