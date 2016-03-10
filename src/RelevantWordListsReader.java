import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RelevantWordListsReader {

	public static void readWords(String language, BufferedReader inList, PrintWriter writer) throws IOException{
		
		String line = "";
		
		while(inList.ready()){
			line = inList.readLine();
			if(line.contains(language))
				writer.println(line);
		}
		
	}
	
public static void readWords(String language1, String language2, BufferedReader inList, PrintWriter writer) throws IOException{
		
		String line = "";
		
		while(inList.ready()){
			line = inList.readLine();
			if(line.contains(language1) || line.contains(language2))
				writer.println(line);
		}
		
	}
	
}
