public interface Observable {
    public void register(TickListener listener);
    public void notifyListeners(int tick);
}