import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Document implements Iterable<Tuple> {
	
	private Map<String, Integer> counts;
	private List<Tuple> sorted;
	
	public Document(File f){
		try {
			this.sorted = new ArrayList<Tuple>();
			this.counts = new HashMap<String, Integer>();
			Scanner s = new Scanner(f);
			
			while(s.hasNextLine()){
				String line = s.nextLine().trim();
				String[] parts = line.split("\t");
				for(String tup : parts){
					String[] tokens = tup.split(" ");
					for(String t : tokens){
						t = t.trim().toLowerCase();
						if(t.length() != 0){
							if(!counts.containsKey(t))
								counts.put(t, 0);
							counts.put(t, counts.get(t) + 1);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(String key : this.counts.keySet()){
			this.sorted.add(new Tuple(key, this.counts.get(key)));
		}
	}
	
	
	public Integer getCount(String token){
		if(!this.counts.containsKey(token))
			return 0;
		else
			return this.counts.get(token);
	}
	
	public Iterator<Tuple> iterator(){
		Collections.sort(this.sorted);
		return this.sorted.iterator();
	}
}
