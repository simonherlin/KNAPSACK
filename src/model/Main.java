package model;

import view.View;

public class Main {
    public static final String name = "ks_1000.dat";

    public static void main(String[] args){
        View myView = new View();
        myView.choiceNbEval();
        myView.selectFitnessEvaluation();
        myView.treatment();

/*       View myView = new View();
        myView.tretamentRecuit(10000);*/

    }
}
