package me.poilet66.banneredit.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CustomInvHolder implements InventoryHolder { //see ColourInvHolder for description on why this works

    @Override
    public Inventory getInventory() {
        return null;
    }
}
