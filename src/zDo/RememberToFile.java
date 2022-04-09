package zDo;

import java.io.*;
import java.util.Scanner;

public class RememberToFile {
    public static String filename ="Save.txt";
    public static String[] ReadsFile(){
        String[] a = new String[3];
        try {
            Scanner rd = new Scanner(new File(filename));
            int count=0;
            while (rd.hasNext()){
                a[count]=rd.nextLine();
                count++;
            }
        }catch (FileNotFoundException e){
            a[0]="";
            a[1]="";
            a[2]="false";
        }
        return a;
    }
    public static void WriterFile(String username,String passwords,boolean checkbox){
        if(checkbox){
            try {
                FileWriter writer = new FileWriter(new File(filename));
                writer.write(username+"\n");
                writer.write(passwords+"\n");
                writer.write(String.valueOf(checkbox));
                writer.close();
            }catch (IOException E){
                ;
            }
        }else{
            try {
                FileWriter writer = new FileWriter(new File(filename));
                writer.write("");
                writer.close();
            }catch (IOException E){
                ;
            }
        }


    }

    public static void main(String[] args) {
       for(String x :ReadsFile()){
           System.out.println(x);
       }
       WriterFile("hi","pe",true);
    }
}
