import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RelevantLOSReader {

	public static void readLOS(String language, BufferedReader inLOS, PrintWriter writer) throws IOException{
		
		String line = "";
		int counter = 0;
		
		while(inLOS.ready()){
			line = inLOS.readLine();
			if(line.contains(language + " " + language)){
				writer.println(line);
				counter++;
			}
		}
		
		if(counter == 0)
			System.out.println("There were no log-odd scores for " + language);
	}
	
	
public static void readLOS(String language1, String language2, BufferedReader inLOS, PrintWriter writer) throws IOException{
		
		String line = "";
		int counterDiff = 0;
//		int counter1 = 0;
//		int counter2 = 0;
		
		while(inLOS.ready()){
			line = inLOS.readLine();
			if(line.contains(language1 + " " + language2)){
				writer.println(line);
				counterDiff++;
			}
//			if(line.contains(language1 + " " + language1)){
//				writer.println(line);
//				counter1++;
//			}
//			if(line.contains(language2 + " " + language2)){
//				writer.println(line);
//				counter2++;
//			}
		}
		
		if(counterDiff == 0) 
			System.out.println("There were no log-odd scores for pair " + language1 + " and " + language2);
//		if(counter1 == 0)
//			System.out.println("There were no log-odd scores for " + language1);
//		if(counter2 == 0)
//			System.out.println("There were no log-odd scores for " + language2);
	}
	
}
