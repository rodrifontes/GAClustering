package br.ufs.AGC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class ClusteringFitnessFunction extends FitnessFunction {

	@Override
	protected double evaluate(IChromosome a_subject) {
		double fitness = 0;
		try {
			List<Cluster> clusters = generateClusters(a_subject);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private List<Cluster> generateClusters(IChromosome a_potentialSolution) throws IOException {
		
		List<Cluster> clusters = separatedCentroides(a_potentialSolution);
		
		List<DataPoint> dataSet =  Clustering.getDataSet();
		for (DataPoint dataPoint : dataSet) {
			Double menorDistancia = 0d;
			int indexClusterMenorDistancia = 0;
			for (int i = 0; i < clusters.size(); i++) {
				Double distanceCentroid = dataPoint.getDistance(clusters.get(i).getCentroid()); 
				if(i == 0 || distanceCentroid < menorDistancia) {
					indexClusterMenorDistancia = i;
					menorDistancia = distanceCentroid;
				}
			}
			clusters.get(indexClusterMenorDistancia).getDataPoints().add(dataPoint);
		}
		
		return clusters;
		
	}
	
	private List<Cluster> separatedCentroides(IChromosome a_potentialSolution) {
		
		List<Cluster> clusters = new ArrayList<>();
		
		int d = 0;
		DataPoint centroid = new DataPoint();
		for (int i = 0; i < a_potentialSolution.size(); i++) {
			centroid.getAttributes().add(a_potentialSolution.getGene(i));
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
	
}
