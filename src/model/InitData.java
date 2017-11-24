package model;

import java.util.Random;

public class InitData {
    private int size ;
    private Random random;

    public InitData(Random random, int n) {
        this.random = random;
        this.size = n;
    }

    public void init(Solution solution) {
        if (solution.size() != this.size) {
            solution.setBitString(new boolean[size]);
        }
        for(int i = 0; i < size; i++) {
            solution.setRangBit(i,this.random);
        }
    }
}