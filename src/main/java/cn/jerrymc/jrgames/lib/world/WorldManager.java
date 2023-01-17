package cn.jerrymc.jrgames.lib.world;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.LOGGER;
import cn.jerrymc.jrgames.lib.file.FileUtil;
import cn.jerrymc.jrgames.lib.file.WorldZipper;
import cn.jerrymc.jrgames.lib.file.ZipFileUtil;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldManager {

    public static File backupFolder = new File(Jrgames.plugin.getDataFolder()+"/Backup");

    /**
     * 删除世界多余数据(还原为未初始化的世界)
     * @param world
     */
    public static void deleteWorldTrash(String world) {
        for (File f : new File[]{new File(Bukkit.getWorldContainer(), world + "/level.dat"),
                new File(Bukkit.getWorldContainer(), world + "/level.dat_mcr"),
                new File(Bukkit.getWorldContainer(), world + "/level.dat_old"),
                new File(Bukkit.getWorldContainer(), world + "/session.lock"),
                new File(Bukkit.getWorldContainer(), world + "/uid.dat")}) {
            if (f.exists()) {
                if (!f.delete()) {
                    LOGGER.logger.warning("无法删除世界 " + f.getPath());
                    LOGGER.logger.warning("这会出现大问题!!!");
                }
            }
        }
    }

    /**
     * 删除世界
     * @param name 世界名
     */
    public static void deleteWorld(String name) {
        Bukkit.getScheduler().runTaskAsynchronously(Jrgames.plugin, () -> {
            // 异步执行拉
            try {
                FileUtils.deleteDirectory(new File(Bukkit.getWorldContainer(), name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 重置世界
     * @param name 世界名称
     */
    public void restoreWorld(String name){
        // 备份的世界, 原世界
        File zipWorld = new File(backupFolder,name+".zip"),
             originWorld = new File(Bukkit.getWorldContainer(),name);
        if(zipWorld.exists()){
            // 删除
            FileUtil.delete(originWorld);
        }
        if(!zipWorld.exists()){
            // 创建世界备份
            new WorldZipper(name,true);
        }else{
            // 解压并还原世界
            try{
                ZipFileUtil.unzipFileIntoDirectory(zipWorld,new File(Bukkit.getWorldContainer(),name));
            }catch (IOException e){
                // 我操你妈的这什么傻逼服务器啊
                e.printStackTrace();
            }
        }

        deleteWorldTrash(name);

        // 异步生成世界
        Bukkit.getScheduler().runTask(Jrgames.plugin,()->{
            WorldCreator woc = new WorldCreator(name);
            woc.generateStructures(false);
            woc.generator(new VoidChunkGenerator());
            World w = Bukkit.createWorld(woc);
            if(w==null){
                throw new IllegalStateException("World is null");
            }
            w.setKeepSpawnInMemory(true);
            w.setAutoSave(false);
        });
    }

}
