package cn.jerrymc.jrgames.games.snowfight;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.Game;
import cn.jerrymc.jrgames.games.GameManager;
import cn.jerrymc.jrgames.games.GameState;
import cn.jerrymc.jrgames.games.GameTickController;
import cn.jerrymc.jrgames.games.snowfight.listeners.SnowFightGameStateEventsListener;
import cn.jerrymc.jrgames.games.snowfight.listeners.SnowFightPlayerEventsListener;
import cn.jerrymc.jrgames.games.snowfight.listeners.SnowFightTickEventListener;

public class SnowFight extends Game {
    // 滴答控制器
    private final GameTickController gameTickController = new GameTickController(this);

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
        this.registerGameListener(new SnowFightGameStateEventsListener(this.getPlugin(),this));
        this.registerGameListener(new SnowFightTickEventListener(this.getPlugin(),this));
        this.registerGameListener(new SnowFightPlayerEventsListener(this.getPlugin(),this));
        LOGGER.debug("snowFight事件初始化完成");
    }

    @Override
    public void init() {
        initHandlers();
        setGameState(GameState.WAITING);
        gameTickController.startControlling();
        LOGGER.debug("snowFight初始化完成");
        // nm我强制送你个tick
        tick();
    }

    @Override
    public void stop(){
        super.stop();
        gameTickController.stopControlling();
    }

    public GameTickController getGameTickController() {
        return gameTickController;
    }
}
