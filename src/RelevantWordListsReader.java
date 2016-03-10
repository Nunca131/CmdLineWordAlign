import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RelevantWordListsReader {

	public static void readWords(String language, BufferedReader inList, PrintWriter writer) throws IOException{
		
		int counter = 0;
		String line = "";
		
		while(inList.ready()){
			line = inList.readLine();
			if(line.contains(language))
				counter++;
				writer.println(line);
		}
		if(counter == 0)
			System.out.println("There were no entries for " + language);
	}
	
public static void readWords(String language1, String language2, BufferedReader inList, PrintWriter writer) throws IOException{
		
		String line = "";
		int counter1 = 0;
		int counter2 = 0;
		
		while(inList.ready()){
			line = inList.readLine();
			if(line.contains(language1)){
				writer.println(line);
				counter1++;
			}
				
			
			if(line.contains(language2)){
				writer.println(line);
				counter2++;
			}
		}

		if(counter1 == 0)
			System.out.println("There were no entries for " + language1);
		if(counter2 == 0)
			System.out.println("There were no entries for " + language2);
	}
	
}
