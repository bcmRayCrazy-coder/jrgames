package cn.jerrymc.jrgames.lib.file;

import cn.jerrymc.jrgames.lib.world.WorldManager;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

public class WorldZipper {

    private final String worldName;
    private boolean replace;

    /**
     * 压缩世界
     * @param worldName 世界名
     * @param replace 是否覆盖
     */
    public WorldZipper(String worldName, boolean replace) {
        this.worldName = worldName;
        this.replace = replace;
        execute();
    }

    private void execute() {
        if (!exists() || replace) {
            try {
                zipWorldFolder();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void zipWorldFolder() throws IOException {
        File worldFolder = getWorldFolder();
        File backupFile = getBackupFile();
        ZipFileUtil.zipDirectory(worldFolder, backupFile);
    }

    private File getWorldFolder() {
        File worldContainer = Bukkit.getWorldContainer();
        return new File(worldContainer, worldName);
    }

    private File getBackupFile() {
        File backupFolder = WorldManager.backupFolder;
        return new File(backupFolder, worldName + ".zip");
    }

    private boolean exists() {
        File worldFolder = getWorldFolder();
        return worldFolder.isDirectory();
    }
}