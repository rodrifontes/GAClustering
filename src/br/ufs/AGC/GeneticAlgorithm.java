package br.ufs.AGC;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class GeneticAlgorithm {

	public Configuration createConfiguration() {
		return new DefaultConfiguration();
	}
	
	public IChromosome clustering(GeneticParameters geneticParameters) throws InvalidConfigurationException {
		
		Configuration conf = createConfiguration();
		
		FitnessFunction fitnessFunction = new ClusteringFitnessFunction();
		conf.setFitnessFunction(fitnessFunction);
		
		Gene[] sampleGenes = new Gene[Clustering.numClusters];
		for (int i = 0; i < sampleGenes.length; i++) {
			sampleGenes[0] = new IntegerGene(conf, 0, Clustering.numClusters-1);
		}
		
		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		
        conf.setSampleChromosome(sampleChromosome);

        // Determino o tamanho da minha popula��o
        conf.setPopulationSize(geneticParameters.getPopulationSize());

        // Gero minha popula��o inicial aleatoriamente
        Genotype population = Genotype.randomInitialGenotype(conf);

        IChromosome bestSolutionSoFar = null;
        
        for (int i = 0; i < geneticParameters.getNumberOfEvolutions(); i++) {
			// Inicio a evolu��o da popula��o, uma gera��o
            population.evolve();
            bestSolutionSoFar = population.getFittestChromosome();
        }
        	
		return bestSolutionSoFar;
		
	}
	
}
