package cn.jerrymc.jrgames;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Storage {
    private File file = null;
    private FileConfiguration config = null;

    public Storage(){
        file = new File(Jrgames.plugin.getDataFolder(),"storage.yml");

        if(!file.exists()){
            // 不存在存储文件, 初始化
            file.getParentFile().mkdirs();
            Jrgames.plugin.saveResource("storage.yml",false);
        }

        // 加载存储
        config = new YamlConfiguration();
        try{
            config.load(file);
        }catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            Jrgames.plugin.getServer().getPluginManager().disablePlugin(Jrgames.plugin);
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * 保存配置
     */
    public void saveConfig() {
        try {
            config.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
