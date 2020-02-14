package utils;

import java.util.ArrayList;
import java.util.List;

public abstract class MyObservable<T> {

    private List<MyObserver<T>> observers = new ArrayList<>();

    public void addObserver(MyObserver<T> observer) {
        observers.add(observer);
    }

    public void removeObserver(MyObserver<T> observer) {
        observers.remove(observer);
    }

    public void notifyObserver(MyObserver<T> o, T t) {
        o.update(t);
    }

    public void notifyObservers(T t) {
        observers.forEach(observer -> observer.update(t));
    }

}
