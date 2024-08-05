package fileSystem;

import java.util.ArrayList;
import java.util.Collection;

public class FileInterpreter {

	public Collection<String[]> interpret(String content, Class<?> clazz) {
		int attributes = clazz.getDeclaredFields().length;

        Collection<String[]> result = new ArrayList<>();

        String[] lines = content.split("\\n");
        for (String line : lines) {
            String[] tuples = line.split(";");
            if(tuples.length == attributes){
                result.add(tuples);
            }
        }

        return result;
	}
	
}
