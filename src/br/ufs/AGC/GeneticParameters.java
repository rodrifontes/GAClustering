package br.ufs.AGC;

public class GeneticParameters {

	private int populationSize;
    private int numberOfEvolutions;
    private double crossoverRate;
    private int mutationRate;
    
    public GeneticParameters(int populationSize, int numberOfEvolutions, double crossoverRate, int mutationRate) {
        this.populationSize = populationSize;
        this.numberOfEvolutions = numberOfEvolutions;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }
    
    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getNumberOfEvolutions() {
        return numberOfEvolutions;
    }

    public void setNumberOfEvolutions(int numberOfEvolutions) {
        this.numberOfEvolutions = numberOfEvolutions;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }
    
    public int getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(int mutationRate) {
        this.mutationRate = mutationRate;
    }
	
}
