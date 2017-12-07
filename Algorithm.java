package infiniteMonkeys;

import static infiniteMonkeys.Constants.SELECTION_RATE;

public class Algorithm {
	
	private String target;
	private Population population;


	public Algorithm(String target) {
		this.target = target;
		this.population = new Population(this.target);
		
	}
	
	public void cycle() {
		//this.population.debug();

		int toReplace = this.toReplace();
		this.population.selection(toReplace);
		for(int i = 0; i<toReplace; i++) {
			this.population.breed();
		}	
		this.population.mutate();
	}
	
	private int toReplace() {
		return (int)(this.population.getSize()*SELECTION_RATE);
	}
	
	public Individual getBest() {
		return this.population.getBest();
	}
	
	public Individual getWorst() {
		return this.population.getWorst();
	}
}
