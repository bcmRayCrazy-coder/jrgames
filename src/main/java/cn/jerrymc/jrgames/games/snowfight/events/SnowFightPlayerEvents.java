package cn.jerrymc.jrgames.games.snowfight.events;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.events.PlayerJoinGameEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SnowFightPlayerEvents implements Listener {
    private final Jrgames plugin;
    public SnowFightPlayerEvents(Jrgames plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinGameEvent event){
        LOGGER.logger.info(event.getPlayer().getClientBrandName()+" 加入了游戏 "+event.getGameName());
    }
}
