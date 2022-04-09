package zDo;

import java.sql.SQLException;

public class clientRegister extends clientManager {
    private String Re_passwords;
    public clientRegister(doDB db,String userName, char[] passwords, char[] Re_passwords) {
        super(db,userName,passwords);
        this.Re_passwords =String.valueOf(Re_passwords);
    }

    public clientRegister(doDB db) {
        super(db);
    }

    public boolean checkRePasswords(){
        boolean check = false;
        if(getPasswords().equals(Re_passwords)){
            check = true;
        }else{
            check = false;
        }
        return  check;
    }

    public void setRe_passwords(String re_passwords) {
        Re_passwords = re_passwords;
    }


    @Override
    public boolean doThis() throws SQLException {
        boolean a;
        SearchDB(doDB.querySearch);
        if(!existsUserName(getDB_username())){
            getDb().InsertDB(doDB.queryInsert,getUsername(),getPasswords());
            a=true;
        }else{
            a=false;
        }
        return a;
    }
}
