package net.craftsupport.extremeskills.skills;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

public class BloodThirsty implements Listener {
    HashMap<UUID, Long> spellcooldown = new HashMap<UUID, Long>();


    @EventHandler
    public void onEntityInteract (PlayerInteractEntityEvent event) {
        Particle.DustOptions data = new Particle.DustOptions(org.bukkit.Color.RED, 25);
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof LivingEntity clickedentity && event.getHand() == EquipmentSlot.HAND && player.isSneaking()) {
            if (spellcooldown.get(player.getUniqueId()) == null) {
                Location modifiedlocation = clickedentity.getLocation();
                modifiedlocation.setY(clickedentity.getLocation().getY() + 2);
                if ((clickedentity.getHealth() - 6) <= 0) {clickedentity.setHealth(6);} else {clickedentity.setHealth(clickedentity.getHealth() - 6);}
                if ((player.getHealth() + 6) >= player.getMaxHealth()) {player.setHealth(player.getMaxHealth());} else {player.setHealth(player.getHealth() + 6);}
                spellcooldown.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
                event.getPlayer().setFoodLevel(player.getFoodLevel() + 20);
                Bukkit.getWorld(player.getWorld().getName()).spawnParticle(Particle.REDSTONE, modifiedlocation, 100, data);
            }
            else if (spellcooldown.get(player.getUniqueId()) <= System.currentTimeMillis()) {
            Location modifiedlocation = clickedentity.getLocation();
            modifiedlocation.setY(clickedentity.getLocation().getY() + 2);
            if ((clickedentity.getHealth() - 4) <= 0) {clickedentity.setHealth(0);} else {clickedentity.setHealth(clickedentity.getHealth() - 4);}
            if ((player.getHealth() + 4) >= player.getMaxHealth()) {player.setHealth(player.getMaxHealth());} else {player.setHealth(player.getHealth() + 4);}
            spellcooldown.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
            event.getPlayer().setFoodLevel(player.getFoodLevel() + 20);
            Bukkit.getWorld(player.getWorld().getName()).spawnParticle(Particle.REDSTONE, modifiedlocation, 100, data);
        } else {
            player.sendMessage("You need to wait " + String.valueOf((spellcooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000) + " Seconds before using " + ChatColor.DARK_RED + "Blood Thirsty" + ChatColor.WHITE + " again");
        }
    }
}
}
