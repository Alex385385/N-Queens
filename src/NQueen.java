import java.util.Random;
import java.util.ArrayList;

public class NQueen {

    public int[] SimAnnealing(int[] problem) {
        Individual current = new Individual(problem);


        while(true) {

            if(t == 0) {
                return current.getArray();
            }

            int deltaE = next.getFitness() - current.getFitness();
            if(deltaE > 0) {
                current = next;
            }
        }

    }

    public Individual GeneticAlg(ArrayList<Individual> population) {
        Random rd = new Random();
        int counter = 0;

        do {
            ArrayList<Individual> new_population= new ArrayList<>();
            for (int i = 0; i < population.size(); i++) {
                Individual child;
                int[] x = population.get(rd.nextInt(population.size())).getArray().clone();
                int[] y = population.get(rd.nextInt(population.size())).getArray().clone();
                child = new Individual(reproduce(x, y));
                new_population.add(child);
            }
            population = new ArrayList<>(new_population);
            Individual solution = searchSolution(population);

            if(solution != null){
                return solution;
            }
            counter++;
        } while (counter == 100);

        return null;
    }

    private Individual searchSolution(ArrayList<Individual> population) {
        for (int i = 0; i < population.size(); i++) {
            if(population.get(i).getFitness() == 0) {
                return population.get(i);
            }
        }
        return null;
    }

    private int[] reproduce(int[] x, int[] y) {
        Random rd = new Random();
        int[] copy = new int[x.length];

        int n = x.length;
        int c = rd.nextInt(n);

        for(int i = 0; i < c; i ++) {
            copy[i] = x[i];
        }

        for(int i = c; i < x.length; i++) {
            copy[i] = y[i];
        }

        if(rd.nextInt(101) < 50) {
            copy = mutate(copy).clone();
        }

        return copy;
    }

    private int[] mutate(int[] child) {
        Random rd = new Random();
        int index = rd.nextInt(child.length);
        int number = rd.nextInt(child.length);

        child[index] = number;

        return child;
    }
}
