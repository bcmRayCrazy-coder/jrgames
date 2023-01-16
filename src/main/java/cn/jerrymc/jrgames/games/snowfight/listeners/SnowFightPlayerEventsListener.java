package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.events.PlayerJoinGameEvent;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import cn.jerrymc.jrgames.lib.PlayerSender;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SnowFightPlayerEventsListener implements Listener {
    private final Jrgames plugin;
    private final SnowFight game;

    public SnowFightPlayerEventsListener(Jrgames plugin,SnowFight game){
        this.plugin = plugin;
        this.game = game;
    }

    @EventHandler
    public void playerJoin(PlayerJoinGameEvent event){
        // 玩家加入游戏
        LOGGER.logger.info("snow fight状态" + game.getGameState());
        if(game.getGameState().equals(GameState.WAITING)){
            int playerNumber = game.getPlayers().size();
            if(playerNumber >= plugin.getConfig().getInt("snowFight.minPlayers")){
                // 切换到开始状态
                game.setGameState(GameState.STARTING);
            }else if(playerNumber > plugin.getConfig().getInt("snowFight.maxPlayers")){
                // 超出上限, 踢出玩家
                PlayerSender.sendToLobby(event.getPlayer());
                event.getPlayer().sendMessage(ChatColor.RED+"无法加入该游戏, 人数已满!");
            }
        }else if(game.getGameState().equals(GameState.PLAYING)){
            // TODO 玩家切换为旁观模式观战
        }else{
            // 不允许玩家进入
            PlayerSender.sendToLobby(event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.RED+"无法加入该游戏, 它正在停止或重启中!");
        }
        LOGGER.logger.info("snow fight处理事件后状态" + game.getGameState());
    }
}
