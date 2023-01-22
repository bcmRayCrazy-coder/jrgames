package cn.jerrymc.jrgames.motd;

import cn.jerrymc.jrgames.Jrgames;
import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PingEventListener implements Listener {
    private final Jrgames plugin;
    public PingEventListener(Jrgames plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPing(PaperServerListPingEvent event){
        event.setNumPlayers(100);
        event.setMaxPlayers(114);

        FileConfiguration config = plugin.storage.getConfig();

        final TextComponent motd = Component.text(config.getString("motd.name","JRGames"))
                        .color(TextColor.color(40,92,242))
                        .decoration(TextDecoration.BOLD,true)
                .append(Component.text(" - ").color(TextColor.color(255,255,255)).decoration(TextDecoration.BOLD,false))
                .append(Component.text( config.getString("motd.description","JMC小游戏周目")).color(TextColor.color(0,255,51))
                .append(Component.text(" | ").color(TextColor.color(255,255,255)).decoration(TextDecoration.BOLD,true)))
                .append(Component.text(config.getString("motd.more1","期待您的到来")).color(TextColor.color(49,132,224)).decoration(TextDecoration.BOLD,false))
                .append(Component.text("\n"))
                .append(Component.text(config.getString("motd.qun","QQ群 799512425")+" | "+ config.getString("motd.more2","请从 mc.hjfunny.site 进入")).color(TextColor.color(112,128,114)));

        event.motd(motd);
    }
}
