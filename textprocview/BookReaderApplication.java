package textprocview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import textproc.GeneralWordCounter;
import textproc.TextProcessor;

public class BookReaderApplication {
    public static void main(String[] args) throws FileNotFoundException{

        

        Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set <String> UndantagsOrd = new HashSet<>();
		while (scan.hasNext()) {
			UndantagsOrd.add(scan.next().toLowerCase());
		}
		scan.close();

		GeneralWordCounter counter = new GeneralWordCounter(UndantagsOrd);

		Scanner s = new Scanner(new File("nilsholg.txt"), "UTF-8");
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			if(word.length() > 0) {
				counter.process(word);
		    }
		}
        
		s.close();
		BookReaderController controller = new BookReaderController(counter);
	}

        
}

