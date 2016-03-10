import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.GZIPOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;


public class WordAlignMain {

	
	
	
	
	
	public static String[] files = {
			"AlbanianTosk"
			
	}; 
	
	public static void main (String[] args) throws IOException
	{
		
		/*
		 * read args
		 * go through WordLists and keep only language of interest (maybe also with semantic id?)
		 * analogously for los File
		 * write two new files and a script (which also deletes these new files)
		 * 
		 */
		if(files.length == 0){
			System.err.println("No input data");
		}
			
		if(files.length == 1){
			
			String wordListFile = "Indo-European.list";
			String losFile = "Indo-European.los";
			
			String relevantWordsFile = files[0] + ".list";
			String relevantLOSFile = files[0] + ".los";
			
			
			try{
				FileReader wordlists = new FileReader(wordListFile);
				BufferedReader inList = new BufferedReader(wordlists);
				PrintWriter relevantWords = new PrintWriter(relevantWordsFile, "UTF-8");
				
				RelevantWordListsReader.readWords(files[0], inList, relevantWords);
				
				wordlists.close();
				relevantWords.close();
				
				
			} catch (FileNotFoundException e) {
				System.err.println("Could not find the file " + wordListFile);
			} catch (IOException e) {
				System.err.println("There was an error processing the file " + wordListFile);
			}

			try{
				FileReader los = new FileReader(losFile);
				BufferedReader inLOS = new BufferedReader(los);
				PrintWriter relevantLOS = new PrintWriter(relevantLOSFile, "UTF-8");
				
				RelevantLOSReader.readLOS(files[0], inLOS, relevantLOS);
				
				los.close();
				relevantLOS.close();
				
			} catch (FileNotFoundException e) {
				System.err.println("Could not find the file " + losFile);
			} catch (IOException e) {
				System.err.println("There was an error processing the file " + losFile);
			}
			
			
			String cmdFileName = "aln_commandlines.sh";
			
			try{
				
				PrintWriter cmdWriter = new PrintWriter(cmdFileName, "UTF-8");
				String outString = "cat " + relevantWordsFile + " | ./WordAlign twoway "
						+ "--scorefile " + relevantLOSFile + " --bigramdef=-3.5 --gapopen=-4 --gapextend=-1 "
								+ "| gzip >" + files[0] + ".aln.gz";
				
				cmdWriter.println(outString);
				cmdWriter.println("rm " + relevantWordsFile);
				cmdWriter.println("rm " + relevantLOSFile);
				
				cmdWriter.close();
				
				Process p =Runtime.getRuntime().exec("/bin/sh aln_commandlines.sh");
				p.waitFor();
				
			} catch (FileNotFoundException e) {
				System.err.println("Could not find the file " + cmdFileName);
			} catch (IOException e) {
				System.err.println("There was an error processing the file " + cmdFileName);
			} catch (InterruptedException e){
				System.err.println("Alignment failed");
			}
			

		}
		
		if(files.length >= 2){
			
			//sort files alphabetically
			ArrayList<String> languages = new ArrayList<String>();
			
			for(int i = 0; i < files.length; i++)
				languages.add(files[i]);
			Collections.sort(languages);
			
			
			String wordListFile = "Indo-European.list";
			String losFile = "Indo-European.los";
			
			for(int i = 0; i < languages.size() -1; i++){
				
				for(int j = 0; j < languages.size(); j++){
					
					String relevantWordsFile = languages.get(i) + "_" + languages.get(j) + ".list";
					String relevantLOSFile = languages.get(i) + "_" + languages.get(j) + ".los";
					

					try{
						FileReader wordlists = new FileReader(wordListFile);
						BufferedReader inList = new BufferedReader(wordlists);
						PrintWriter relevantWords = new PrintWriter(relevantWordsFile, "UTF-8");
						
						RelevantWordListsReader.readWords(languages.get(i), languages.get(j), inList, relevantWords);
						
						wordlists.close();
						relevantWords.close();
						
						
					} catch (FileNotFoundException e) {
						System.err.println("Could not find the file " + wordListFile);
					} catch (IOException e) {
						System.err.println("There was an error processing the file " + wordListFile);
					}
				
					try{
						FileReader los = new FileReader(losFile);
						BufferedReader inLOS = new BufferedReader(los);
						PrintWriter relevantLOS = new PrintWriter(relevantLOSFile, "UTF-8");
						
						RelevantLOSReader.readLOS(languages.get(i), languages.get(j), inLOS, relevantLOS);
						
						los.close();
						relevantLOS.close();
						
					} catch (FileNotFoundException e) {
						System.err.println("Could not find the file " + losFile);
					} catch (IOException e) {
						System.err.println("There was an error processing the file " + losFile);
					}
					
					String cmdFileName = "aln_commandlines.sh";
					
					try{
						
						PrintWriter cmdWriter = new PrintWriter(cmdFileName, "UTF-8");
						String outString = "cat " + relevantWordsFile + " | ./WordAlign twoway "
								+ "--scorefile " + relevantLOSFile + " --bigramdef=-3.5 --gapopen=-4 --gapextend=-1 "
										+ "| gzip >" + languages.get(i) + "_" + languages.get(j) + ".aln.gz";
						
						cmdWriter.println(outString);
						cmdWriter.println("rm " + relevantWordsFile);
						cmdWriter.println("rm " + relevantLOSFile);
						
						cmdWriter.close();
						
						System.out.println("Starting pairwise alignment of " + languages.get(i) + " and " +
								languages.get(j) + "\\.\\.\\.");
						
						Process p =Runtime.getRuntime().exec("/bin/sh aln_commandlines.sh");
						p.waitFor();
						
					} catch (FileNotFoundException e) {
						System.err.println("Could not find the file " + cmdFileName);
					} catch (IOException e) {
						System.err.println("There was an error processing the file " + cmdFileName);
					} catch (InterruptedException e){
						System.err.println("Alignment failed");
					}
					
				}
				
			}
		}
		
		
		return;

	}

}
