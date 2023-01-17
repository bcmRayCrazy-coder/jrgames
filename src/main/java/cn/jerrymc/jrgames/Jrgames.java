package cn.jerrymc.jrgames;

import cn.jerrymc.jrgames.commands.JstopallCommand;
import cn.jerrymc.jrgames.games.GameManager;
import cn.jerrymc.jrgames.games.snowfight.SnowFight;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Jrgames extends JavaPlugin implements Listener {
    public Economy economy = null;
    public Storage storage;

    public static Jrgames plugin = null;

    public GameManager gameManager;

    @Override
    public void onEnable() {
        // 插件初始化
        plugin = this;
        storage = new Storage();
        gameManager = new GameManager();
        this.saveDefaultConfig();

        // 初始化logger
        LOGGER.logger = this.getLogger();

        // 初始化插件对接
        if(!initVault()){
            LOGGER.logger.info(ChatColor.RED + "无法与Vault插件挂钩, 请检查是否安装了经济插件!");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(this.getServer().getPluginManager().getPlugin("ItemsAdder") == null){
            LOGGER.logger.info(ChatColor.RED +"无法与ItemsAdder插件挂钩!");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(this.getServer().getPluginManager().getPlugin("PlaceHolderAPI") == null){
            LOGGER.logger.info(ChatColor.RED +"无法与PlaceHolderAPI插件挂钩!");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(this.getServer().getPluginManager().getPlugin("ScreenEffects") == null){
            LOGGER.logger.info(ChatColor.RED +"无法与ScreenEffects插件挂钩!");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(this.getServer().getPluginManager().getPlugin("Multiverse-Core") == null){
            LOGGER.logger.info(ChatColor.RED +"无法与Multiverse-Core插件挂钩!");
            getServer().getPluginManager().disablePlugin(this);
        }

        initGames();
        initCommands();

        LOGGER.logger.info(ChatColor.GREEN+"初始化完成!");
    }

    // 初始化vault
    private boolean initVault(){
        RegisteredServiceProvider < Economy > rsp = getServer().getServicesManager().getRegistration(Economy.class);
        assert rsp != null;
        economy = rsp.getProvider();
        return true;
    }

    // 初始化游戏
    private void initGames(){
        new SnowFight(this);
        gameManager.initGames();
        LOGGER.logger.info(ChatColor.BLUE+"游戏初始化完成!");
    }

    // 初始化命令
    private void initCommands(){
        Objects.requireNonNull(getCommand("jstopall")).setExecutor(new JstopallCommand(this));
    }

    @Override
    public void onDisable() {
        // 取消所有计划事件
        Bukkit.getScheduler().cancelTasks(this);
        // 踢出玩家
        gameManager.forceStopAllGames();
        LOGGER.logger.info(ChatColor.BLUE+" 所有游戏已停止!");
        // 关闭存档
        Bukkit.unloadWorld(getConfig().getString("snowFight.map","snow_fight"),false);
    }
}
