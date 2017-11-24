package model;

import java.util.Random;

public class HillClimberFirst extends Search {
    private String name = "fi";
    protected Random random;
    private int nbEvalMax;
    private InitData initData;
    private int nbStep;
    private double maxFitness;
    protected int range;
    protected double currentFitness;

    public HillClimberFirst(Random random, Evaluation evaluation, int evaluationMax, double max) {
        super(evaluation);
        this.nbEvalMax = evaluationMax;
        this.maxFitness = max;
        this.random = random;
        this.initData = new InitData(random, evaluation.getSize());
        this.solution = new Solution(name);
    }

    protected HillClimberFirst(Random random, Evaluation evaluation, int evaluationMax, double max, String name) {
        super(evaluation);
        this.name = name;
        this.nbEvalMax = evaluationMax;
        this.maxFitness = max;
        this.random = random;
        this.initData = new InitData(random, evaluation.getSize());
        this.solution = new Solution(name);
    }

    public void run() {
        this.initDataHCF();
        while(this.nbEval < this.nbEvalMax && this.solution.getFitness() < this.maxFitness) {
            this.currentFitness = this.solution.getFitness();
            this.range = this.random.nextInt(this.evaluation.getSize());
            this.solution.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
            this.evaluation.apply(this.solution);
            this.nbEval += 1;
            if (this.solution.getFitness() <= this.currentFitness) {
                this.solution.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
                this.solution.setFitness(this.currentFitness);
            } else{
                this.nbStep += 1;
            }
        }
        this.solution.writeLine(this.nbEval);
    }

    protected void initDataHCF() {
        this.initData.init(this.solution);
        this.evaluation.apply(this.solution);
        this.nbEval = 1;
        this.range = 0;
        this.nbStep = 0;
    }

    public String nameOfData(){
        return name;
    }

    public void close(){
        this.solution.close();
    }
}
