// Bonus: 

public class FiniteEntity implements TickListener{
    private TickListener listener;
    private Observable observable;
    private int firings;
    private final int maxFires;

    public FiniteEntity(TickListener listener, Observable observable, int maxFires) {
        this.listener = listener;
        this.observable = observable;
        this.maxFires = maxFires; //assuming maxFires >= 0
        firings = 0;
    }

    @Override
    public void onTick(int tick) {
        listener.onTick(tick);
        if (listener.getDidFire())
        //check if fired - if so firings++
        if (firings == maxFires)
            observable.unregister(this); //here this object dies.
    }
}
