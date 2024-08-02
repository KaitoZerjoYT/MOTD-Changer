package net.kaito.proxy;

import dev.waterdog.waterdogpe.event.defaults.ProxyPingEvent;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.config.Configuration;
import net.kaito.proxy.manager.MOTDManager;
import net.kaito.proxy.task.MOTDTask;

import java.io.File;

public class Main extends Plugin {

    private MOTDManager motdManager;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        Configuration config = this.getConfig();
        this.motdManager = new MOTDManager(config.getStringList("motds"));
        this.getProxy().getScheduler().scheduleRepeating(new MOTDTask(motdManager), 1200);

        this.getProxy().getEventManager().subscribe(ProxyPingEvent.class, event -> {
            event.setMotd(motdManager.getCurrentMOTD());
        });

    }

    @Override
    public void onDisable() {
    }

    public void saveDefaultConfig() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            this.saveResource("config.yml", false);
        }
    }
}
