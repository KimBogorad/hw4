package com.mysteryticking;

public interface Observable {
    public void register(TickListener listener);
    public void notifyListeners(int tick);
    //Bonus:
    public boolean unregister(TickListener listener);
}