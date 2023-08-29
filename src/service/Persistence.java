package service;

import java.io.*;
import java.util.*;

public class Persistence {

    private static final String path="src/data/logs.txt";
    public static void saveLogs(Stack<String> logs) throws IOException {
        String content="";
        while (!logs.isEmpty()){
            content+= logs.pop()+"\n";
        }
        FileWriter fw = new FileWriter(path,false);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(content);
        bfw.close();
        fw.close();
    }
    public static Stack<String> getLogs() throws IOException {
        ArrayList<String> content = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader bfr = new BufferedReader(fr);
        String line;
        while ((line = bfr.readLine()) !=null){
          content.add(line);
        }
        bfr.close();
        fr.close();
        Collections.reverse(content);
        Stack<String> logs = new Stack<String>();
        for (String l:content ) {
            logs.push(l);
        }
      return  logs;
    }

}
