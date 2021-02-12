
//http://www.tutorialspoint.com/tika/tika_language_detection.htm
//https://www.apache.org/dyn/closer.cgi/tika/tika-app-2.0.0-ALPHA.jar
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.language.*;

import org.xml.sax.SAXException;

public class LanguageDetectionTika {


	public static void main(final String[] args) throws IOException, SAXException, TikaException {
		
		String inputfolder = args[0];
		System.out.println(inputfolder);
		String outputfile = args[1];
	//zia String directory = "C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed";
		// Parser method parameters
		Parser parser = new AutoDetectParser();
		Metadata metadata = new Metadata();
	

		// File.separator = "\\";
		//String dirPath = "C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\English\\";
	//String dirPath = "C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\German\\";
	//	String dirPath = "C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\French\\";
		//String dirPath = "C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\Portuguese\\";
	//Zia 	String dirPath = "C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\Spanish\\";

		File dir = new File(inputfolder);
		File[] firstLevelFiles = dir.listFiles();
		for (File xFile : firstLevelFiles) {
			if (xFile.getName().endsWith(".cermtxt") | xFile.getName().endsWith(".cermzones")) {

				System.out.println(xFile.getName());

				FileInputStream content = new FileInputStream(xFile.getAbsolutePath()); // .getName());
				
				StringWriter any = new StringWriter();

				BodyContentHandler handler = new BodyContentHandler(any);

				parser.parse(content, handler, metadata, new ParseContext());

				LanguageIdentifier object = new LanguageIdentifier(handler.toString());
				System.out.println("\t" + object.getLanguage());

				java.io.File newFile = new File(outputfile + xFile.getName());
				if (object.getLanguage().equalsIgnoreCase( "hu"))		
				{	//System.out.println(newFile.toPath());
					Files.copy( xFile.toPath(), newFile.toPath() );
				}
		
			}
		}


	}
}