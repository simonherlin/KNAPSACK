package model;

import java.util.Random;

public class HillClimberFirstWhitoutReplacement extends Search {
    final private String name = "fw";
    private Random random;
    private int nbEvalMax;
    private InitData initData;
    private int nbStep;
    private double maxFitness;
    private int [] neighbors;
    private int range;
    private double currentFitness;
    private boolean optimum;
    private double fitnessNeighbor;
    private int rangeNeighbors;
    private int nbNeighbor;

    public HillClimberFirstWhitoutReplacement(Random random, Evaluation evaluation, int maxEval, double max) {
        super(evaluation);
        this.nbEvalMax = maxEval;
        this.maxFitness = max;
        this.random = random;
        this.initData = new InitData(random, evaluation.getSize());
        this.neighbors = new int[evaluation.getSize()];
        for(int i = 0; i < evaluation.getSize(); i++){
            this.neighbors[i] = i;
        }
        this.solution = new Solution(name);
    }

    public void run() {
        this.initDataHCFWR();
        while(!this.optimum && this.nbEval < this.nbEvalMax && this.solution.getFitness() < this.maxFitness) {
            this.currentFitness = this.solution.getFitness();
            this.nbNeighbor = 0;
            this.fitnessNeighbor = this.currentFitness - 1;
            this.loopFitnessNeighbor();
            if (this.currentFitness < this.fitnessNeighbor) {
                this.solution.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
                this.solution.setFitness(this.fitnessNeighbor);
                this.nbStep += 1;
            } else{
                this.optimum = true;
            }
        }
        this.solution.writeLine(this.nbEval);
    }

    private void initDataHCFWR(){
        this.initData.init(this.solution);
        this.evaluation.apply(this.solution);
        this.nbEval = 1;
        this.range = 0;
        this.nbStep = 0;
        this.optimum = false;
    }

    private void loopFitnessNeighbor(){
        while (this.fitnessNeighbor < this.currentFitness && this.nbNeighbor < this.evaluation.getSize()) {
            this.rangeNeighbors = this.random.nextInt(this.evaluation.getSize() - this.nbNeighbor);
            this.range = this.neighbors[this.rangeNeighbors];
            this.solution.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
            this.evaluation.apply(this.solution);
            this.nbEval ++;
            this.fitnessNeighbor = this.solution.getFitness();
            this.solution.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
            this.solution.setFitness(currentFitness);
            nbNeighbor += 1;
            this.neighbors[this.rangeNeighbors] = this.neighbors[this.evaluation.getSize() - this.nbNeighbor] ;
            this.neighbors[this.evaluation.getSize() - this.nbNeighbor] = this.range;
        }
    }

    public String nameOfData(){
        return "fw";
    }

    public void close(){
        this.solution.close();
    }
}
