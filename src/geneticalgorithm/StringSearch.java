package geneticalgorithm;

public class StringSearch {

    private Processor[] processors;
    private int finish_time;
    private double start_roulette_wheel;
    private double finish_roulette_wheel;
    private double fitness;

    public StringSearch() {
        this.processors = null;
        this.finish_time = -1;
        this.start_roulette_wheel = 0.0;
        this.finish_roulette_wheel = 0.0;
        this.fitness = 0.0;
    }

    public StringSearch(Processor[] processors, int finish_time) {
        this.processors = processors;
        this.finish_time = finish_time;
        this.start_roulette_wheel = 0.0;
        this.finish_roulette_wheel = 0.0;
        this.fitness = 0.0;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setStart_roulette_wheel(double start_roulette_wheel) {
        this.start_roulette_wheel = start_roulette_wheel;
    }

    public double getStart_roulette_wheel() {
        return start_roulette_wheel;
    }

    public double getFinish_roulette_wheel() {
        return finish_roulette_wheel;
    }

    public void setFinish_roulette_wheel(double finish_roulette_wheel) {
        this.finish_roulette_wheel = finish_roulette_wheel;
    }

    public void setProcessors(Processor[] processors) {
        this.processors = processors;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public Processor[] getProcessors() {
        return processors;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public double getFitness() {
        return fitness;
    }
}
