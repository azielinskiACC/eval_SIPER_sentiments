
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;

import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.io.RuntimeIOException;
import edu.stanford.nlp.ling.*;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;



public class SentimentEvaluationsSIPER {

	static TObjectIntMap<String> tf = new TObjectIntHashMap<String>(10000000);

	static Set<String> testDescriptions = new LinkedHashSet<String>();

	static Map<String, Double> map = new HashMap<String, Double>();
//	static Map<Integer, String> mapfname = new HashMap<Integer, String>();

	static Properties EnglishProperties = new Properties() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{

			try (InputStream is = IOUtils
					.getInputStreamFromURLOrClasspathOrFileSystem("StanfordCoreNLP-english.properties")) {
				load(is);

			} catch (IOException e) {
				throw new RuntimeIOException(e);
			}

		}
	};



	public static List<String> listDirectory(String directory, int level, List<String> completeFileList) {
		File dir = new File(directory);
		File[] firstLevelFiles = dir.listFiles();
		if (firstLevelFiles != null && firstLevelFiles.length > 0) {
			for (File aFile : firstLevelFiles) {
				if (aFile.isDirectory()) {
					// System.out.println("[" + aFile.getName() + "]");
					List<String> m = listDirectory(aFile.getAbsolutePath(), level + 1, completeFileList);
				} else {
					if (aFile.getName().endsWith(".cermtxt") | aFile.getName().endsWith(".cermzones")) {
						completeFileList.add(aFile.getAbsolutePath());
					}
				}
			}
		}
		return completeFileList;

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {

		// OUTER LOOP
		// read abstracts from DB from gold standard

		BufferedWriter writer = new BufferedWriter(
				new FileWriter("C:\\ISI\\032 ISDEC Siper\\WP 1\\results\\outputMatchFinal.csv", true));

		// id name abstract_1
		//File file = new File("C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\1998-403.cermtxt");
		File file = new File("C:\\ISI\\032 ISDEC Siper\\AG_SIPER\\Data\\Docs_preprocessing_renamed\\English-results\\English-hits5.txt");
		// 1997-56

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		BufferedReader SW = new BufferedReader(isr);

		String text = "";
		/*
		 * // if (args.length > 0) { if (text.length() == 0) { // annotation = new
		 * Annotation(IOUtils.slurpFileNoExceptions(args[0])); text =
		 * IOUtils.slurpFileNoExceptions(file); } else {
		 * System.out.println("Single Sentence"); //text =
		 * "The availability of long-term career paths thereafter is less than clear."
		 * text =
		 * "Given the current economic conditions, the risks of not achieving the full public sector legacy targets are considered to be moderate for all three funds."
		 * ;
		 * 
		 * }
		 */

		
		//text = "Given the current economic conditions, the risks of not achieving the full public sector legacy targets are considered to be moderate for all three funds.";
		text = "Both the review and the evaluation of the NANOMAT programme stressed the need for more generous long-term funding and that increased efforts were needed to harvest commercial results in the form of patents, new companies and innovations to a greater extent than was achieved under the NANOMAT programme.";		
		
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		// <...>
		Annotation annotation = pipeline.process(text);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
			System.out.println(sentiment + "\t" + sentence);
			
			Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	      System.out.println();
		    tree.pennPrint();
		
		}

		// writer.close();
	} // for

}