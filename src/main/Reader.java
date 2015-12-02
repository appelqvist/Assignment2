package main;

/**
 * Created by Andréas Appelqvist on 2015-11-29.
 */
public class Reader extends Thread {

    private CharacterBuffer buffer;
    private Controller controller;
    private boolean running = false;

    /**
     * Konstruktor
     */
    public Reader(Controller controller, CharacterBuffer buffer){
        this.controller = controller;
        this.buffer = buffer;
    }

    /**
     * Stoppar Thread
     */
    public void stopReader(){
        running = false;
    }

    /**
     * Startar Thread
     */
    public void startReader(){
        running = true;
        this.start();
    }


    /**
     * Thread
     */
    @Override
    public void run() {
        super.run();

        char c;
        while(running){
            if(controller.isSyncMode()){
                c = buffer.getSync();
                controller.printReaderLog("Sync: "+c);
            }else{
                c = buffer.getAsync();
                controller.printReaderLog("ASync: "+c);
            }

            controller.addToRecived(c);
            controller.sendRecived();
            controller.checkDifference();

            try{
                int rand =  (int) (Math.random()*1000);
                sleep(rand);
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }

    }
}
