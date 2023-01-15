package cn.jerrymc.jrgames.games;

/**
 * 游戏状态
 */
public enum GameState {
    /**
     * 等待中(人数不足)
     */
    WAITING,

    /**
     * 开始中(倒计时)
     */
    STARTING,

    /**
     * 游玩中
     */
    PLAYING,

    /**
     * 游戏结束(玩家未退场)
     */
    ENDING,

    /**
     * 停止中(结束该游戏所有listeners和schedulers
     */
    STOPPING,

    /**
     * 重启中(恢复存档)
     */
    RESTARTING
}
