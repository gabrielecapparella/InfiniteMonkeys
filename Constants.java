package infiniteMonkeys;

public final class Constants {
	
	static final public int POPULATION_SIZE = 2000;       // Number of monkeys
	
	static final public double SELECTION_RATE = 0.6;      // Portion of the population that gets replaced
	static final public double TOURNAMENTS = 2;           // Number of tournaments
	
	static final public boolean INCEST_PREVENTING = true; // Prevents too much similar individuals to breed
	static final public int INCEST = 20;                  // Minimum diversity between breeders allowed
	
	static final public boolean ELITISM = true;           // Prevents the best individual to mutate
	static final public double MUTATION_RATE = 0.05;      // Changes that each genes has to mutate
	static final public int MUTATION_RANGE = 2;           // Magnitude of mutations
	

	
}
