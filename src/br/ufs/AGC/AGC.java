package br.ufs.AGC;

import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;

public class AGC {

	public Configuration createConfiguration() {
		return new DefaultConfiguration();
	}
	
	public IChromosome clustering(GeneticParameters geneticParameters) throws InvalidConfigurationException {
		Configuration conf = createConfiguration();
		
		FitnessFunction fitnessFunction = new ClusteringFitnessFunction();
		conf.setFitnessFunction(fitnessFunction);
		
		return null;
	}
	
}
