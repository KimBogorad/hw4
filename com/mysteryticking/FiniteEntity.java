package com.mysteryticking;

public class FiniteEntity implements TickListener{
    private TickListener listener;
    private Observable observable;
    private int firings;
    private final int maxFires;

    public FiniteEntity(TickListener listener, Observable observable, int maxFires) {
        this.listener = listener;
        this.observable = observable;
        if (maxFires < 0) throw new IllegalArgumentException("maxFires cannot be negative.");
        this.maxFires = maxFires;
        firings = 0;
    }

    @Override
    public void onTick(int tick) {
        listener.onTick(tick);
        firings++;
        if (firings == maxFires)
            observable.unregister(this);
    }
}
