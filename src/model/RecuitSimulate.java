package model;

import java.util.Random;
import java.util.stream.DoubleStream;

public class RecuitSimulate extends HillClimberFirst {
    private double T = 10.00;
    public RecuitSimulate(Random random, Evaluation evaluation, int evaluationMax, double max, String name) {
        super (random, evaluation, evaluationMax,max,name);
    }

    @Override
    public void run() {
        int temp=0;
        this.initDataHCF();
        evaluation.apply(this.solution);
        while(this.nbEval < 10000){
            this.currentFitness = this.solution.getFitness();
            Solution neigbhor = new Solution(this.solution);
            this.range = this.random.nextInt(this.evaluation.getSize());
            neigbhor.setRangBitToBit(this.range, !this.solution.getRangBit(this.range));
            this.evaluation.apply(neigbhor);
            if ((neigbhor.getFitness() - solution.getFitness()) > 0){
                solution.copyData(neigbhor);
            }
            else{
                double u = random.nextDouble();
                double toto = Math.exp((neigbhor.getFitness() - solution.getFitness())/T);
                if (u < toto){
                    solution.copyData(neigbhor);
                }
            }
            if (temp == 100){
                T*=0.95;
                temp=0;
            }
            this.nbEval++;
        }
        this.solution.writeLine(this.nbEval);
    }

    public void close() {
        this.solution.close();
    }
}
