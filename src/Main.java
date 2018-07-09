import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufs.AGC.Data;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("iris.data"));
		String row = br.readLine();
		List<Data> dataSet = new ArrayList<Data>();
		while (row != null) {
			Data data = new Data();
			System.out.println(row);
			row = br.readLine();
		}
		
		br.close();	
		
	}

}
