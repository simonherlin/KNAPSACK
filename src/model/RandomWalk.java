package model;

import java.util.Random;

public class RandomWalk extends Search {
    final private String name = "rw";
    private Random random;
    private int nbEvalMax;
    private InitData initData;
    private double [] saveWalk;

    public RandomWalk(Random random, Evaluation evaluation, int maxEval) {
        super(evaluation);
        this.random = random;
        this.nbEvalMax = maxEval;
        this.initData = new InitData(random, evaluation.getSize());
        this.saveWalk = new double[maxEval];
        this.solution = new Solution(name);
    }

    public void run() {
        this.initData.init(this.solution);
        this.evaluation.apply(this.solution);
        this.saveWalk[0] = this.solution.getFitness();
        nbEval = 1;
        int i;
        while(nbEval < this.nbEvalMax) {
            i = this.random.nextInt(this.evaluation.getSize());
            this.solution.setRangBitToBit(i, !this.solution.getRangBit(i));
            this.evaluation.apply(this.solution);
            this.saveWalk[nbEval] = solution.getFitness();
            nbEval++;
            this.solution.writeLine(this.nbEval);
        }
    }

    public String nameOfData(){
        return "rw";
    }
}
