package lesson3;

import java.io.*;

public class ReadData {
    public static void main(String[] args) throws IOException {
        FileReader fr=new FileReader("C:/Users/Administrator/Desktop/txt.txt");
        FileWriter fw=new FileWriter("C:/Users/Administrator/Desktop/output.txt");
        BufferedReader br=new BufferedReader(fr);
        String[] arrs=null;
        String line=null;
        while ((line=br.readLine())!=null){
//            line.trim();
//            String value = line.substring(line.indexOf("="), line.indexOf(">"));
//            String name=line.substring(line.indexOf())


        }
    }
}
