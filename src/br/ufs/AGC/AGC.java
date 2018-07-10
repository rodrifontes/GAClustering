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

public class AGC {

	public Configuration createConfiguration() {
		return new DefaultConfiguration();
	}
	
	public IChromosome clustering(GeneticParameters geneticParameters, int k) throws InvalidConfigurationException {
		
		Configuration conf = createConfiguration();
		
		FitnessFunction fitnessFunction = new ClusteringFitnessFunction();
		conf.setFitnessFunction(fitnessFunction);
		
		Gene[] sampleGenes = new Gene[k];
		for (int i = 0; i < sampleGenes.length; i++) {
			sampleGenes[0] = new IntegerGene(conf, 1, k);
		}
		
		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		
        conf.setSampleChromosome(sampleChromosome);

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
	
}
