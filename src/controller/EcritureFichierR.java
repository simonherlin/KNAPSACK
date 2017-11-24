package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EcritureFichierR {

    private File f;
    private FileWriter fw;

    public EcritureFichierR(String Path) {
        f = new File (Path);
        try {
            fw = new FileWriter (f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EcrireLigne("nbeval fitness");
    }

    public void EcrireLigne(String Ligne){
        try {
            fw.write (Ligne + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FermetureFichier(){
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
