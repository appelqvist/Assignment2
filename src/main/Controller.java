package main;

import java.util.LinkedList;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class Controller {
    private Reader reader;
    private Writer writer;
    private GUIMutex gui;

    private LinkedList recivedString = new LinkedList();
    private String transferdString;

    public Controller(GUIMutex gui){
        this.gui = gui;
        gui.setController(this);
        CharacterBuffer buffer = new CharacterBuffer();
        this.reader = new Reader(this, buffer);
        this.writer = new Writer(this, buffer);
    }

    public void controllerClear(){
        stopProcess();

        CharacterBuffer buffer = new CharacterBuffer();
        reader = new Reader(this, buffer);
        writer = new Writer(this, buffer);
        recivedString.clear();
        transferdString = "";
    }

    public void checkDifference(){
        String rec = "";
        for(int i = 0; i < recivedString.size(); i++){
            rec += recivedString.get(i);
        }
        if(transferdString.equals(rec)){
            gui.setDifference(true);
        }else{
            gui.setDifference(false);
        }
    }

    public void stopProcess(){
        reader.stopReader();
    }

    public void addToRecived(char c){
        recivedString.addLast(c);
    }

    public void sendRecived(){
        String s  = "";
        for(int i = 0; i < recivedString.size(); i++){
            s += recivedString.get(i);
        }
        gui.setRecivedString(s);
    }

    public void printWriterLog(String s){
        gui.printWriterLog(s);
    }

    public void printReaderLog(String s){
        gui.printReaderLog(s);
    }

    public boolean isSyncMode(){
        return gui.modeSelected();
    }

    public void startTransfer(String s) {
        transferdString = s;
        writer.startWithString(s);
        reader.startReader();
        gui.setTransmittedString(s);
    }
}
