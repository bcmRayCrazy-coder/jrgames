package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.GameCountDown;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.events.GameTickEvent;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SnowFightTickEventListener implements Listener {
    private final Jrgames plugin;
    private final SnowFight game;
    private final GameCountDown countDown;

    public SnowFightTickEventListener(Jrgames plugin,SnowFight game){
        this.plugin = plugin;
        this.game = game;
        this.countDown = new GameCountDown(plugin,game);
    }

    @EventHandler
    public void onTick(GameTickEvent event){
        switch (game.getGameState()){
            case STARTING:
                this.countDown.onTick(event);
        }
    }
}
