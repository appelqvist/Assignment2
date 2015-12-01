package main;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class Reader extends Thread {

    private CharacterBuffer buffer;
    private Controller controller;
    private boolean running = false;

    public Reader(Controller controller, CharacterBuffer buffer){
        this.controller = controller;
        this.buffer = buffer;
    }

    public void stopReader(){
        running = false;
    }

    public void startReader(){
        running = true;
        this.start();
    }


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
                sleep(70);
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }

    }
}
