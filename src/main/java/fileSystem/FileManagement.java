package fileSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManagement {

	private final String filepath;

    public FileManagement(String filepath) {
        this.filepath = filepath;
    }

    public void write(Object o){
        try {
            FileWriter fw = new FileWriter(filepath, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(o);
            pw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String read(){
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void clear(){
        try {
            FileWriter fw = new FileWriter(filepath, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.print("");
            pw.close();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getFilepath() {
        return filepath;
    }
	
}
