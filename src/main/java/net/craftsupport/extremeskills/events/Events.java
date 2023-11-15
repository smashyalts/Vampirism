package net.craftsupport.extremeskills.events;

import net.craftsupport.extremeskills.ExtremeSkills;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class Events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(ExtremeSkills.getPlugin(ExtremeSkills.class), new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setExhaustion(10000);
                }
            }
        }, 100L);

    }

}
