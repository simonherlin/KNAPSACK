package model;

abstract public class Evaluation {
    private  int size;

    public Evaluation() {
        this.size = 0;
    }

    abstract public void apply(Solution solution) ;

    public void  setSize(int size){
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
