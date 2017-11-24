package model;

import controller.EcritureFichierR;

import java.util.Random;

public class Solution {
    private EcritureFichierR write;
    private boolean[] bitString;
    private double fitness;
    private String name;

    public Solution(String name){
        this.name = name;
        this.openWrite();
        this.fitness = 0;
        this.bitString = new boolean[1000];
    }

    public Solution(Solution solution){
        this.fitness = solution.getFitness();
        this.bitString = new boolean[1000];
        for (int i =0; i< solution.getBitString().length;i++){
            this.bitString[i] = solution.getBitString()[i];
        }
    }

    public boolean[] getBitString(){
        return this.bitString;
    }

    public double getFitness(){
        return this.fitness;
    }

    public void setBitString(boolean[] bitString){
        for (int i =0; i< bitString.length;i++){
            this.bitString[i] = bitString[i];
        }
    }

    public void setFitness(double fitness){
        this.fitness = fitness;
    }

    public void setRangBit (int rang, Random random){
        this.bitString[rang] = random.nextBoolean();
    }

    public void setRangBitToBit (int rang, boolean data){
        this.bitString[rang] = data;
    }

    public boolean getRangBit(int rang){
        return this.bitString[rang];
    }

    public Solution getBestSolution() {
        return new Solution(this);
    }

    public void copyData(Solution solution) {
        this.setBitString(solution.getBitString());
        this.setFitness(solution.getFitness());
    }

    private void openWrite(){
        this.write = new EcritureFichierR(this.name+".csv");
    }

    public void writeLine(int nbEval){
        this.write.EcrireLigne(nbEval + " " + this.fitness);
    }

    public int size() {
        if (this.bitString == null) {
            return 0 ;
        } else {
            return this.bitString.length;
        }
    }

    public void close(){
        this.write.FermetureFichier();
    }
}
