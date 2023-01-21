package cn.jerrymc.jrgames.motd;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PingEventListener implements Listener {
    @EventHandler
    public void onPing(PaperServerListPingEvent event){
        event.setNumPlayers(100);
        event.setMaxPlayers(114);

        final TextComponent motd = Component.text("JRGames")
                        .color(TextColor.color(40,92,242))
                        .decoration(TextDecoration.BOLD,true)
                .append(Component.text(" - ").color(TextColor.color(255,255,255)).decoration(TextDecoration.BOLD,false))
                .append(Component.text("JMC小游戏周目").color(TextColor.color(0,255,51))
                .append(Component.text(" | ").color(TextColor.color(255,255,255)).decoration(TextDecoration.BOLD,true)))
                .append(Component.text("期待您的到来").color(TextColor.color(49,132,224)).decoration(TextDecoration.BOLD,false))
                .append(Component.text("\n"))
                .append(Component.text("QQ群 799512425 | 请从 mc.hjfunny.site 进入").color(TextColor.color(112,128,114)));

        event.motd(motd);
    }
}
