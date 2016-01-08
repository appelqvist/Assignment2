package main;

import java.util.LinkedList;

/**
 * Created by Andréas Appelqvist on 2015-11-29.
 */
public class Controller {
    private Reader reader;
    private Writer writer;
    private GUIMutex gui;

    private LinkedList recivedString = new LinkedList();
    private String transferdString;

    /**
     * Konstruktor
     *
     * @param gui
     */
    public Controller(GUIMutex gui) {
        this.gui = gui;
        gui.setController(this);
        CharacterBuffer buffer = new CharacterBuffer();
        this.reader = new Reader(this, buffer);
        this.writer = new Writer(this, buffer);
        System.out.println();
    }

    /**
     * Återställer variabler och trådar i controllerklassen.
     */
    public void controllerClear() {
        stopProcess();

        CharacterBuffer buffer = new CharacterBuffer();
        reader = new Reader(this, buffer);
        writer = new Writer(this, buffer);
        recivedString.clear();
        transferdString = "";
    }

    /**
     * Kontrollerar om in-string och out-string är lika
     * Sedan kallar den på gui med resultatet för utskrivning
     */
    public void checkDifference() {
        String rec = "";
        String temp = "";

        for (int i = 0; i < recivedString.size(); i++) {
            rec += recivedString.get(i);
        }

        if (rec.length() <= transferdString.length()) {

            temp = transferdString.substring(0, rec.length());

            if (temp.equals(rec)) {
                gui.setDifference(true);
            } else {
                gui.setDifference(false);
            }

        }else{
            gui.setDifference(false);
        }
    }

    /**
     * String är slut.
     * Stoppar processen
     */
    public void stopProcess() {
        reader.stopReader();
        writer.stop();
    }

    /**
     * Lägger till en char i det mottagna meddelandet.
     *
     * @param c
     */
    public void addToRecived(char c) {
        recivedString.addLast(c);
    }

    /**
     * Skickar mottagen string till gui för utskrift.
     */
    public void sendRecived() {
        String s = "";
        for (int i = 0; i < recivedString.size(); i++) {
            s += recivedString.get(i);
        }
        gui.setRecivedString(s);
    }

    /**
     * Skickar sträng till gui för att skrivas i log
     *
     * @param s
     */
    public void printWriterLog(String s) {
        gui.printWriterLog(s);
    }

    /**
     * Skickar sträng till gui för att skrivas i log
     *
     * @param s
     */
    public void printReaderLog(String s) {
        gui.printReaderLog(s);
    }

    /**
     * Frågar vilket mode som är aktiverat
     *
     * @return
     */
    public boolean isSyncMode() {
        return gui.modeSelected();
    }

    /**
     * Startar transfereringen av string
     *
     * @param s
     */
    public void startTransfer(String s) {
        transferdString = s;
        writer.startWithString(s);
        reader.startReader();
        gui.setTransmittedString(s);
    }
}
