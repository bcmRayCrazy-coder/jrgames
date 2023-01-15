package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    private final Jrgames plugin;
    private final Number tick = 0;
    private String gameName = "";
    private GameState gameState;

    public ArrayList<Player> players;

    public Game(Jrgames plugin){
        this.plugin = plugin;
    }

    public void onTick(Number tick){}

    public void onPlayerJoin(Player p){
        players.add(p);
    }
    public void onPlayerLeave(Player p){
        players.remove(p);
    }

    public void setGameName(String name){
        this.gameName = gameName;
    }
    public String getGameName() {
        return gameName;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public GameState getGameState() {
        return gameState;
    }
    public Jrgames getPlugin() {
        return plugin;
    }
    public Number getCurrentTick() {
        return tick;
    }
}
