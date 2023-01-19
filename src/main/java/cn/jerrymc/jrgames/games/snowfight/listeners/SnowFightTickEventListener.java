package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.GameCountDown;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.events.GameTickEvent;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import cn.jerrymc.jrgames.lib.PlayerSender;
import cn.jerrymc.jrgames.lib.ScreenEffectType;
import cn.jerrymc.jrgames.lib.ScreenEffects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.MessageFormat;

public class SnowFightTickEventListener implements Listener {
    private final Jrgames plugin;
    private final SnowFight game;
    private final GameCountDown countDown;

    private final int playTime;

    public SnowFightTickEventListener(Jrgames plugin,SnowFight game){
        this.plugin = plugin;
        this.game = game;
        this.countDown = new GameCountDown(plugin,game);
        playTime = plugin.getConfig().getInt("snowFight.playTime");
    }

    @EventHandler
    public void onTick(GameTickEvent event){
        switch (game.getGameState()) {
            case STARTING:
                this.countDown.onTick(event);
                break;
            case PLAYING:
                int timeLess = playTime - event.getTick();
                if (event.getTick() % 20 == 0) {
                    int second = timeLess / 20;
                    if (second % 30 == 0 || second <= 5) {
                        for (Player p : game.getPlayers()) {
                            p.sendMessage(MessageFormat.format("{0}游戏剩余 {1}{2}{3} 秒", ChatColor.YELLOW, ChatColor.RED, second, ChatColor.YELLOW));
                        }
                    }
                }
                if (timeLess <= 0) {
                    // 游戏结束
                    LOGGER.debug("snowFight游戏结束");
                    StringBuilder win = new StringBuilder();
                    for (Player p : game.getPlayers()) {
                        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
                            ScreenEffects.startEffect(ScreenEffectType.fullscreen_transparent, "GREEN", 10, 60, 10, false, p.getName(), "胜利!");
                            win.append(p.getName()).append(", ");
                        } else {
                            ScreenEffects.startEffect(ScreenEffectType.fullscreen_transparent, "BLUE", 10, 60, 10, false, p.getName(), "游戏结束!");
                        }
                    }
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.sendMessage(MessageFormat.format("{0}[雪战游戏]{1}游戏结束, 胜利者: {2}{3}", ChatColor.AQUA, ChatColor.YELLOW, ChatColor.GREEN, win));
                    }
                    game.setGameState(GameState.ENDING);
                }
                break;
            case ENDING:
                if (event.getTick() % 100 == 0) {
                    if(game.getPlayers() != null) {
                        for (Player p : game.getPlayers()) {
                            PlayerSender.sendToLobby(p);
                        }
                    }
                    LOGGER.debug("snowFight游戏停止, 已将玩家送回大厅");
                    game.setGameState(GameState.STOPPING);
                }
                break;
        }
    }
}
