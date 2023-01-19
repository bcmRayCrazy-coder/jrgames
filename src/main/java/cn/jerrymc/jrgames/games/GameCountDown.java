package cn.jerrymc.jrgames.games;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.games.events.GameTickEvent;
import cn.jerrymc.jrgames.lib.PlayerSender;
import cn.jerrymc.jrgames.lib.ScreenEffectType;
import cn.jerrymc.jrgames.lib.ScreenEffects;
import org.bukkit.entity.Player;

public class GameCountDown {
    private final Jrgames plugin;
    private final Game game;

    /**
     * 游戏倒计时
     * @param plugin 插件
     * @param game 游戏
     */
    public GameCountDown(Jrgames plugin,Game game){
        this.plugin = plugin;
        this.game = game;
    }

    /**
     * 滴答
     * @param event 触发事件
     */
    public void onTick(GameTickEvent event){
        // 剩余tick
        int tickLess = plugin.getConfig().getInt("startCountDownTicks") - event.getTick();
        // 剩余秒
        int second = Math.floorDiv(tickLess,20);

        // 只能输出一次
        if(tickLess%20==0) {
            if (second == 40 || second == 30 || second == 20 || second <= 10) {
                for (Player p : game.getPlayers()) {
                    PlayerSender.sendCountdownMessage(p, second);
                }
            }

            if (second <= 5) {
                // 效果拉满!
                int fadeIn = second == 5 ? 5 : 0;
                int fadeOut = second == 1 ? 5 : 0;
                for (Player p : game.getPlayers()) {
                    ScreenEffects.startEffect(ScreenEffectType.fullscreen_transparent, "#9400D3", fadeIn, 20-fadeIn-fadeOut, fadeOut, false, p.getName(), String.valueOf(second));
                }
            }
        }

        if(second<=0){
            // 游戏开始
            for(Player p :game.getPlayers()){
                ScreenEffects.startEffect(ScreenEffectType.fullscreen_transparent,"#00CC33",5,10,5,false, p.getName(), "游戏开始!");
            }
            game.setGameState(GameState.PLAYING);
        }
    }
}
