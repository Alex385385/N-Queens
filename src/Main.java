import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rd = new Random();
        NQueen nq = new NQueen();
        Individual id = new Individual();
        ArrayList<Individual> population = new ArrayList<>();

        //int[] array2 = {17,22,6,8,3,5,16,21,10,24,2,4,18,14,11,1,23,20,13,7,9,15,12,19,1};
        //Individual is = new Individual(array2);

        int[] array = new int[25];



        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j] = rd.nextInt(25);
            }
            population.add(new Individual(array));
        }
        //do {
            //nq = new NQueen();
            id = nq.GeneticAlg(population);
            int xy = nq.getSmallest();
            //System.out.println(xy);
       // }while (id == null);

        int x = 1;


    }
}
