package cn.jerrymc.jrgames.games.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 玩家离开游戏事件
 */
public class PlayerLeaveGameEvent extends GameEvent {
    private final Player player;

    public PlayerLeaveGameEvent(Player player, String gameName){
        super(gameName);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
