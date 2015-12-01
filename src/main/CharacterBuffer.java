package main;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class CharacterBuffer {

    private char c;
    private boolean hasValue;
    private Object lock = new Object();

    public void putAsync(char c) {
        this.c = c;
        hasValue = true;
    }

    public char getAsync(){
        hasValue = false;
        return c;
    }

    public void putSync(char c){
        this.c = c;
    }

    public char getSync(){
        return c;
    }



}

