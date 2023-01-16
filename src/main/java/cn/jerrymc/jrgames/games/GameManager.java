package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.listeners.PlayerEventsListener;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import cn.jerrymc.jrgames.lib.PlayerSender;
import cn.jerrymc.jrgames.lib.ScreenEffectType;
import cn.jerrymc.jrgames.lib.ScreenEffects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {
    public static ArrayList<Game> games = new ArrayList<>();

    /**
     * 初始化所有游戏
     * @param plugin 传入Jrgames类即可
     */
    public void initGames(Jrgames plugin){
        new SnowFight(plugin);

        // 注册事件
        plugin.getServer().getPluginManager().registerEvents(new PlayerEventsListener(),plugin);
    }

    /**
     * 强制停止一个游戏
     * @param game 目标游戏
     */
    public void forceStopGame(Game game){
        for(Player p: game.getPlayers()){
            // 踢出玩家
            PlayerSender.sendToLobby(p);
            ScreenEffects.startEffect(ScreenEffectType.fullscreen_transparent,"RED",10,200,10,false,p.getName(),"游戏被强制停止, 您已被踢出游戏! 本次游玩不算入统计信息");
        }
        game.gameStopHandler();
    }

    /**
     * 强制停止所有游戏
     */
    public void forceStopAllGames(){
        for(Game g:getGames()){
            forceStopGame(g);
        }
    }

    public static void registerGame(Game game){
        games.add(game);
        LOGGER.logger.info("添加了新游戏 "+ ChatColor.AQUA+ game.getGameName());
    }
    public static ArrayList<Game> getGames() {
        return games;
    }
}
