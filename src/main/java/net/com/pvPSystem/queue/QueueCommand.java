package net.com.pvPSystem.queue;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QueueCommand implements CommandExecutor {

    private final QueueManager queueManager;

    public QueueCommand(QueueManager queueManager) {
        this.queueManager = queueManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /queue <gamemode>");
            player.sendMessage(ChatColor.GRAY + "Available: " + queueManager.getGamemodes());
            return true;
        }

        String gamemode = args[0].toUpperCase();

        if (!queueManager.isValidGamemode(gamemode)) {
            player.sendMessage(ChatColor.RED + "Invalid gamemode.");
            return true;
        }

        if (!queueManager.joinQueue(player, gamemode)) {
            player.sendMessage(ChatColor.RED + "You are already in that queue.");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Joined " + gamemode + " queue!");
        return true;
    }
}