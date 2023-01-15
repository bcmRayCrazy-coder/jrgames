package cn.jerrymc.jrgames;

import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Jrgames extends JavaPlugin implements Listener {
    public Economy economy = null;
    public Storage storage = new Storage();

    public static Jrgames plugin = null;

    @Override
    public void onEnable() {
        // 初始化logger
        LOGGER.logger = this.getLogger();

        // 插件初始化
        plugin = this;
        this.saveDefaultConfig();

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

        LOGGER.logger.info(ChatColor.GREEN+"初始化完成!");
    }

    // 初始化vault
    private boolean initVault(){
        RegisteredServiceProvider < Economy > rsp = getServer().getServicesManager().getRegistration(Economy.class);
        assert rsp != null;
        economy = rsp.getProvider();
        return true;
    }

    // 等待ia完成初始化
    @EventHandler
    private void onIAItemLoaded(ItemsAdderLoadDataEvent event){
        // 初始化自己的ia事件
    }

    @Override
    public void onDisable() {
        // 取消所有计划事件
        Bukkit.getScheduler().cancelTasks(this);
        // TODO 提出玩家并重置所有游戏存档
    }
}
