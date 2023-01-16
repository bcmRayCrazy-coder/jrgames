package cn.jerrymc.jrgames.games.snowfight;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.Game;
import cn.jerrymc.jrgames.games.GameManager;
import cn.jerrymc.jrgames.games.snowfight.listeners.SnowFightPlayerEventsListener;
import cn.jerrymc.jrgames.games.snowfight.listeners.SnowFightTickEventListener;

public class SnowFight extends Game {
    public SnowFight(Jrgames plugin) {
        super(plugin);
        this.setGameName("snowFight");
        this.setDisplayName("打雪仗");

        // 注册
        GameManager.registerGame(this);
    }

    /**
     * 初始化事件处理器
     */
    public void initHandlers(){
        this.registerGameListener(new SnowFightPlayerEventsListener(this.getPlugin(),this));
        this.registerGameListener(new SnowFightTickEventListener(this.getPlugin(),this));
    }

    @Override
    public void init() {
        super.init();

        initHandlers();
    }

    @Override
    public void start(){
        LOGGER.logger.info("snow fight游戏开始!");
    }
}
