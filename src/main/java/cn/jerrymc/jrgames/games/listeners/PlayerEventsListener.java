package cn.jerrymc.jrgames.games.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.Game;
import cn.jerrymc.jrgames.games.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerEventsListener implements Listener {
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event){
        String worldName = event.getPlayer().getWorld().getName();
        String originWorldName = event.getFrom().getName();

        LOGGER.logger.info(String.format("玩家 %s 从地图 %s 进入了地图 %s",event.getPlayer().getName(),originWorldName,worldName));

        for(Game game:GameManager.getGames()){
            // 当前game的地图名
            String currentGameWorldName = Jrgames.plugin.getConfig().getString("gameMap."+game.getGameName());

            if(worldName.equals(currentGameWorldName)){
                // 进入游戏
                game.onPlayerJoin(event.getPlayer());
            }
            if(originWorldName.equals(currentGameWorldName)){
                // 退出游戏
                game.onPlayerLeave(event.getPlayer());
            }
        }
    }
}
