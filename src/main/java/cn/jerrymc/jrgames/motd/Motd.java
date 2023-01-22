package cn.jerrymc.jrgames.motd;

import cn.jerrymc.jrgames.Jrgames;

public class Motd {
    private final Jrgames plugin;

    public Motd(Jrgames plugin){
        this.plugin = plugin;
    }
    public void init(){
        plugin.getServer().getPluginManager().registerEvents(new PingEventListener(plugin),plugin);
    }
}
