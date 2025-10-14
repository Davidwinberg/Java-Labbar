package textproc;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class MultiWordCounter implements TextProcessor {
    private Map <String,Integer> wordsToCheck = new TreeMap();

    public MultiWordCounter(String[] wordsToCheck){
        for(String word: wordsToCheck){
            this.wordsToCheck.put(word, 0);
        }
        
    }
    @Override
	public void process(String w) {
		if (wordsToCheck.containsKey(w)) {  // inte w == word utan w.equals(word) när det är strings
			wordsToCheck.put(w, wordsToCheck.get(w)+1);
		}
	}
    @Override
    public void report() {
		for(String key : wordsToCheck.keySet())
        System.out.println(key + ": " + wordsToCheck.get(key));
	}
}



	





