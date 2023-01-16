package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.games.events.PlayerJoinGameEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SnowFightPlayerEventsListener implements Listener {
    private final Jrgames plugin;
    public SnowFightPlayerEventsListener(Jrgames plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinGameEvent event){
    }
}
