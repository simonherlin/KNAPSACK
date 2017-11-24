package model;

import controller.LectureFichierKS;

import java.util.Arrays;

public class knapsnack extends Evaluation{
    private int[] profits;
    private int[] weights;
    private int capacity;
    private double beta;
    private LectureFichierKS read;

    public knapsnack(){
        super();
        this.read = new LectureFichierKS("ks_1000.dat");
        this.read.readAllData();
        this.profits = new int[1000];
        this.weights = new int[1000];
        this.setSize(this.read.getSize());
/*        System.arraycopy( this.getProfits(), 0, this.read.getProfits(), 0, this.getProfits().length );
        System.arraycopy( this.getWeights(), 0, this.read.getWeights(), 0, this.getWeights().length );*/
        for (int i =0; i< this.read.getProfits().length;i++){
            this.profits[i] = this.read.getProfits()[i];
        }
        for (int i =0; i< this.read.getWeights().length;i++){
            this.weights[i] = this.read.getWeights()[i];
        }
        this.capacity = this.read.getCapacity();
        this.beta = this.read.getBeta();
    }

    public void apply(Solution solution) {
        int sumOfProfit = 0;
        int sumOfWeight = 0;
        for(int i = 0; i < solution.size(); i++){
            if (solution.getBitString()[i]) {
                sumOfProfit += profits[i];
                sumOfWeight += weights[i];
            }
        }
        if (sumOfWeight <= capacity){
            solution.setFitness(sumOfProfit);
        } else{
            solution.setFitness(sumOfProfit - beta * (sumOfWeight - capacity));
        }
    }

    public int[] getProfits(){
        return this.profits;
    }

    public int[] getWeights(){
        return this.weights;
    }
}
