package infiniteMonkeys;

import static infiniteMonkeys.Constants.*;

import java.util.Arrays;
import java.util.Random;

public class Individual implements Comparable<Individual> {
	
	private Gene[] genotype;
	private static Individual target;
	private Random rand = new Random();
	

	public Individual(Gene[] genotype) {
		this.genotype = genotype;
	}
	
	public Individual(String targetStr) {
		this.genotype = new Gene[targetStr.length()];
		Gene[] targetGenes = new Gene[targetStr.length()];
		
		for(int i = 0; i<targetStr.length(); i++) {
			targetGenes[i] = new Gene(targetStr.charAt(i));
			this.genotype[i] = new Gene();
		}
		
		target = new Individual(targetGenes);
	}
	
	public void mutate() {
		for(int i = 0; i<this.genotype.length; i++) {
			if(this.rand.nextDouble()<=MUTATION_RATE) {
				this.genotype[i].mutate();
			}
		}
	}
	
	public int diversity(Individual i) {
		int div = 0;
		for(int k = 0; k<this.genotype.length; k++) {
			div += this.genotype[k].diversity(i.getGenotype()[k]);
		}
		return div;
	}
	
	public int fitness() {
		return this.diversity(target);
	}
	
	public Gene[] getGenotype() {
		return genotype;
	}
	
	public String getSolution() {
		StringBuilder sol = new StringBuilder();

		for(Gene g : this.genotype) {
			sol.append(g.getGene());
		}
		return sol.toString();
	}

	public void setGenotype(Gene[] genotype) {
		this.genotype = genotype;
	}
	
	public String debug() {
		return this.fitness()+"   "+this.getSolution();
	}
	
	@Override
	public boolean equals(Object o) {
		Individual i = (Individual)o;
		return Arrays.deepEquals(this.genotype, i.getGenotype());
	}

	@Override
	public int compareTo(Individual i) { //in caso di = devo confrontare qualche altra cosa
		double ret = this.fitness()-i.fitness();
		if(ret>0)return -1;
		if(ret<0)return 1;
		else return 0;
	}

}
