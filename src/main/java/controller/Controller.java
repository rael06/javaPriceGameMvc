package controller;

import model.Model;

public class Controller {
    private static Controller instance;
    private Model model = Model.getInstance();

    private Controller() {
    }

    public static Controller getInstance() {
        return instance = instance == null ? new Controller() : instance;
    }

    public void compare(String saisie) {
        try{
            int nombre = Integer.parseInt(saisie);
            model.process(nombre);
        }
        catch(Exception e){
            model.error();
        }
    }

    public void restart() {
        model.init();
    }
}
