package cn.jerrymc.jrgames.commands.jgCommand;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Menu implements CommandExecutor, TabCompleter {
    private final Jrgames plugin;

    public Menu(Jrgames plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("只有玩家才能使用本命令");
            return true;
        }
        ((Player) sender).closeInventory();
        Bukkit.getScheduler().runTaskLater(plugin,()-> {
            if (args.length < 2) {
                ((Player) sender).performCommand("jgmenu");
            }
            switch (args[1]) {
                case "menu":
                    ((Player) sender).performCommand("jgmenu");
                    break;
                case "play":
                    ((Player) sender).performCommand("jgmenuplay");
                    break;
            }
        },5);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> autoCompletes = new ArrayList<>();
        autoCompletes.add("menu");
        autoCompletes.add("play");
        return autoCompletes;
    }
}
