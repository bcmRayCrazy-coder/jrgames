package cn.jerrymc.jrgames.commands.jgCommand;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.Storage;
import cn.jerrymc.jrgames.lib.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetupGame implements CommandExecutor, TabCompleter {
    private final Jrgames plugin;

    public SetupGame(Jrgames plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // args[0]是setup, 固定的, 传参在args[1]中
        if(args[1] == null|args[1].length() == 0) return false;
        if(Bukkit.getWorld(args[1]) == null){
            sender.sendMessage(ChatColor.RED + "指定游戏世界不存在!");
            return true;
        }

        // 创建世界备份
        if(plugin.storage.getConfig().getBoolean("backup."+args[1],false)){
            sender.sendMessage(ChatColor.RED + "指定世界已经备份过了!");
            return true;
        }
        plugin.storage.getConfig().set("backup."+args[1],true);
        plugin.storage.saveConfig();
        WorldManager.backupWorld(args[1],true);
        sender.sendMessage(ChatColor.GREEN + "世界 " + args[1] + " 初始化完成!");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> autoCompletes = new ArrayList<>();
        for(World world:Bukkit.getWorlds()){
            autoCompletes.add(world.getName());
        }
        return autoCompletes;
    }
}
