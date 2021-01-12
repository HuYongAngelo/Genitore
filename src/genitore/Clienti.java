package genitore;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Clienti implements Runnable {
    private boolean soddisfatto = false;
    private boolean stop = false;
    private Panino kebab;
    private int n;
    
    public Clienti(Panino kebab, int n) {
        this.kebab = kebab;
        this.n = n;
    }
    
    public void termina() {
        stop=true;
    }
    
    public void run() {
        while (!stop) {
            while (!soddisfatto) {
                soddisfatto = kebab.prendiPanino();
                if (soddisfatto == true) {
                    System.out.println("Thread soddisfatto "+n);
                    notify();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Clienti.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
