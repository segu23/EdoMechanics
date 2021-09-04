package org.kayteam.edomechanics.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.kayteam.edomechanics.EdoMechanics;
import org.kayteam.edomechanics.events.ArrowProjectileMechanicEvent;
import org.kayteam.edomechanics.mechanics.mechanics.ArrowProjectile;
import org.kayteam.edomechanics.mechanics.mechanics.EggProjectile;

public class ArrowProjectileMechanicListener implements Listener {

    private final EdoMechanics plugin;

    public ArrowProjectileMechanicListener(EdoMechanics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArrowProyectileMechanic(ArrowProjectileMechanicEvent event){
        new ArrowProjectile(plugin, event.getPlayer(), event.getItemUsed()).actions();
    }
}