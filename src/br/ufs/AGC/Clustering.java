package br.ufs.AGC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Clustering {

	private static List<DataPoint> dataSet;
	public static int numClusters = 3;
	public static int numDimensions;
	private static String pathDataSet;
	
	public static void main(String[] args) throws IOException {
		
		pathDataSet = JOptionPane.showInputDialog("Informe o Caminho do DataSet");
		numClusters = Integer.parseInt(JOptionPane.showInputDialog("Informe o n�mero de clusters"));
		dataSet = getDataSet();

	}
	
	public static List<DataPoint> getDataSet() throws IOException {
		if(dataSet == null)
			dataSet = dataSetReader();	
		return dataSet;
	}

	private static List<DataPoint> dataSetReader() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(pathDataSet));
		String row = br.readLine();
		List<DataPoint> dataSet = new ArrayList<DataPoint>();
		while (row != null) {
			DataPoint data = new DataPoint();
			String[] dataSplit = row.split(",");
			numDimensions = dataSplit.length;
			for (int i = 0; i < dataSplit.length; i++) {
				if(Pattern.matches("^\\d+$", dataSplit[i]) || Pattern.matches("^([+-]?\\d*\\.+\\d*)$", dataSplit[i]))
					data.getAttributes().add(new Double(dataSplit[i]));
				else
					data.getAttributes().add(new String(dataSplit[i]));
			}
			dataSet.add(data);
			row = br.readLine();
		}
		br.close();
		return dataSet;
	}

}