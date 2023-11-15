package net.craftsupport.extremeskills.events;

import net.craftsupport.extremeskills.skills.bloodExplosion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class event implements Listener {
bloodExplosion spell = new bloodExplosion();

    @EventHandler
    public void onMovementEvent(PlayerMoveEvent event) {
        if (spell.getSpellActive()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You can't move while you're preparing a spell");
        }
    }
}
