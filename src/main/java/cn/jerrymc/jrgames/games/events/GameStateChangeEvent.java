package cn.jerrymc.jrgames.games.events;

import cn.jerrymc.jrgames.games.GameState;
import org.bukkit.event.Event;

public class GameStateChangeEvent extends GameEvent {
    private final GameState currentGameState;

    public GameStateChangeEvent(GameState currentGameState,String gameName){
        super(gameName);
        this.currentGameState = currentGameState;
    }


    public GameState getCurrentGameState() {
        return currentGameState;
    }
}
