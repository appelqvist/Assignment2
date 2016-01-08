package main;

/**
 * Created by Andréas Appelqvist on 2015-11-29.
 */
public class CharacterBuffer {

    private char c;
    private boolean hasValue = false;

    /**
     * Lägger char i buffer
     * @param c
     */
    public synchronized void putAsync(char c) {
        this.c = c;
        hasValue = true;
    }

    /**
     * Hämtar char från buffer
     * @return
     */
    public synchronized char getAsync() {
        hasValue = false;
        return c;
    }

    /**
     * Lägger en char i buffer sync
     * @param c
     */
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

    /**
     * Hämtar en char från buffer sync
     * @return
     */
    public synchronized char getSync() {
        char temp;
        if (!hasValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        hasValue = false;
        temp = c;
        notify();
        return temp;
    }


}

