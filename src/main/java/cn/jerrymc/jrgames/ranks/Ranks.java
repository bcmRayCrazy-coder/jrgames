package cn.jerrymc.jrgames.ranks;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;

public class Ranks {
    private final RanksPAPI ranksPAPI;

    public Ranks(Jrgames plugin){
         ranksPAPI = new RanksPAPI(plugin);
    }

    public void init(){
        ranksPAPI.register();
    }
}
