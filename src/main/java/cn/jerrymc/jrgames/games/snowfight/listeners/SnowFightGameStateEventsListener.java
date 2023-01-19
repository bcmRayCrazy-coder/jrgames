package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.events.GameStateChangeEvent;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import cn.jerrymc.jrgames.lib.ScreenEffectType;
import cn.jerrymc.jrgames.lib.ScreenEffects;
import cn.jerrymc.jrgames.lib.world.WorldManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

public class SnowFightGameStateEventsListener implements Listener {
    private final Jrgames plugin;
    private final SnowFight game;

    public SnowFightGameStateEventsListener(Jrgames plugin,SnowFight game){
        this.plugin = plugin;
        this.game = game;
    }

    @EventHandler
    public void onGameStateChange(GameStateChangeEvent event){
        LOGGER.debug("snowFight状态变更为: "+event.getCurrentGameState());
        switch (game.getGameState()){
            case STARTING:
                LOGGER.logger.info("snow fight小游戏即将开始");
                break;
            case PLAYING:
                Location spawnLocation = new Location(Bukkit.getWorld(plugin.getConfig().getString("snowFight.map","snow_fight")),plugin.getConfig().getDouble("snowFight.spawnX",0D),plugin.getConfig().getDouble("snowFight.spawnY",0D),plugin.getConfig().getDouble("snowFight.spawnZ",0D));
                for(Player p:game.getPlayers()){
                    p.getInventory().clear();
                    ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);
                    p.getInventory().setItem(EquipmentSlot.HAND,shovel);
                    if(!p.teleport(spawnLocation)){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),String.format("tp %s %s %s %s",p.getName(),plugin.getConfig().getDouble("snowFight.spawnX",0D),plugin.getConfig().getDouble("snowFight.spawnY",0D),plugin.getConfig().getDouble("snowFight.spawnZ",0D)));
                    }
                }
                break;
            case STOPPING:
                // 卸载世界
                LOGGER.debug("snowFight游戏世界已卸载");
                Bukkit.unloadWorld(plugin.getConfig().getString("snowFight.map","snow_fight"),false);
                game.setGameState(GameState.RESTARTING);
                break;
            case ENDING:
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
                for(Player p:game.getPlayers()){
                    p.getInventory().clear();
                }
                break;
            case RESTARTING:
                // 重置世界
                WorldManager.restoreWorld(plugin.getConfig().getString("snowFight.map","snow_fight"));
                LOGGER.debug("snowFight游戏世界已重置");
                game.setGameState(GameState.WAITING);
                break;
        }
    }
}
