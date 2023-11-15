package net.craftsupport.extremeskills.skills;

import net.craftsupport.extremeskills.ExtremeSkills;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.HashMap;
import java.util.UUID;

public class bloodExplosion implements Listener {
    HashMap<UUID, Long> spellcooldown = new HashMap<UUID, Long>();
    private Boolean spellactive = false;
    public boolean getSpellActive() {
        return spellactive;
    }



    @EventHandler
    public void onSkillActivation(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.REDSTONE && event.getItemDrop().getItemStack().containsEnchantment(Enchantment.ARROW_DAMAGE) && event.getPlayer().isSneaking()) {
            Player player = event.getPlayer();

            final double offsetX = 2.0;
            final double offsetY = 0.0;
            final double offsetZ = 2.0;
        if (spellcooldown.get(player.getUniqueId()) == null) {
            spellactive = true;
            Bukkit.getScheduler().runTaskLater(ExtremeSkills.getPlugin(ExtremeSkills.class), () -> {
                Location itemLocation = event.getItemDrop().getLocation();

                spawnParticle(itemLocation.clone().add(offsetX, offsetY, offsetZ));
                spawnParticle(itemLocation.clone().add(offsetX, offsetY, -offsetZ));
                spawnParticle(itemLocation.clone().add(-offsetX, offsetY, offsetZ));
                spawnParticle(itemLocation.clone().add(-offsetX, offsetY, -offsetZ));
                player.setInvulnerable(true);
                event.getItemDrop().remove();
            }, 20L);
            spellcooldown.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
            Bukkit.getScheduler().runTaskLater(ExtremeSkills.getPlugin(ExtremeSkills.class), () -> {
                Location itemLocation = event.getItemDrop().getLocation();
                spawnTNT(itemLocation.clone().add(offsetX, offsetY, offsetZ));
                spawnTNT(itemLocation.clone().add(offsetX, offsetY, -offsetZ));
                spawnTNT(itemLocation.clone().add(-offsetX, offsetY, offsetZ));
                spawnTNT(itemLocation.clone().add(-offsetX, offsetY, -offsetZ));
                spawnTNT(itemLocation.clone().add(offsetX, offsetY, 0));
                spawnTNT(itemLocation.clone().add(-offsetX, offsetY, 0));
                spawnTNT(itemLocation.clone().add(0, offsetY, offsetZ));
                spawnTNT(itemLocation.clone().add(0, offsetY, -offsetZ));
                spellactive = false;
                player.setInvulnerable(false);
            }, 100L);
        }

else if (spellcooldown.get(player.getUniqueId()) <= System.currentTimeMillis()) {
    spellactive = true;
            Bukkit.getScheduler().runTaskLater(ExtremeSkills.getPlugin(ExtremeSkills.class), () -> {
                Location itemLocation = event.getItemDrop().getLocation();

                spawnParticle(itemLocation.clone().add(offsetX, offsetY, offsetZ));
                spawnParticle(itemLocation.clone().add(offsetX, offsetY, -offsetZ));
                spawnParticle(itemLocation.clone().add(-offsetX, offsetY, offsetZ));
                spawnParticle(itemLocation.clone().add(-offsetX, offsetY, -offsetZ));
                player.setInvulnerable(true);
                event.getItemDrop().remove();
            }, 20L);
            spellcooldown.put(player.getUniqueId(), System.currentTimeMillis() + 60000);
            Bukkit.getScheduler().runTaskLater(ExtremeSkills.getPlugin(ExtremeSkills.class), () -> {
                Location itemLocation = event.getItemDrop().getLocation();

                spawnTNT(itemLocation.clone().add(offsetX, offsetY, offsetZ));
                spawnTNT(itemLocation.clone().add(offsetX, offsetY, -offsetZ));
                spawnTNT(itemLocation.clone().add(-offsetX, offsetY, offsetZ));
                spawnTNT(itemLocation.clone().add(-offsetX, offsetY, -offsetZ));
                spawnTNT(itemLocation.clone().add(offsetX, offsetY, 0));
                spawnTNT(itemLocation.clone().add(-offsetX, offsetY, 0));
                spawnTNT(itemLocation.clone().add(0, offsetY, offsetZ));
                spawnTNT(itemLocation.clone().add(0, offsetY, -offsetZ));
                spellactive = false;
                player.setInvulnerable(false);
            }, 100L);
        } else {
            player.sendMessage("You need to wait " + String.valueOf((spellcooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000) + " Seconds before using " + ChatColor.DARK_RED + "Blood Bomb" + ChatColor.WHITE + " again");
        }
        }}


    private void spawnParticle(Location location) {
        Bukkit.getWorld(location.getWorld().getName()).spawnParticle(Particle.REDSTONE, location, 50, new Particle.DustOptions(org.bukkit.Color.RED, 25));
    }

    private void spawnTNT(Location location) {
        Bukkit.getWorld(location.getWorld().getName()).createExplosion(location, 4);
    }
}
