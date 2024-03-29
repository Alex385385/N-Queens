import java.lang.Math;
import java.util.Random;
import java.util.ArrayList;

public class NQueen {
    public Individual SimAnnealing(int[] problem) {
        Random rd = new Random();
        Individual current = new Individual(problem);
        Individual next;
        Schedule sd = new Schedule();

        double littleT = 1.0;
        double T;

        while(true) {

            T = sd.getT(littleT);

            if(T <= 0.0 || current.getFitness() == 0) {
                return current;
            }

            next = new Individual(mutate(current.getArray()));
            int deltaE = current.getFitness() - next.getFitness() ;
            if(deltaE > 0) {
                current = next;
            }
            else if(Math.random() <= Math.exp((deltaE/T))) {
                current = next;
            }

            littleT++;
        }
    }


    public Individual GeneticAlg(ArrayList<Individual> population) {
        Random rd = new Random();
        int counter = 0;

        do {
            ArrayList<Individual> new_population= new ArrayList<>();
            for (int i = 0; i < population.size(); i++) {
                Individual child;
                int[] x = randomSelection(population);
                int[] y = randomSelection(population);
                child = new Individual(reproduce(x, y).clone());
                new_population.add(child);
            }
            population = new ArrayList<>(new_population);
            for (int i = 0; i < population.size(); i++) {
                if (population.get(i).getFitness() == 0) {
                    return population.get(i);
                }
            }

            counter++;
        } while(counter < 1000);

        return null;
    }

    private int[] randomSelection(ArrayList<Individual> population) {
        int[] randomSample = new int[25];
        Random rd = new Random();
        for(int i = 0; i < randomSample.length; i++) {
            randomSample[i] = rd.nextInt(population.size());
        }

        Individual parent = new Individual(population.get(randomSample[0]).getArray().clone());

        for(int i = 0; i < randomSample.length; i++) {
            if(parent.getFitness() > population.get(randomSample[i]).getFitness()) {
                parent = new Individual(population.get(randomSample[i]).getArray().clone());
            }
        }

        return parent.getArray();
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
        Random mutate = new Random();
        int[] copy = new int[25];

        int n = 25;
        int c = rd.nextInt(n);

        for(int i = 0; i < c; i ++) {
            copy[i] = x[i];
        }

        for(int j = c; j < 25; j++) {
            copy[j] = y[j];
        }

        if(mutate.nextInt(101) < 35) {
            copy = mutate(copy).clone();
        }

        return copy;
    }

    private int[] mutate(int[] child) {
        Random rd = new Random();
        int index = rd.nextInt(25);
        int number = rd.nextInt(25);

        child[index] = number;

        return child;
    }
}
