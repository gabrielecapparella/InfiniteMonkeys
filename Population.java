package infiniteMonkeys;

import static infiniteMonkeys.Constants.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
	
	private String target;
	private int numberOfGenes;
	private ArrayList<Individual> individuals;
	private Random rand = new Random();
	
	public Population(String target) {
		this.target = target;
		this.numberOfGenes = target.length();
		this.individuals = new ArrayList<>();
		
		for(int i = 0; i<POPULATION_SIZE; i++) {
			this.individuals.add(new Individual(this.target));
		}
		
	}
	
	
	public void mutate() {
		Individual elite = this.getBest();
		for(Individual i : this.individuals) {
			if(ELITISM && elite.equals(i)) continue;
			i.mutate();
		}
	}
	
	// Kills the least fit portion of population
	public void selection(int toKill) {
		for(int i=0; i<toKill; i++) {
			Individual k = Collections.min(this.individuals);
			//System.out.println("killing: "+k.debug());
			this.individuals.remove(k);
		}
	}
	
	public void debug() {
		for(Individual i : this.individuals) {
			System.out.println(i.debug());
		}
		System.out.println("########");
	}
	
	public void breed() {
		Individual parent1 = chooseParent();
		Individual parent2 = chooseParent();
		
		while(INCEST_PREVENTING && parent1.diversity(parent2)<=INCEST) {
			parent2 = this.chooseParent();
		}
		
		this.crossover(parent1, parent2);
		
	}
	
	// Uniform crossover
	private void crossover(Individual a, Individual b) { 
		Gene[] newGenes = new Gene[this.numberOfGenes];
		
		for(int i = 0; i<this.numberOfGenes; i++) {
			if(this.rand.nextDouble()<=0.5) {
				newGenes[i] = new Gene(a.getGenotype()[i].getGene());
			} else {
				newGenes[i] = new Gene(b.getGenotype()[i].getGene());
			}
		}
		
		this.individuals.add(new Individual(newGenes));
	}
	
	// Tournament selection
	private Individual chooseParent() {
		Individual current, best = null;
		int r;
		
		for(int k = 0; k<TOURNAMENTS; k++) {
			r = rand.nextInt(this.individuals.size());
			current = this.individuals.get(r);
			if(best==null || current.fitness()<best.fitness()) {
				best = current;
			}
		}
		
		return best;
	}
	
	public int getSize() {
		return this.individuals.size();
	}
	
	public Individual getBest() {
		return Collections.max(this.individuals);
	}
	
	public Individual getWorst() {
		return Collections.min(this.individuals);
	}
}
