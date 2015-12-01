package main;

/**
 * Created by andreasappelqvist on 2015-11-29.
 */
public class Reader extends Thread {

    private CharacterBuffer buffer;
    private Controller controller;

    private String text;

    public Reader(Controller controller, CharacterBuffer buffer){
        this.controller = controller;
        this.buffer = buffer;
    }

    //Ändring
    public void startReader(String s){
        text = s;
        this.start();
    }

    @Override
    public void run() {
        super.run();

        char c;

        while(true){
            //hämtar från buffer och loggar.

            if(controller.isSyncMode()){
                c = buffer.getSync();

            }else{
                c = buffer.getAsync();
            }
        }

    }
}
