package main;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class Writer extends Thread {

    private Controller controller;
    private CharacterBuffer buffer;

    private String s;

    public Writer(Controller controller, CharacterBuffer buffer) {
        this.controller = controller;
        this.buffer = buffer;
    }

    public void startWithString(String s) {
        this.s = s;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        int count = 0;
        char c;

        while (count < s.length()) { //Så länge de finns bokstäver kvar
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
                sleep(70);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        controller.stopProcess();
    }
}
