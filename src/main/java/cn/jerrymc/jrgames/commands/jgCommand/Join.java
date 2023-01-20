package cn.jerrymc.jrgames.commands.jgCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Join implements TabCompleter, CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("只有玩家才能使用本命令");
            return true;
        }
        if(args.length < 2) return false;
        if(args[1].equals("snow_fight")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"mv tp "+sender.getName()+" snow_fight");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "游戏不存在!");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> autoCompletes = new ArrayList<>();

        autoCompletes.add("snow_fight");

        return autoCompletes;
    }
}
