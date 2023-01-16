package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.games.events.PlayerJoinGameEvent;
import cn.jerrymc.jrgames.games.events.PlayerLeaveGameEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class Game {
    private final Jrgames plugin;
    // 滴答
    private final Number tick = 0;
    // 游戏id
    private String gameName = "";
    // 显示的名字
    private String displayName = "";
    // 当前游戏状态
    private GameState gameState = GameState.WAITING;
    // 游戏监听器
    private ArrayList<Listener> gameListeners = new ArrayList<>();

    public ArrayList<Player> players = new ArrayList<>();

    public Game(Jrgames plugin){
        this.plugin = plugin;
    }

    public void gameStopHandler(){
        // 停止监听
        for(Listener l : getGameListeners()){
            HandlerList.unregisterAll(l);
        }
    }

    /**
     * 时刻改变
     * @param tick 目前时刻
     */
    public void onTick(Number tick){}

    /**
     * 玩家进入游戏
     * @param p 玩家
     */
    public void onPlayerJoin(Player p){
        players.add(p);
        Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(p,getGameName()));
    }

    /**
     * 玩家离开游戏
     * @param p 玩家
     */
    public void onPlayerLeave(Player p){
        players.remove(p);
        Bukkit.getPluginManager().callEvent(new PlayerLeaveGameEvent(p,getGameName()));
    }

    public void setGameName(String name){
        this.gameName = name;
    }
    public String getGameName() {
        return gameName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public GameState getGameState() {
        return gameState;
    }

    public void registerGameListener(Listener listener){
        getGameListeners().add(listener);
        getPlugin().getServer().getPluginManager().registerEvents(listener,getPlugin());
    }
    public ArrayList<Listener> getGameListeners() {
        return gameListeners;
    }

    public Jrgames getPlugin() {
        return plugin;
    }
    public Number getCurrentTick() {
        return tick;
    }
}
