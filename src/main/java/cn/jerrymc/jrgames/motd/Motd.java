package cn.jerrymc.jrgames.motd;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.games.listeners.PlayerEventsListener;

public class Motd {
    public static void init(Jrgames plugin){
        plugin.getServer().getPluginManager().registerEvents(new PingEventListener(),plugin);
    }
}
