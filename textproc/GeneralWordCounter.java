package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{
    private Map <String,Integer> words = new TreeMap<>();
    private Set <String> undantag;
    
    public GeneralWordCounter(Set<String> undantag) {
        this.undantag = undantag;
        }
        
    
    @Override
	public void process(String w) {
		if (!undantag.contains(w)) {
			words.put(w, words.getOrDefault(w,0)+1);
        }
    }
    @Override
    public void report() {
		Set<Map.Entry<String, Integer>> wordSet = words.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
     
        
      //Sorterar efter värden där högst kommer först, alfabetiskt då de har samma värde
        wordList.sort((e1, e2) -> {
            int compare = e2.getValue().compareTo(e1.getValue());
            if (compare != 0){
                return compare;
            }
            return e1.getKey().compareTo(e2.getKey());
        }); 
        
        int limit = Math.min(5, wordList.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = wordList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        
        //for(String key: words.keySet()){
          //  if(words.get(key) >= 200){
            //    System.out.println(key + ": " + words.get(key));
        }   
    public List <Map.Entry<String, Integer>> getWordList() {
        return new ArrayList<>(words.entrySet()); 
	}
    }


	

 
