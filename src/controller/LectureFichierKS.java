package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureFichierKS {
    private File file;
    private int size;
    private int[] profits;
    private int[] weights;
    private int capacity;
    private Scanner scanner;
    private  double beta;

    public LectureFichierKS(String Path){
        file = new File (Path);
    }

    public void readAllData(){
        try {
            this.scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        size = scanner.nextInt();
        profits = new int[size];
        weights = new int[size];
        for(int i = 0; i < size; i++){
            profits[i] = scanner.nextInt();
        }

        for(int i = 0; i < size; i++){
            weights[i] = scanner.nextInt();
        }
        capacity = scanner.nextInt();

        beta = 0;
        for(int i = 0; i < size; i++){
            if (weights[i] > 0){
                if (beta < profits[i] / weights[i]){
                    beta = profits[i] / weights[i];
                }
            }
        }

        scanner.close();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getProfits() {
        return profits;
    }

    public void setProfits(int[] profits) {
        this.profits = profits;
    }

    public int[] getWeights() {
        return weights;
    }

    public void setWeights(int[] weights) {
        this.weights = weights;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}
