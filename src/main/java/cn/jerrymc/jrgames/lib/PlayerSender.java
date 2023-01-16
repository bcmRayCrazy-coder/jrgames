package cn.jerrymc.jrgames.lib;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerSender {
    /**
     * 将玩家传送到大厅
     * @param player 玩家
     */
    public static void sendToLobby(Player player){
        setPlayerWorld(player,Jrgames.plugin.getConfig().getString("lobby","world"));
    }

    /**
     * 设置玩家所在世界
     * @param player 玩家
     * @param worldName 目标世界名称
     */
    public static void setPlayerWorld(Player player,String worldName){
        player.getLocation().setWorld(Bukkit.getWorld(worldName));
    }
}
