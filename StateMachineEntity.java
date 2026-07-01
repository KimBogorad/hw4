import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class StateMachineEntity implements TickListener {
    private final String entityName;
    private final int startTick;
    private final int cycLen;
    private final List<State> states;
    private final Map<Integer, State> stateEntryMap;
    //can add more fields to implement this

    public StateMachineEntity(String entityName, int startTick, State... states) {
        this.entityName = entityName;
        this.startTick = startTick;
        if(states == null || states.length == 0) {
            throw new IllegalArgumentException("Entity must have at least one state!");
        }
        this.states = List.of(states); //enough to ensure secure copy because records are immutable.
        stateEntryMap = new HashMap<>();
        int currentSum = 0;
        for (State state : states) {
            stateEntryMap.put(currentSum, state);
            currentSum += state.cost();
        }
        this.cycLen = currentSum;
    }

    @Override
    public void onTick(int tick) {
        if (tick < startTick) return;
        int posInCycle = (tick - startTick) % cycLen;
        State currentState = stateEntryMap.get(posInCycle);
        if(currentState != null) //name can't be null
            System.out.println(currentState.name());
    }

    @Override
    public String toString() { return entityName; }

    // getters
}