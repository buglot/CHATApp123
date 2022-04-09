package zDo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class clientManager {
    private doDB db;
    private String passwords, username, DB_username, DB_passwords;

    public clientManager(doDB db) {
        this.db = db;
    }

    public clientManager(doDB db,String username, char[] passwords) {
        this.passwords = String.valueOf(passwords);
        this.username = username;
        this.db =db;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public void setBoth(String username,char[] passwords){
        setUsername(username);
        setPasswords(String.valueOf(passwords));
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPasswords(String passwords) {
        if (this.passwords.equals(passwords)) {
            return true;
        }
        return false;
    }

    public boolean existsUserName(String username) {
        if (this.username.equals(username)) {
            return true;
        }
        return false;
    }

    public String getPasswords() {
        return passwords;
    }

    public String getUsername() {
        return username;
    }

    public doDB getDb() {
        return db;
    }

    public String getDB_username() {
        return DB_username;
    }

    public String getDB_passwords() {
        return DB_passwords;
    }

    public void SearchDB(String query) {
        try {
            ResultSet er = db.doSearchDB(query, getUsername());
            while (er.next()) {
                DB_username = er.getString(doDB.ColumnLabel_username);
                DB_passwords = er.getString(doDB.ColumnLabel_passwords);
            }
        }catch (SQLException ee){
            DB_username=null;
            DB_passwords=null;
        }

    }

    public boolean doThis() throws SQLException {
        boolean a = false;
        SearchDB(doDB.querySearch);
        if (existsUserName(DB_username) && checkPasswords(DB_passwords)) {
            a = true;
        } else {
            a = false;
        }
        return a;
    }

}
