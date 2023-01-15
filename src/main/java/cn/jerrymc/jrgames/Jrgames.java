package cn.jerrymc.jrgames;

import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Jrgames extends JavaPlugin implements Listener {
    public Economy economy = null;
    public static Jrgames plugin = null;

    @Override
    public void onEnable() {
        // 初始化logger
        LOGGER.logger = this.getLogger();

        // 插件初始化
        plugin = this;
        this.saveDefaultConfig();

        // 初始化插件对接
        if(this.getServer().getPluginManager().getPlugin("Vault") == null||!initVault()){
            LOGGER.logger.info(ChatColor.RED + "无法与Vault插件挂钩!");
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
        RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if(economyProvider == null)return false;
        this.economy = economyProvider.getProvider();
        return true;
    }

    // 等待ia完成初始化
    @EventHandler
    private void onIAItemLoaded(ItemsAdderLoadDataEvent event){
        // 初始化自己的ia事件
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
