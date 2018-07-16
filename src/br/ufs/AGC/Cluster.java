package br.ufs.AGC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgap.IChromosome;

public class Cluster {

	private List<DataPoint> dataPoints;
	private DataPoint centroid;
	
	public Cluster() {
		dataPoints = new ArrayList<>();
	}
	
	public Cluster(DataPoint centroide) {
		dataPoints = new ArrayList<>();
		this.centroid = centroide;
	}
	
	public static List<Cluster> generateClusters(IChromosome a_potentialSolution) throws IOException {
		
		List<Cluster> clusters = separatedCentroides(a_potentialSolution);
		
		List<DataPoint> dataSet =  Clustering.getDataSet();
		for (DataPoint dataPoint : dataSet) {
			Double menorDistancia = 0d;
			int indexClusterMenorDistancia = 0;
			for (int i = 0; i < clusters.size(); i++) {
				DataPoint centroid = clusters.get(i).getCentroid();
				Double distanceCentroid = dataPoint.getDistance(centroid); 
				if(i == 0 || distanceCentroid < menorDistancia) {
					indexClusterMenorDistancia = i;
					menorDistancia = distanceCentroid;
				}
			}
			clusters.get(indexClusterMenorDistancia).getDataPoints().add(dataPoint);
		}
		
		return clusters;
		
	}
	
	private static List<Cluster> separatedCentroides(IChromosome a_potentialSolution) {
		
		List<Cluster> clusters = new ArrayList<>();
		
		int d = 0;
		DataPoint centroid = new DataPoint();
		for (int i = 0; i < a_potentialSolution.size(); i++) {
			centroid.getAttributes().add(a_potentialSolution.getGene(i).getAllele());
			if(d == Clustering.numDimensions - 1){
				clusters.add(new Cluster(centroid));
				centroid = new DataPoint();
				d = 0;
			} else {
				d++;
			}
		}
		
		return clusters;
		
	}
	
	public List<DataPoint> getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(List<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public DataPoint getCentroid() {
		return centroid;
	}

	public void setCentroid(DataPoint centroide) {
		this.centroid = centroide;
	}
	
	@Override
	public String toString() {
		String result = "Centroid: " + getCentroid() + "\n";
		for (DataPoint dataPoint : dataPoints) {
			result += dataPoint + "\n";
		}
		return result;
	}
	
}
