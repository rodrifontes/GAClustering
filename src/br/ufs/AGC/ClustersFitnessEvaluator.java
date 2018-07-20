package br.ufs.AGC;

import org.jgap.FitnessEvaluator;
import org.jgap.IChromosome;

public class ClustersFitnessEvaluator implements FitnessEvaluator {

	@Override
	public boolean isFitter(double d, double d1) {
		if(d<d1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isFitter(IChromosome c, IChromosome c1) {
		return(isFitter(c.getFitnessValue(), c1.getFitnessValue()));
	}

	
	
}
