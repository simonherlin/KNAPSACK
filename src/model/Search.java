package model;

abstract public class Search {
    protected Solution solution;
    protected Evaluation evaluation;
    protected int nbEval;

    public Search(Evaluation evaluation) {
        this.evaluation = evaluation;
        this.nbEval = 0;
    }

    public abstract void run() ;

    public double fitness() {
        return this.solution.getFitness();
    }

    public int nbEval() {
        return this.nbEval;
    }

    abstract public String nameOfData();
}