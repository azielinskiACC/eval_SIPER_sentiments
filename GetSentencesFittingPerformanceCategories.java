import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.io.RuntimeIOException;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CollectionUtils;
import edu.stanford.nlp.util.CoreMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class GetSentencesFittingPerformanceCategories {

	public static List<String> getCommonWordsInString(String text, List<String> terminology) {
		List<String> overlap = new ArrayList<>();

		List<String> words = Arrays.asList(text.split(" "));
		for (String word : words) {
			if (terminology.contains(word) == true)
				overlap.add(word);
		}
		;
		return overlap;
	}

	static Properties EnglishProperties = new Properties() {
		// static Properties GermanProperties = new Properties() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{

			try (InputStream is = IOUtils.getInputStreamFromURLOrClasspathOrFileSystem(
					// "C:\\Users\\zia\\eclipse-workspace\\EFI-Annotator\\FH-ISI\\StanfordCoreNLP-german-split.properties"))
					// {
					"C:\\Users\\zia\\eclipse-workspace\\EFI-Annotator\\FH-ISI\\StanfordCoreNLP-english-split.properties")) {
				load(is);

			} catch (IOException e) {
				throw new RuntimeIOException(e);
			}

		}
	};
	// static StanfordCoreNLP pipeline = new StanfordCoreNLP(GermanProperties);
	static StanfordCoreNLP pipeline = new StanfordCoreNLP(EnglishProperties);

	public static void main(String[] args) throws IOException {
		String inputfolder = args[0];
		System.out.println(inputfolder);
		String outputfile = args[1];

		String terminologyfile = args[2];

		List<String> terminology = new ArrayList<String>();
		List<String> overlap = new ArrayList<String>();
		Set<String> terminologySet = new HashSet<>(100);

		BufferedWriter out = new BufferedWriter(new FileWriter(outputfile));

		try {
			terminology = new BufferedReader(new FileReader(terminologyfile)).lines().collect(Collectors.toList());

			CollectionUtils.addAll(terminologySet, terminology);

		} catch (FileNotFoundException ex) {
		}

		int no = 0;

		File dir = new File(inputfolder);
		File[] firstLevelFiles = dir.listFiles();
		for (File xFile : firstLevelFiles) {
			if (xFile.getName().endsWith(".cermtxt") | xFile.getName().endsWith(".cermzones")) {

				System.out.println(xFile.getName());

				FileInputStream content = new FileInputStream(xFile.getAbsolutePath());

				String text = IOUtils.slurpFile(xFile, "utf-8");

				// Writer out = new BufferedWriter(new OutputStreamWriter(new
				// FileOutputStream(outputfile), "UTF-8"));

				List<String> slicedText = slice(text); // slice(text);
				// System.out.println(slicedText.size());

				for (String sentence : slicedText) {

					overlap = getCommonWordsInString(sentence, terminology);
					
					if  (overlap == null || overlap.isEmpty()) {
						System.out.println(sentence);
					}
					else
						out.write(xFile.getName() + "\t" + overlap.size() + "\t" +overlap + "\t" + sentence.toString() + "\n");

					 // if
				} // for
			} // if
		} // for
		out.close();

	} // main

	private static List<String> slice(String text) {

		List<String> lines = new LinkedList<String>();
		// Create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		pipeline.annotate(document);
		// Iterate over all of the sentences found
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			String mySentence = sentence.toString();
			String mySentence2 = mySentence.replace("\n", " ").replace("\r", " ").replace("..", "").replace("_", "");
			lines.add(mySentence2);
		}

		return lines;
	};

}