package main;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class Controller {
    private Reader reader;
    private Writer writer;
    private boolean syncMode = true;
    private GUIMutex gui;

    public Controller(GUIMutex gui){
        this.gui = gui;
        gui.setController(this);
        CharacterBuffer buffer = new CharacterBuffer();
        this.reader = new Reader(this, buffer);
        this.writer = new Writer(this, buffer);
    }

    public void printWriterLog(String s){
        gui.printWriterLog(s);
    }

    //Ändrign
    public void printReaderLog(String s){
        gui.printReaderLog(s);
    }

    public boolean isSyncMode(){
        return gui.modeSelected();
    }

    public void startTransfer(String s) {
        //Startar reader och writer med strängen.
        writer.startWithString(s);
        gui.setTransmittedString(s);
    }
}
