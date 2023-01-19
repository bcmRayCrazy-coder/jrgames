package cn.jerrymc.jrgames.games.snowfight.listeners;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.events.PlayerJoinGameEvent;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import cn.jerrymc.jrgames.lib.PlayerSender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

public class SnowFightPlayerEventsListener implements Listener {
    private final Jrgames plugin;
    private final SnowFight game;

    public SnowFightPlayerEventsListener(Jrgames plugin,SnowFight game){
        this.plugin = plugin;
        this.game = game;
    }

    @EventHandler
    public void playerJoin(PlayerJoinGameEvent event){
        // 玩家加入游戏
        LOGGER.debug("snow fight状态: " + game.getGameState());
        if(game.getGameState().equals(GameState.WAITING)){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"gamemode "+event.getPlayer().getName()+" survival");
            int playerNumber = game.getPlayers().size();
            if(playerNumber >= plugin.getConfig().getInt("snowFight.minPlayers")){
                // 切换到开始状态
                game.setGameState(GameState.STARTING);
            }else if(playerNumber > plugin.getConfig().getInt("snowFight.maxPlayers")){
                // 超出上限, 踢出玩家
                PlayerSender.sendToLobby(event.getPlayer());
                event.getPlayer().sendMessage(ChatColor.RED+"无法加入该游戏, 人数已满!");
            }
            // 向玩家广播
            for(Player p : Bukkit.getServer().getOnlinePlayers()){
                p.sendMessage(MessageFormat.format("{0}玩家 {1}{2}{3} 加入了游戏 {4}{5}",ChatColor.AQUA,ChatColor.RESET,event.getPlayer().getName(),ChatColor.AQUA,ChatColor.RESET,game.getDisplayName()));
            }
        }else if(game.getGameState().equals(GameState.PLAYING)){
            // 玩家切换为旁观模式观战
            event.getPlayer().sendMessage("该游戏已开始");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"gamemode "+event.getPlayer().getName()+" spectator");
            game.getPlayers().remove(event.getPlayer());
        }else{
            // 不允许玩家进入
            PlayerSender.sendToLobby(event.getPlayer());
            event.getPlayer().sendMessage(ChatColor.RED+"无法加入该游戏, 它正在停止或重启中!");
        }
        LOGGER.logger.info("snow fight处理事件后状态" + game.getGameState());
    }

    @EventHandler
    public void onDig(BlockBreakEvent event){
        if(!event.getPlayer().getWorld().equals(Bukkit.getWorld(plugin.getConfig().getString("snowFight.map","snow_fight")))){
            // 不是游戏内事件
            return;
        }

        if(!game.getPlayers().contains(event.getPlayer())){
            // 玩家不在游玩
            event.setCancelled(true);
            return;
        }

        if(game.getGameState().equals(GameState.PLAYING)) {
            Player breaker = event.getPlayer();
            if(!event.getBlock().getType().equals(Material.SNOW_BLOCK)){
                // 不是能破坏的方块
                event.setCancelled(true);
                return;
            }
            LOGGER.logger.info("掉落");
            event.setDropItems(false);
            // 给玩家4个雪球
            ItemStack snowballStack = new ItemStack(Material.SNOWBALL);
            snowballStack.setAmount(4);
            breaker.getInventory().addItem(snowballStack);
        }else{
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHurt(EntityDamageByEntityEvent event){
        if(event.getDamager().getType().equals(EntityType.SNOWBALL)){
            for(Player p : game.getPlayers()){
                p.sendMessage(MessageFormat.format("{0}{1}{2} 被雪撅了{3}(悲",ChatColor.YELLOW,event.getEntity().getName(),ChatColor.AQUA,ChatColor.RED));
            }
        }
    }
}
