public class Individual {
    private int[] array;
    private int fitness;

    public Individual(int[] array) {
        this.array = array.clone();
        this.fitness = fitness(array);
    }

    public Individual() {

    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    private int fitness(int[] array) {
        int counter = 0;
        for(int i = 0; i < array.length - 2; i++) {
            for(int j = i + 1; j < array.length-1; j++) {
                int deltaIndex = j - i;
                int deltaQueen = Math.abs(array[i] - array[j]);

                if((array[i] == array[j]) || (deltaIndex == deltaQueen)) {
                    counter++;
                }
            }
        }

        return counter;
    }
}
