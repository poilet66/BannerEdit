package me.poilet66.banneredit.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ColourInvHolder implements InventoryHolder { //as spigot does not allow comparison of inventories (if inv1 == inv2) the workaround is casting whoever is in the gui to a class custom to that GUI, this means whenever some is an instance of this class, they must be in the GUI

    @Override
    public Inventory getInventory() { //this is required to check if the inventory the player's in is a GUI, idk why but it makes it play nice
        return null;
    }

}
