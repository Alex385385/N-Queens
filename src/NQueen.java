import java.util.Random;
import java.util.ArrayList;

public class NQueen {
    private int smallest = 1000000;

    public int[] SimAnnealing(int[] problem) {
        Random rd = new Random();
        Individual current = new Individual(problem);
        Individual next;
        Schedule sd = new Schedule();
        double T;

        long temp = System.nanoTime();

        while(true) {

            T = sd.getT(temp);

            if(T == 0) {
                return current.getArray();
            }

            next = new Individual(mutate(current.getArray()));
            int deltaE = next.getFitness() - current.getFitness();
            if(deltaE < 0) {
                current = next;
            }
            else if(rd.nextFloat() < Math.exp((deltaE/T))) {
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
                //int[] x = population.get(rd.nextInt(population.size())).getArray().clone();
                //int[] y = population.get(rd.nextInt(population.size())).getArray().clone();
                int[] x = randomSelection(population);
                int[] y = randomSelection(population);
                child = new Individual(reproduce(x, y).clone());
                new_population.add(child);
            }
            population = new ArrayList<>(new_population);
            //Individual solution = searchSolution(population);

            for (int i = 0; i < population.size(); i++) {
                if (population.get(i).getFitness() == 0) {
                    return population.get(i);
                }
            }

            //if(solution != null){
            //    return solution;
            //}
            counter++;
        } while(counter < 200);

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
            else if(population.get(i).getFitness() < smallest) {
                smallest = population.get(i).getFitness();
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

    public int getSmallest() {
        return smallest;
    }
}
