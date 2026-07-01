import java.util.List;
import java.util.ArrayList;

public class PipeBomb implements Observable {
    private final int countdown;
    private final List<TickListener> listeners;

    public PipeBomb(int countdown) {
        if (countdown < 1) throw new IllegalArgumentException("Countdown cannot be less than 1.");
        this.countdown = countdown;
        listeners = new ArrayList<>();
    }
    @Override
    public void register(TickListener listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyListeners(int tick) {
        for (TickListener listener : listeners) {
            listener.onTick(tick);
        }
    }

    public void start() {
        for (int i = 1; i <= countdown; i++ ) {
            notifyListeners(i);
        }
        System.out.println("BOOM!");
    }


}