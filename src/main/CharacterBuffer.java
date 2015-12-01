package main;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class CharacterBuffer {

    private char c;
    private boolean hasValue = false;


    public synchronized void putAsync(char c) {
        this.c = c;
        hasValue = true;
    }

    public synchronized char getAsync() {
        hasValue = false;
        return c;
    }


    public synchronized void putSync(char c) {
        if (hasValue) {
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("Interrupted");
            }
        }
        this.c = c;
        hasValue = true;
        notify();
    }

    public synchronized char getSync() {
        if (!hasValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        hasValue = false;
        notify();
        return c;
    }


}

