package net.com.pvPSystem;

import net.com.pvPSystem.queue.QueueCommand;
import net.com.pvPSystem.queue.QueueManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private QueueManager qm;

    @Override
    public void onEnable() {
        //Queuek
        qm = new QueueManager();
        getCommand("queue").setExecutor(new QueueCommand(qm));
    }

    @Override
    public void onDisable() {

    }


    public QueueManager getQueueManager() {
        return qm;
    }
}