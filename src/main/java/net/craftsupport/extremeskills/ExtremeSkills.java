package net.craftsupport.extremeskills;

import net.craftsupport.extremeskills.events.event;
import net.craftsupport.extremeskills.skills.bloodExplosion;
import net.craftsupport.extremeskills.skills.bloodThirsty;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtremeSkills extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
getServer().getPluginManager().registerEvents(new bloodThirsty(), this);
        getServer().getPluginManager().registerEvents(new bloodExplosion(), this);
        getServer().getPluginManager().registerEvents(new event(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
