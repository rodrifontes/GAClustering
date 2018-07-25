package br.ufs.AGC;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;
import org.jgap.impl.GABreeder;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;
import org.jgap.impl.WeightedRouletteSelector;

public class GeneticAlgorithm {

	private Configuration conf;
    private GeneticParameters geneticParameters;
	
	private Configuration createConfiguration() throws InvalidConfigurationException {
		
		Configuration conf = new Configuration();
		
		//conf.setMinimumPopSizePercent(0);
		
		// Define os operadores do algoritmo.
        conf.setBreeder(new GABreeder());
		conf.addNaturalSelector(new BestChromosomesSelector(conf), false);
		conf.addGeneticOperator(new CrossoverOperator(conf, geneticParameters.getCrossoverRate()));
        conf.addGeneticOperator(new SwappingMutationOperator(conf, geneticParameters.getMutationRate()));
        conf.setRandomGenerator(new StockRandomGenerator());
        conf.setEventManager(new EventManager());
        
        return conf;
	
	}
	
	public IChromosome clustering(GeneticParameters geneticParameters) throws InvalidConfigurationException {
		
		this.geneticParameters = geneticParameters; 
		
		conf = createConfiguration();
		
		FitnessFunction fitnessFunction = new ClustersFitnessFunction();
		conf.setFitnessFunction(fitnessFunction);
		conf.setFitnessEvaluator(new ClustersFitnessEvaluator());
		
		conf.setSampleChromosome(createSampleCromossome());

        // Determino o tamanho da minha população
        conf.setPopulationSize(geneticParameters.getPopulationSize());

        // Gero minha população inicial aleatoriamente
        Genotype population = Genotype.randomInitialGenotype(conf);

        IChromosome bestSolutionSoFar = null;
        
        for (int i = 0; i < geneticParameters.getNumberOfEvolutions(); i++) {
			//System.out.println("Geração " + (i+1) + "..." );
        	population.evolve();
            bestSolutionSoFar = population.getFittestChromosome();
            //System.out.println(fitnessFunction.getFitnessValue(bestSolutionSoFar));
        }
        
        Clustering.fit = fitnessFunction.getFitnessValue(bestSolutionSoFar);
        conf.reset();
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
