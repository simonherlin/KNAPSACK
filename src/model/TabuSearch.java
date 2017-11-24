package model;

import java.util.Random;

public class TabuSearch extends HillClimberBest {
    public TabuSearch (Random random, Evaluation evaluation, int evaluationMax, double max, String name){
        super(random, evaluation, evaluationMax, max, name);
    }

    public void run(){
     this.initDataHCB();
    }
    
}
