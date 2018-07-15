package br.ufs.AGC;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;

public class GeneticAlgorithm {

	private Configuration conf;
    private GeneticParameters geneticParameters;
	
	private Configuration createConfiguration() {
		return  new DefaultConfiguration();
	}
	
	public IChromosome clustering(GeneticParameters geneticParameters) throws InvalidConfigurationException {
		
		this.geneticParameters = geneticParameters; 
		
		conf = createConfiguration();
		
		FitnessFunction fitnessFunction = new ClusteringFitnessFunction();
		conf.setFitnessFunction(fitnessFunction);
		
		conf.setSampleChromosome(createSampleCromossome());

        // Determino o tamanho da minha população
        conf.setPopulationSize(geneticParameters.getPopulationSize());

        // Gero minha população inicial aleatoriamente
        Genotype population = Genotype.randomInitialGenotype(conf);

        IChromosome bestSolutionSoFar = null;
        
        for (int i = 0; i < geneticParameters.getNumberOfEvolutions(); i++) {
			// Inicio a evolução da população, uma geração
            population.evolve();
            bestSolutionSoFar = population.getFittestChromosome();
        }
        	
		return bestSolutionSoFar;
		
	}
	
	private IChromosome createSampleCromossome() throws InvalidConfigurationException {
		
		int lengthChromosome = Clustering.numClusters*Clustering.numDimensions;
		Gene[] sampleGenes = new Gene[lengthChromosome];
		int d = 0;
		for (int i = 0; i < sampleGenes.length; i++) {
			sampleGenes[i] = new DoubleGene(conf, Clustering.minValueDimensions[d], Clustering.maxValueDimensions[d]);
			if(d == Clustering.numDimensions - 1){
				d = 0;
			} else {
				d++;
			}
		}
		
		return new Chromosome(conf, sampleGenes);
		
	}
	
}
