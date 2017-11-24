package model;

import java.util.Random;

public class HillClimberBest extends Search {
    private String name = "hi";
    private Random random;
    private int nbEvalMax;
    private InitData initData;
    private int nbStep;
    private double maxFitness;
    private int rangBest;
    private int range;
    private double bestNeighbor;
    private double currentFitness;
    private boolean optimum;

    public HillClimberBest(Random random, Evaluation evaluation, int evaluationMax, double max) {
        super(evaluation);
        this.nbEvalMax = evaluationMax;
        this.maxFitness = max;
        this.random = random;
        this.initData = new InitData(random, evaluation.getSize());
        this.solution = new Solution(name);
    }

    protected HillClimberBest(Random random, Evaluation evaluation, int evaluationMax, double max, String name) {
        super(evaluation);
        this.name = name;
        this.nbEvalMax = evaluationMax;
        this.maxFitness = max;
        this.random = random;
        this.initData = new InitData(random, evaluation.getSize());
        this.solution = new Solution(name);
    }

    public void run() {
        this.initDataHCB();
        while(!optimum && this.nbEval < this.nbEvalMax && this.solution.getFitness() < this.maxFitness) {
            this.rangBest = -1;
            this.currentFitness = solution.getFitness();
            this.loopForSeeBestNeighbor();
            if (this.solution.getFitness() < this.bestNeighbor) {
                this.solution.setRangBitToBit(this.rangBest, !this.solution.getRangBit(this.rangBest));
                this.solution.setFitness(this.bestNeighbor);
                this.nbStep += 1;
            } else {
                this.optimum = true;
            }
        }
        this.solution.writeLine(this.nbEval);
    }

    protected void initDataHCB() {
        this.initData.init(this.solution);
        this.evaluation.apply(this.solution);
        this.nbEval = 1;
        this.bestNeighbor = -1;
        this.nbStep = 0;
        this.optimum = false;
    }

    protected void loopForSeeBestNeighbor() {
        for(this.range = 0; this.range < this.evaluation.getSize(); this.range++) {
            this.solution.setRangBitToBit(this.range, ! this.solution.getRangBit(this.range));
            this.evaluation.apply(this.solution);
            this.nbEval ++;
            if (this.rangBest < 0 || this.bestNeighbor < this.solution.getFitness()) {
                this.bestNeighbor = this.solution.getFitness();
                this.rangBest = this.range;
            }
            this.solution.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
            this.solution.setFitness(this.currentFitness);
        }
    }

    public String nameOfData(){
        return "bi";
    }

    public void close(){
        this.solution.close();
    }
}