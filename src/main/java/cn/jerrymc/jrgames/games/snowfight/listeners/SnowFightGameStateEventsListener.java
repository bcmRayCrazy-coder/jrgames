package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.events.GameStateChangeEvent;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import cn.jerrymc.jrgames.lib.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

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
                    p.teleport(spawnLocation);
                }
                break;
            case STOPPING:
                // 卸载世界
                LOGGER.debug("snowFight游戏世界已卸载");
                Bukkit.unloadWorld(plugin.getConfig().getString("snowFight.map","snow_fight"),false);
                game.setGameState(GameState.RESTARTING);
                break;
            case ENDING:
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
