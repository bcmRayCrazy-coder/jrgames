package cn.jerrymc.jrgames.lib;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.Bukkit;

public class ScreenEffects {
    /**
     * 显示字幕
     * @param type 种类
     * @param color 颜色(支持Hex)
     * @param fadeIn 渐入tick
     * @param stay 停留tick
     * @param fadeOut 渐出tick
     * @param freeze 是否冻结玩家操作
     * @param player 目标玩家名
     * @param message 显示文字
     */
    public static void startEffect(String type,String color,Number fadeIn,Number stay,Number fadeOut,Boolean freeze,String player,String message){
        String cmd = "screeneffect " + type +
                color +
                fadeIn +
                stay +
                fadeOut +
                (freeze ? "freeze" : "nofreeze") +
                player +
                message;
        Jrgames.plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }
}
