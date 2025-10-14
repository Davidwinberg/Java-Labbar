package textproc;

import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		ArrayList <TextProcessor> wordList = new ArrayList <TextProcessor>();
		TextProcessor nils = new SingleWordCounter("nils");
		wordList.add(nils);
		TextProcessor norge = new SingleWordCounter("norge");
		wordList.add(norge);
		TextProcessor landskap = new MultiWordCounter(REGIONS);
		wordList.add(landskap);
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set <String> UndantagsOrd = new HashSet<>();
		while (scan.hasNext()) {
			UndantagsOrd.add(scan.next().toLowerCase());
		}
		scan.close();
		
		TextProcessor topOrd = new GeneralWordCounter(UndantagsOrd);
		wordList.add(topOrd);

		Scanner s = new Scanner(new File("nilsholg.txt"), "UTF-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for(TextProcessor wordInList : wordList) {
				wordInList.process(word);
		}
		}

		s.close();
		for(TextProcessor wordInList : wordList) {
			wordInList.report();
		}
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");

}}
