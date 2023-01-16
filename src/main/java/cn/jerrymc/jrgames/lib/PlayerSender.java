package cn.jerrymc.jrgames.lib;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
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
     * 传送玩家到世界
     * @param player 玩家
     * @param worldName 目标世界名称
     */
    public static void setPlayerWorld(Player player,String worldName){
        Jrgames.plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(),"mv tp "+player+" "+worldName);
    }
}
