package org.kayteam.edomechanics.inventories.editor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.kayteam.edomechanics.EdoMechanics;
import org.kayteam.edomechanics.inventories.EdoMechanicsInventory;
import org.kayteam.edomechanics.mechanics.MechanicType;
import org.kayteam.kayteamapi.inventory.InventoryBuilder;
import org.kayteam.kayteamapi.yaml.Yaml;

import java.util.Arrays;
import java.util.List;

public class MechanicsInventory extends InventoryBuilder {

    public MechanicsInventory(EdoMechanics plugin, Player player, int itemSlot, int page) {
        super(plugin.getInventories().getString("mechanics.inventoryTitle"), 6);
        Yaml inventories = plugin.getInventories();
        // Fill
        fillItem(() -> inventories.getItemStack("mechanics.panel"), new int[] {1, 6});
        // Back
        addItem(0, () -> inventories.getItemStack("mechanics.back"));
        addLeftAction(0, (player1, slot) -> plugin.getInventoryManager().openInventory(player, new ItemEditorInventory(plugin, player, itemSlot)));
        // Close
        addItem(8, () -> inventories.getItemStack("mechanics.close"));
        addLeftAction(8, (player1, slot) -> player.closeInventory());
        // Preview
        addItem(4, () -> player.getInventory().getItem(itemSlot));
        // Mechanics
        List<MechanicType> mechanics = plugin.getMechanicManager().getItemMechanics(player.getInventory().getItem(itemSlot));
        for (int i = 9; i < 45; i++) {
            int index = ((page * (4 * 9)) - (4 * 9)) + (i - 9);
            if (index < mechanics.size()) {
                addItem(i, () -> Yaml.replace(inventories.getItemStack("mechanics.mechanic"), new String[][] {
                        {"%mechanic_name%", mechanics.get(index).toString()}
                }));
                addLeftAction(i, (player1, slot) -> {
                    ItemStack resultItemStack = plugin.getMechanicManager().removeItemMechanic(player.getInventory().getItem(itemSlot), mechanics.get(index));
                    plugin.getInventoryManager().openInventory(player, new MechanicsInventory(plugin, player, itemSlot, page));
                });
            }
        }
        // Add Mechanics
        addItem(48, () -> inventories.getItemStack("mechanics.addMechanic"));
        addLeftAction(48, (player1, slot) -> plugin.getInventoryManager().openInventory(player, new MechanicsSelectorInventory(plugin, player, itemSlot, 1)));
        // Add Potion Effect
        addItem(50, () -> inventories.getItemStack("mechanics.addPotionEffect"));
        // Previous Page
        if (page > 1) {
            addItem(45, () -> inventories.getItemStack("mechanics.previousPage"));
            addLeftAction(45, (player1, slot) -> plugin.getInventoryManager().openInventory(player, new MechanicsSelectorInventory(plugin, player, itemSlot, page - 1)));
        }
        // Next Page
        if (mechanics.size() > (page * (4 * 9))) {
            addItem(53, () -> inventories.getItemStack("mechanics.nextPage"));
            addLeftAction(53, (player1, slot) -> plugin.getInventoryManager().openInventory(player, new MechanicsSelectorInventory(plugin, player, itemSlot, page + 1)));
        }
    }

}