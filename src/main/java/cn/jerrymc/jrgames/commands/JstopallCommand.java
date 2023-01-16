package cn.jerrymc.jrgames.commands;

import cn.jerrymc.jrgames.Jrgames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class JstopallCommand implements CommandExecutor {
    private final Jrgames plugin;

    public JstopallCommand(Jrgames plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        plugin.gameManager.forceStopAllGames();
        sender.sendMessage("OK!");
        return true;
    }
}
