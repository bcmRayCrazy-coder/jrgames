package cn.jerrymc.jrgames.commands;

import cn.jerrymc.jrgames.Jrgames;
import cn.jerrymc.jrgames.commands.jgCommand.GetState;
import cn.jerrymc.jrgames.commands.jgCommand.Join;
import cn.jerrymc.jrgames.commands.jgCommand.Menu;
import cn.jerrymc.jrgames.commands.jgCommand.SetupGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class JgMainCommand implements TabCompleter, CommandExecutor {
    private final SetupGame setupGame;
    private final GetState getState;
    private final Join join;
    private final Menu menu;

    public JgMainCommand(Jrgames plugin){
        this.setupGame = new SetupGame(plugin);
        this.getState = new GetState();
        this.join = new Join();
        this.menu = new Menu(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0||args[0].equals("info")){
            sender.sendMessage(ChatColor.AQUA+"Jrgames主插件 v1.0.0 by bcmray");
            return true;
        }
        switch (args[0]){
            case "setup":
                return setupGame.onCommand(sender,command,label,args);
            case "state":
                return getState.onCommand(sender,command,label,args);
            case "join":
                return join.onCommand(sender,command,label,args);
            case "menu":
                return menu.onCommand(sender,command,label,args);
            default:
                sender.sendMessage(ChatColor.RED+"未知的参数: "+args[0]);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> autoCompletes = new ArrayList<>();
        if(args.length == 0) {
            autoCompletes.add("setup");
            autoCompletes.add("info");
        }else {
            switch (args[0]) {
                case "setup":
                    autoCompletes = setupGame.onTabComplete(sender, command, alias, args);
                    break;
                case "state":
                    autoCompletes = getState.onTabComplete(sender,command,alias,args);
                    break;
                case "join":
                    autoCompletes = join.onTabComplete(sender,command,alias,args);
                    break;
                case "menu":
                    autoCompletes = onTabComplete(sender,command,alias,args);
                    break;
            }
        }
        return autoCompletes;
    }
}
