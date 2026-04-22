package net.com.pvPSystem.queue;

import org.bukkit.entity.Player;

import java.util.*;

public class QueueManager {

    private final Map<String, List<Player>> queues = new HashMap<>();
    private final Set<String> gamemodes = new HashSet<>();

    public QueueManager() {
        registerGamemode("SWORD");
        registerGamemode("AXE");
        registerGamemode("SMP");
        registerGamemode("UHC");
    }

    public void registerGamemode(String gamemode) {
        gamemode = gamemode.toUpperCase();
        gamemodes.add(gamemode);
        queues.putIfAbsent(gamemode, new ArrayList<>());
    }

    public boolean isValidGamemode(String gamemode) {
        return gamemodes.contains(gamemode.toUpperCase());
    }

    public boolean isQueued(Player player, String gamemode) {
        gamemode = gamemode.toUpperCase();
        return queues.getOrDefault(gamemode, Collections.emptyList()).contains(player);
    }

    public boolean joinQueue(Player player, String gamemode) {
        gamemode = gamemode.toUpperCase();

        if (!isValidGamemode(gamemode)) return false;

        List<Player> queue = queues.get(gamemode);

        if (queue.contains(player)) return false;

        queue.add(player);
        return true;
    }

    public boolean leaveQueue(Player player, String gamemode) {
        gamemode = gamemode.toUpperCase();

        if (!isValidGamemode(gamemode)) return false;

        return queues.get(gamemode).remove(player);
    }

    public List<Player> getQueue(String gamemode) {
        gamemode = gamemode.toUpperCase();
        return Collections.unmodifiableList(
                queues.getOrDefault(gamemode, Collections.emptyList())
        );
    }

    public int getQueueSize(String gamemode) {
        return getQueue(gamemode).size();
    }

    public Set<String> getGamemodes() {
        return Collections.unmodifiableSet(gamemodes);
    }
}