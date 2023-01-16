package cn.jerrymc.jrgames.games.snowfight;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.games.Game;
import cn.jerrymc.jrgames.games.GameManager;
import cn.jerrymc.jrgames.games.snowfight.events.SnowFightPlayerEvents;

public class SnowFight extends Game {
    public SnowFight(Jrgames plugin) {
        super(plugin);
        this.setGameName("snowFight");
        this.setDisplayName("打雪仗");

        initHandlers();

        // 注册
        GameManager.registerGame(this);
    }

    /**
     * 初始化事件处理器
     */
    public void initHandlers(){
        this.registerGameListener(new SnowFightPlayerEvents(this.getPlugin()));
    }
}
