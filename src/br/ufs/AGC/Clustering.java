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

	public static double fit;
	private static List<DataPoint> dataSet;
	public static int numClusters = 3;
	public static int numDimensions;
	private static String pathDataSet;
	public static Double[] maxValueDimensions;
	public static Double[] minValueDimensions;
	
	public static void main(String[] args) throws IOException, InvalidConfigurationException {
		
		pathDataSet = "wine.data";//JOptionPane.showInputDialog("Informe o Caminho do DataSet");
		numClusters = 3;//Integer.parseInt(JOptionPane.showInputDialog("Informe o n�mero de clusters"));
		dataSet = getDataSet();
		int populationSize = 2000;//Integer.parseInt(JOptionPane.showInputDialog("Informe o Tamanho da Popula��o"));
	    int numberOfEvolutions = 100;//Integer.parseInt(JOptionPane.showInputDialog("Informe o N�mero de Gera��es"));
	    double crossoverRate = 0.7;//Integer.parseInt(JOptionPane.showInputDialog("Informe a Taxa de Cruzamento"));
	    int mutationRate = 1;//Integer.parseInt(JOptionPane.showInputDialog("Informe a Taxa de Muta��o"));
		
	    System.out.println(pathDataSet);
	    for(int i = 1; i<numClusters+1; i++)
	    	System.out.print("C"+i+";");
	    System.out.print("Tempo;");
	    System.out.println("Fitness");
		for(int i = 0; i<30;i++){
			long startTime = System.currentTimeMillis();
		    GeneticParameters geneticParameters = new GeneticParameters(populationSize, numberOfEvolutions, crossoverRate, mutationRate);
		    GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
		    IChromosome bestSolution = geneticAlgorithm.clustering(geneticParameters);
		    
		    List<Cluster> clusters = Cluster.generateClusters(bestSolution);
		    long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    
		    //taxaErro(clusters);
		    toCSV(clusters, elapsedTime*0.001, fit);
		    System.out.println();
		    
		}

	}
	
	public static void taxaErro(List<Cluster> clusters) throws IOException {
		pathDataSet = "iris2.data";
		List<DataPoint> dataOriginal = dataSetReader();
		for(Cluster c:clusters){
			System.out.print(c+";");
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
	
	private static void toCSV(List<Cluster> centros, double tempo, double fitness){
		for(Cluster c:centros){
			System.out.print(c.getDataPoints().size()+";");
		}
		System.out.printf("%.2f;", tempo);
		System.out.print(fitness);
	}

}
