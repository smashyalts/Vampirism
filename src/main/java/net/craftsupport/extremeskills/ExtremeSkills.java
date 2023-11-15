package net.craftsupport.extremeskills;

import net.craftsupport.extremeskills.events.Events;
import net.craftsupport.extremeskills.skills.BloodThirsty;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtremeSkills extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
getServer().getPluginManager().registerEvents(new BloodThirsty(), this);
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
