import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ixBet implements IObservable {
    private int betId;
    private String result;
    private List<IObserver> observers;

    public ixBet(int betId) {
        this.betId = betId;
        observers = new ArrayList<IObserver>();
    }

    public void PlayBet() {
        result = (ThreadLocalRandom.current().nextInt(0, 2) == 1) ? "won" : "lost";
        NotifyObservers();
    }

    @Override
    public void RegisterObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void RemoveObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void NotifyObservers() throws NullPointerException {
        String[] info = new String[2];
        info[0] = result;
        info[1] = Integer.valueOf(betId).toString();

        try {
            for (IObserver observer : observers) {
                observer.Update(info);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Наблюдатель был null");
        }
    }

    public String getResult() {
        return result;
    }

    public List<IObserver> getObservers() {
        return  List.copyOf(observers);
    }
}