package cn.jerrymc.jrgames.games.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinGameEvent extends GameEvent {
    private final Player player;

    public PlayerJoinGameEvent(Player player,String gameName){
        super(gameName);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
