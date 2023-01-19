package cn.jerrymc.jrgames.commands.jgCommand;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.games.Game;
import cn.jerrymc.jrgames.games.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GetState implements TabCompleter, CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0) return false;
        for(Game g: GameManager.games){
            if(g.getGameName().equals(args[0])){
                sender.sendMessage(g.getGameState().toString());
                return true;
            }
        }
        sender.sendMessage(ChatColor.RED+"游戏不存在");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> autoCompletes = new ArrayList<>();
        for(Game g: GameManager.games){
            autoCompletes.add(g.getGameName());
        }
        return autoCompletes;
    }
}
