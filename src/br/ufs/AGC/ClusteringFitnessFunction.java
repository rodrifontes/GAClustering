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
		
		List<Cluster> clusters = new ArrayList<>();
		
		for (int i = 0; i < Clustering.numClusters-1; i++) {
			clusters.add(new Cluster());
		}
		
		for(int i = 0; i < a_potentialSolution.size(); i++){
			DataPoint data = Clustering.getDataSet().get(i);
			clusters.get((Integer) a_potentialSolution.getGene(i).getAllele()).getData().add(data);
		}
		
		return clusters;
		
	}
	

	
}
