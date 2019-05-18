package a2.demo.obs;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject  {
    private List<Observer> observerList=new ArrayList<Observer>();
    public void attach(Observer o){
        observerList.add(o);
    }

    public void detach(Observer o){
        observerList.remove(o);
    }

    public void notifyObservers()
    {
        for (Observer o : observerList) {
            o.notify();
        }
    }
}
