package cn.jerrymc.jrgames.lib;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.OfflinePlayer;

/**
 * 用于Jrgames的PAPI扩展
 */
public class JrgamesPapiExpansion {
    /**
     * 获取papi解析时的名称
     * @return 名称
     */
    public String getName(){
        return "";
    }

    /**
     * papi解析
     * @param player 目标玩家
     * @return 输出
     */
    public String onRequest(OfflinePlayer player){
        return "";
    }

    /**
     * 注册
     */
    public void register(){
        Jrgames.papiExpansion.registerJrgamesPapiExpansion(getName(),this);
    }
}
