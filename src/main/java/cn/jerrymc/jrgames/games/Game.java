package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.events.GameStateChangeEvent;
import cn.jerrymc.jrgames.games.events.GameTickEvent;
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
    private int tick = 0;
    // 游戏id
    private String gameName = "";
    // 显示的名字
    private String displayName = "";
    // 当前游戏状态
    private GameState gameState = GameState.WAITING;
    // 游戏监听器
    private final ArrayList<Listener> gameListeners = new ArrayList<>();

    public ArrayList<Player> players = new ArrayList<>();

    public Game(Jrgames plugin){
        this.plugin = plugin;
    }

    /**
     * 游戏滴答一次
     */
    public void tick(){
        tick += 1;
        LOGGER.debug("[ticker] Current tick "+tick);
        onTick(tick);
    }

    /**
     * 游戏初始化
     */
    public void init(){
    }

    /**
     * 游戏停止
     */
    public void stop(){
        // 停止监听
        for(Listener l : getGameListeners()){
            HandlerList.unregisterAll(l);
        }
    }

    /**
     * 时刻改变
     * @param tick 目前时刻
     */
    public void onTick(int tick){
        Bukkit.getPluginManager().callEvent(new GameTickEvent(tick,getGameName()));
    }

    /**
     * 玩家进入游戏
     * @param p 玩家
     */
    public void onPlayerJoin(Player p){
        if(getGameState().equals(GameState.WAITING)||getGameState().equals(GameState.PLAYING)) {
            // 加入游玩列表
            players.add(p);
        }
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

    /**
     * 设置游戏状态
     * 此方法会触发GameStateChangeEvent事件和清除tick
     * @param state 目标游戏状态
     */
    public void setGameState(GameState state) {
        setGameStateQuite(state);
        tick = 0;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(state,getGameName()));
    }
    /**
     * 设置游戏状态
     * 静默 - 不进行任何其他操作
     * @param state 目标游戏状态
     */
    public void setGameStateQuite(GameState state){
        this.gameState = state;
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
    public int getCurrentTick() {
        return tick;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

}
