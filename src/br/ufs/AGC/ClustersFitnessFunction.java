package br.ufs.AGC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class ClustersFitnessFunction extends FitnessFunction {

	@Override
	protected double evaluate(IChromosome a_subject) {
		double fitness = 0;
		try {
			List<Cluster> clusters = Cluster.generateClusters(a_subject);
			
			for (Cluster cluster : clusters) {
				if(cluster.getDataPoints().size() == 0)
					return Double.MAX_VALUE;
			}
			
			Double sumDistance = 0d;
			for (Cluster cluster : clusters) {
				
				List<DataPoint> dataPoints = cluster.getDataPoints();
				for (DataPoint dataPoint : dataPoints) {
					sumDistance += dataPoint.getDistance(cluster.getCentroid());
				}
				
			}
			
			fitness = sumDistance / Clustering.getDataSet().size();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fitness;
	}
	
}
