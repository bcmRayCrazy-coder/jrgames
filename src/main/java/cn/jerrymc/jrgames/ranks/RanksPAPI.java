package cn.jerrymc.jrgames.ranks;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.lib.JrgamesPapiExpansion;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.OfflinePlayer;

public class RanksPAPI extends JrgamesPapiExpansion {
    private final Jrgames plugin;

    public RanksPAPI(Jrgames plugin){
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "ranks";
    }

    @Override
    public String onRequest(OfflinePlayer player){
        StringBuilder sb = new StringBuilder();

        for(String rank:plugin.storage.getConfig().getStringList("ranks."+player.getName())){
            try {
                sb.append(new FontImageWrapper(rank).getString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
