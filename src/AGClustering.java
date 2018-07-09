import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AGClustering {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("iris.data"));
		String row = br.readLine();
		while (row != null) {
			System.out.println(row);
			row = br.readLine();
		}
		
		br.close();	
		
	}

}
