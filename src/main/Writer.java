package main;

/**
 * Created by Andréas Appelqvist on 2015-11-29.
 */
public class Writer extends Thread {

    private Controller controller;
    private CharacterBuffer buffer;
    private String s;
    private boolean running = false;

    /**
     * Konstruktor
     * @param controller
     * @param buffer
     */
    public Writer(Controller controller, CharacterBuffer buffer) {
        this.controller = controller;
        this.buffer = buffer;
    }

    /**
     * Startar tråden med en string som ska skickas
     * @param s
     */
    public void startWithString(String s) {
        this.s = s;
        running = true;
        this.start();
    }

    /**
     * Stoppar writer
     */
    public void stopWriter(){
        running = false;
    }

    /**
     * Thread
     */
    @Override
    public void run() {
        super.run();
        int count = 0;
        char c;

        while (count < s.length() && running) { //Så länge de finns bokstäver kvar
            c = s.charAt(count);

            if (controller.isSyncMode()) {
                buffer.putSync(c);
                controller.printWriterLog("Sync : " + c);
            } else {
                buffer.putAsync(c);
                controller.printWriterLog("ASync : " + c);
            }
            count++;

            try {
                int rand =  (int)(Math.random()*1000);
                sleep(rand);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Vi är färdiga stoppar alla andra processer
        controller.stopProcess();
    }
}
