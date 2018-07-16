package br.ufs.AGC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;

public class Clustering {

	private static List<DataPoint> dataSet;
	public static int numClusters = 3;
	public static int numDimensions;
	private static String pathDataSet;
	public static Double[] maxValueDimensions;
	public static Double[] minValueDimensions;
	
	public static void main(String[] args) throws IOException, InvalidConfigurationException {
		
		pathDataSet = "iris.data";//JOptionPane.showInputDialog("Informe o Caminho do DataSet");
		numClusters = 3;//Integer.parseInt(JOptionPane.showInputDialog("Informe o número de clusters"));
		dataSet = getDataSet();
		int populationSize = 1000;//Integer.parseInt(JOptionPane.showInputDialog("Informe o Tamanho da População"));
	    int numberOfEvolutions = 1000;//Integer.parseInt(JOptionPane.showInputDialog("Informe o Número de Gerações"));
	    double crossoverRate = 0.9;//Integer.parseInt(JOptionPane.showInputDialog("Informe a Taxa de Cruzamento"));
	    int mutationRate = 1;//Integer.parseInt(JOptionPane.showInputDialog("Informe a Taxa de Mutação"));
	    
	    GeneticParameters geneticParameters = new GeneticParameters(populationSize, numberOfEvolutions, crossoverRate, mutationRate);
	    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
	    IChromosome bestSolution = geneticAlgorithm.clustering(geneticParameters);
	    
	    List<Cluster> clusters = Cluster.generateClusters(bestSolution);
	    for (Cluster cluster : clusters) {
			System.out.println(cluster);
		}

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
		
		minMaxValueDimensions(dataSet);
		
		return dataSet;
	}
	
	private static void minMaxValueDimensions(List<DataPoint> dataSet) {
		
		Double min[] = new Double[numDimensions];
		Double max[] = new Double[numDimensions];
		
		int count = 0;
		for (DataPoint dataPoint : dataSet) {
			
			for (int i = 0; i < numDimensions; i++) {
				if(count == 0 || (Double) dataPoint.getAttributes().get(i) < min[i]) 
					min[i] = (Double) dataPoint.getAttributes().get(i);	
				if(count == 0 || (Double) dataPoint.getAttributes().get(i) > max[i]) 
					max[i] = (Double) dataPoint.getAttributes().get(i);	
			}
			
			count++;
			
		}
		
		minValueDimensions = min;
		maxValueDimensions = max;
		
	}

}
