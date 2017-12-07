package infiniteMonkeys;

public class Main {
	
	public static void main(String[] args) {
		String target = new String("To be, or not to be, that is the question:;Whether 'tis nobler in the mind to suffer; The slings and arrows of outrageous fortune,");
		Algorithm alg = new Algorithm(target);
		Individual best;
		int diversity;

		int i = 1;
		best = alg.getBest();

		
		System.out.println("Generation: 0   "+best.debug());
		
		do {
			alg.cycle();
			i++;
			best = alg.getBest();
			diversity = alg.getWorst().fitness()-best.fitness();

			System.out.println("Gen: "+i+"   Div:"+diversity+"   Fit:"+best.debug());
		} while(best.fitness()>0 && diversity>0);
		
	}

}
