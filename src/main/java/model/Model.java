package model;

import utils.MyObservable;

import java.util.Random;

public class Model extends MyObservable<Result> {
    private static Model instance;
    private int random;
    private int nbPlayed = 0;

    private Model() {
        init();
    }

    public static Model getInstance() {
        return instance = instance == null ? new Model() : instance;
    }

    public void process(int number) {
        nbPlayed++;
        Result result = new Result(random - number, nbPlayed, number);
        notifyObservers(result);
    }

    public void error() {
        notifyObservers(new Result());
    }

    public void init() {
        random = new Random().nextInt(100) + 1;
        // System.out.println(random);
    }
}
