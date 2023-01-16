package cn.jerrymc.jrgames.games.events;

import org.bukkit.entity.Player;

/**
 * 玩家加入游戏事件
 */
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
