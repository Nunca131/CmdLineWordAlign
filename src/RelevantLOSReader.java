import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RelevantLOSReader {

	public static void readLOS(String language, BufferedReader inLOS, PrintWriter writer) throws IOException{
		
		String line = "";
		
		while(inLOS.ready()){
			line = inLOS.readLine();
			if(line.contains(language + " " + language))
				writer.println(line);
		}
		
	}
	
	
public static void readLOS(String language1, String language2, BufferedReader inLOS, PrintWriter writer) throws IOException{
		
		String line = "";
		
		while(inLOS.ready()){
			line = inLOS.readLine();
			if(line.contains(language1 + " " + language2))
				writer.println(line);
		}
		
	}
	
}
