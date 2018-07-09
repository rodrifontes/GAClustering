import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import br.ufs.AGC.Data;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("iris.data"));
		String row = br.readLine();
		List<Data> dataSet = new ArrayList<Data>();
		while (row != null) {
			Data data = new Data();
			String[] dataSplit = row.split(",");
			for (int i = 0; i < dataSplit.length; i++) {
				if(Pattern.matches("^\\d+$", dataSplit[i]) || Pattern.matches("^([+-]?\\d*\\.+\\d*)$", dataSplit[i]))
					data.getAttributes().add(new Double(dataSplit[i]));
				else
					data.getAttributes().add(new String(dataSplit[i]));
			}
			dataSet.add(data);
			row = br.readLine();
		}
		
		for (Data data : dataSet) {
			System.out.println(data);
		}
		
		br.close();	
		
	}

}
