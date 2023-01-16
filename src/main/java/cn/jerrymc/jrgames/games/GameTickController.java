package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class GameTickController implements Runnable {
    private final Game game;
    private final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

    private int taskId = -1;

    /**
     * 游戏滴答控制器
     * @param targetGame 目标控制游戏
     */
    public GameTickController(Game targetGame){
        game = targetGame;
    }

    public void startControlling(){
        taskId = scheduler.scheduleSyncRepeatingTask(Jrgames.plugin,this,0,1);
    }

    public void stopControlling(){
        // 没有滴答
        if(getTaskId() == -1) return;

        // 停止并清除
        scheduler.cancelTask(getTaskId());
        taskId = -1;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public void run() {
        // 是否需要滴答
        if(game.getGameState().equals(GameState.WAITING)||game.getGameState().equals(GameState.RESTARTING)||game.getGameState().equals(GameState.STOPPING)) return;
        // 滴答
        game.tick();
    }
}
