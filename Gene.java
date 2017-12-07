package infiniteMonkeys;

import static infiniteMonkeys.Constants.*;

import java.util.Random; //32-126

public class Gene {
	
	private char gene;
	private Random rand = new Random();

	public Gene() {
		this.gene = (char)(rand.nextInt(95)+32);
	}
	
	public Gene(char gene) {
		this.rand = new Random();
		this.gene = gene;
	}
	
	public char getGene() {
		return gene;
	}
	
	public void mutate() {
		int newGene=0;
		
		do {
			newGene = this.gene + this.rand.nextInt(2*MUTATION_RANGE+1)-MUTATION_RANGE;
		} while(newGene<32 || newGene>126);
		
		this.gene = (char)newGene;
	}
	
	public int diversity(Gene g) {
		return Math.abs(this.gene-g.getGene());
	}
	
	@Override
	public boolean equals(Object o) {
		Gene g = (Gene)o;
		return this.gene==g.getGene();
	}

}
