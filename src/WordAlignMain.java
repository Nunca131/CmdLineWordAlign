import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;


public class WordAlignMain {

	public static void main(String[] args) throws IOException, InterruptedException
	{
		
		//read word lists on demand!
		String databaseFile = "";
		WordDatabase database = new WordDatabase();
		WordListReader.readFile(databaseFile, database);
	    
		
		
		
		//choose languages and/or words
		//run alignment with resp. list
		//choose output file name
		
		String outfile = "newfile.gz";
		
		// saves results as zipped stream
		FileOutputStream outStream = new FileOutputStream(outfile);
		GZIPOutputStream zipper = new GZIPOutputStream(outStream);
		PumpStreamHandler streamHandler = new PumpStreamHandler(zipper);
		
		String current = new java.io.File( "." ).getCanonicalPath();
		System.out.println(current);
		
		
		CommandLine cmd = new CommandLine("ls");
		
		
		DefaultExecutor executor = new DefaultExecutor();
		
		//writes to file
		executor.setStreamHandler(streamHandler);
		executor.execute(cmd);
		
		zipper.close();
		outStream.close();
		
		
		
		
		
		return;

	}

}
