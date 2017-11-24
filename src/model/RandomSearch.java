package model;

import java.util.Random;

public class RandomSearch extends Search {
    final private String name = "rs";
    private Random random;
    private int nbEvalMax;
    private InitData initData;

    public RandomSearch(Random random, Evaluation evaluation, int nbEvalMax) {
        super(evaluation);
        this.random = random;
        this.nbEvalMax = nbEvalMax;
        this.initData = new InitData(random, evaluation.getSize());
        this.solution = new Solution(name);
    }

    public void run() {
        this.initData.init(solution);
        this.evaluation.apply(solution);
        int nbEval = 1;
        Solution best = solution.getBestSolution();

        while (nbEval < this.nbEvalMax) {
            this.initData.init(solution);
            this.evaluation.apply(solution);
            nbEval += 1;

            if (best.getFitness() < solution.getFitness()){
                best.copyData(solution);
            }
            this.solution.writeLine(this.nbEval);
        }
        this.nbEval += nbEval;
        this.solution.copyData(best);
    }

    public String nameOfData(){
        return "rs";
    }
}
