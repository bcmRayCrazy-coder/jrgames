package cn.jerrymc.jrgames.games.events;

public class GameTickEvent extends GameEvent{
    private final int tick;
    public GameTickEvent(int tick,String gameName) {
        super(gameName);
        this.tick = tick;
    }

    public int getTick() {
        return tick;
    }
}
