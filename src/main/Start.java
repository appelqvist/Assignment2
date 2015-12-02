package main;

/**
 * Created by Andréas Appelqvist on 2015-11-29.
 */
public class Start {
    public static void main(String [ ] args){
        GUIMutex guiMutex = new GUIMutex();
        new Controller(guiMutex);
        guiMutex.start();
    }
}
