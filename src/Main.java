import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Random rd = new Random();
        NQueen nq = new NQueen();
        NQueen sa = new NQueen();
        Individual id;
        ArrayList<Individual> population = new ArrayList<>();


        int[] array = new int[25];
        int[] array2 = new int[25];


        System.out.println("N-Queen");
        System.out.println("1) To use genetic algorithm enter 1");
        System.out.println("2) To use simulated annealing enter 2");
        int response = kb.nextInt();
        kb.nextLine();

        if(response == 1) {
            int counter = 1;
            do {
                for (int i = 0; i < 500; i++) {
                    for (int j = 0; j < array.length; j++) {
                        array[j] = rd.nextInt(25);
                    }
                    population.add(new Individual(array));
                }
                nq = new NQueen();
                id = nq.GeneticAlg(population);

                if(id != null) {
                    System.out.println("Your solution is: " + Arrays.toString(id.getArray()));
                    System.out.println("The fitness is: " + id.getFitness());
                }
                else {
                    System.out.println("No solution found try again");
                }

                counter++;
            } while (counter <= 3);
        }
        else if(response == 2) {
            int counter = 1;
            do {
                for (int j = 0; j < array2.length; j++) {
                    array2[j] = rd.nextInt(25);
                }

                sa = new NQueen();
                id = sa.SimAnnealing(array2);

                System.out.println("Your solution is: " + Arrays.toString(id.getArray()));
                System.out.println("The fitness is: " + id.getFitness());

                counter++;
            } while (counter <= 3);
        }

    }
}
