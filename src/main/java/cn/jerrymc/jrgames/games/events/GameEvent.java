package cn.jerrymc.jrgames.games.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 游戏内事件
 */
public class GameEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final String gameName;

    public GameEvent(String gameName){
        this.gameName = gameName;
    }

    public boolean isGame(String name){
        return Objects.equals(gameName, name);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){return HANDLERS;}

    public String getGameName() {
        return gameName;
    }
}
