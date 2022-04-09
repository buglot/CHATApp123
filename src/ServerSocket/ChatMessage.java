package ServerSocket;

import java.io.Serializable;

/*
 * This class defines the different type of messages that will be exchanged between the
 * Clients and the Server.
 * When talking from a Java Client to a Java Server a lot easier to pass Java objects, no
 * need to count bytes or to wait for a line feed at the end of the frame
 */

//นำโค้ดมาดัดแปลง
//https://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/
//แก้ไขเอง 170-210
//นายยงเกียรติ แสวงสุข 6430300790
public class ChatMessage implements Serializable {

    static final int  WHOISIN=0,MESSAGE = 1, LOGOUT = 2,LOGIN=3;
    private int type;
    private String message;

    // constructor
    public ChatMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    // getters
    int getType() {
        return type;
    }
    String getMessage() {
        return message;
    }
}
