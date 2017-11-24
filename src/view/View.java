package view;

import controller.Enter;
import model.*;

import java.util.Random;

public class View {
    final int LENGHT = 1000;
    private Enter myEnter;
    private int nbEval;
    private double maxFitness;
    private long begin;
    private Random random;
    Search randomSearch = null, randomWalk = null, hillClimbbest = null, hillClimbFirst = null, hillClimbWithout = null;
    Search rc = null;
    private knapsnack myKnapsnack;

    public View() {
        this.myEnter = new Enter();
    }

    public void choiceNbEval() {
        System.out.println("Choose the max rating you want");
    }

    public void selectFitnessEvaluation() {
        System.out.println("Choose the max rating you want");
        this.chooseNbEval();
        System.out.println("Choose the max fitness you want");
        this.chooseFitness();
    }

    public void treatment(){
        this.random = new Random(LENGHT);
        this.myKnapsnack = new knapsnack();
        this.treatmentRS();
        this.treatmentRW();
        this.treatmentHCB();
        this.treatmentHCF();
        this.treatmentHCFWR();
        this.tretamentRecuit();
    }

    private void treatmentRS(){
        System.out.println("treatment of the random search .........");
        this.begin = System.currentTimeMillis();
        this.randomSearch = new RandomSearch(this.random, this.myKnapsnack, this.nbEval);
        this.randomSearch.run();
        System.out.println("run time of randomSearch : " + (System.currentTimeMillis()-this.begin));
    }

    private void treatmentRW(){
        System.out.println("treatment of the random walk .........");
        this.begin = System.currentTimeMillis();
        this.randomWalk = new RandomWalk(this.random, this.myKnapsnack, this.nbEval);
        this.randomWalk.run();
        System.out.println("run time of random walk : " + (System.currentTimeMillis()-this.begin));
    }

    private void treatmentHCB(){
        System.out.println("treatment of the random hill climber best improvement .........");
        this.begin = System.currentTimeMillis();
        this.hillClimbbest = new HillClimberBest(this.random, this.myKnapsnack, this.nbEval, this.maxFitness);
        for (int i = 0;i<500;i++){
            this.hillClimbbest.run();
        }
        HillClimberBest tempToClose = (HillClimberBest)this.hillClimbbest;
        tempToClose.close();
        System.out.println("run time of hill climber best improvement : " + (System.currentTimeMillis()-this.begin));
    }

    private void treatmentHCF(){
        System.out.println("treatment of the random hill climber first improvement .........");
        this.begin = System.currentTimeMillis();
        this.hillClimbFirst = new HillClimberFirst(this.random, this.myKnapsnack, this.nbEval, this.maxFitness);
        for (int i = 0;i<500;i++){
            this.hillClimbFirst.run();
        }
        HillClimberFirst tempToClose = (HillClimberFirst)this.hillClimbFirst;
        tempToClose.close();
        System.out.println("run time of hill climber first imporvement : " + (System.currentTimeMillis()-this.begin));
    }

    private void treatmentHCFWR(){
        System.out.println("treatment of the random hill climber first improvement whitout replacement.........");
        this.begin = System.currentTimeMillis();
        this.hillClimbWithout = new HillClimberFirstWhitoutReplacement(this.random, this.myKnapsnack, this.nbEval, this.maxFitness);
        for (int i =0;i <500;i++){
            this.hillClimbWithout.run();
        }
        HillClimberFirstWhitoutReplacement tempToClose = (HillClimberFirstWhitoutReplacement)this.hillClimbWithout;
        tempToClose.close();
        System.out.println("run time of hill climber first imporvement whitout replacement : " + (System.currentTimeMillis()-this.begin));
    }

    public void tretamentRecuit(){
        System.out.println("treatment of the recuit simulate .........");
        this.myKnapsnack = new knapsnack();
        this.rc = new RecuitSimulate(this.random, this.myKnapsnack, this.nbEval, this.maxFitness, "rc");
        for (int i = 0;i<500;i++){
            this.rc.run();
        }
        RecuitSimulate tempToClose = (RecuitSimulate)this.rc;
        tempToClose.close();
        System.out.println("run time of the recuit simulate : " + (System.currentTimeMillis()-this.begin));
    }

    private void chooseNbEval (){
        this.nbEval = this.myEnter.enterInt();
    }

    private void chooseFitness (){
        this.maxFitness = this.myEnter.enterDouble();
    }
}
