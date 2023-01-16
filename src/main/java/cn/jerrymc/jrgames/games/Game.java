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
    private final Number tick = 0;
    private String gameName = "";
    private String displayName = "";
    private GameState gameState = GameState.WAITING;
    private ArrayList<Listener> gameListeners;

    public ArrayList<Player> players;

    public Game(Jrgames plugin){
        this.plugin = plugin;
    }

    public void gameStopHandler(){
        // 停止监听
        for(Listener l : getGameListeners()){
            HandlerList.unregisterAll(l);
        }
    }

    public void onTick(Number tick){}

    public void onPlayerJoin(Player p){
        players.add(p);
        Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(p,getGameName()));
    }
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
