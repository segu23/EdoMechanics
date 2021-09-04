package org.kayteam.edomechanics.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.kayteam.edomechanics.EdoMechanics;
import org.kayteam.edomechanics.events.ArrowProjectileMechanicEvent;
import org.kayteam.edomechanics.events.LightningMechanicEvent;
import org.kayteam.edomechanics.mechanics.mechanics.EggProjectile;

import java.util.Objects;

public class LightningMechanicListener implements Listener {

    private final EdoMechanics plugin;

    public LightningMechanicListener(EdoMechanics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLightningMechanic(LightningMechanicEvent event){
        new EggProjectile(plugin, event.getPlayer(), event.getItemUsed()).actions();
    }
}