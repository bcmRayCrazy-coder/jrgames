package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.games.listeners.PlayerEventsListener;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;

import java.util.ArrayList;

public class GameManager {
    public static ArrayList<Game> games;

    public void initGames(Jrgames plugin){
        new SnowFight(plugin);

        // 注册事件
        plugin.getServer().getPluginManager().registerEvents(new PlayerEventsListener(),plugin);
    }

    public static void registerGame(Game game){
        games.add(game);
    }
    public static ArrayList<Game> getGames() {
        return games;
    }
}
