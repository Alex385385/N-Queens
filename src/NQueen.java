import java.util.Random;
import java.util.ArrayList;

public class NQueen {

    public void SimAnnealing(int[] problem) {

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
            population.addAll(new_population);
            counter++;
        } while (counter == 20);

        return population.get(0);
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
