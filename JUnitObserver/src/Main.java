// Демонстрация паттерна Observer

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ixBet bet142 = new ixBet(142);
        List<Sheeple> sheeples = new ArrayList<Sheeple>();
        int n = 4;

        for (int i = 0; i < n; ++i) {
            sheeples.add(new Sheeple(i));
        }

        for (Sheeple sheeple : sheeples) {
            bet142.RegisterObserver(sheeple);
        }

        bet142.PlayBet();

        for (Sheeple sheeple : sheeples) {
            bet142.RemoveObserver(sheeple);
        }
    }
}