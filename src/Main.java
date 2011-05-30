import java.io.File;

public class Main {
	
	public static void main(String[] args){
		Document doc = new Document(new File("input.data"));
		for(Tuple t : doc)
			System.out.println(t.e + "\t\t" + t.v);
	}
}
