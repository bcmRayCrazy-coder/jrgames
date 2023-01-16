package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.listeners.PlayerEventsListener;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class GameManager {
    public static ArrayList<Game> games = new ArrayList<>();

    public void initGames(Jrgames plugin){
        new SnowFight(plugin);

        // 注册事件
        plugin.getServer().getPluginManager().registerEvents(new PlayerEventsListener(),plugin);
    }

    public static void registerGame(Game game){
        games.add(game);
        LOGGER.logger.info("添加了新游戏 "+ ChatColor.AQUA+ game.getGameName());
    }
    public static ArrayList<Game> getGames() {
        return games;
    }
}
