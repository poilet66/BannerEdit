package me.poilet66.banneredit.listener;

import me.poilet66.banneredit.util.CustomInvHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class DesignGUIClick implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if(!(event.getInventory().getHolder() instanceof CustomInvHolder)) return; //if theyre not in the designs GUI

        event.setCancelled(true);

        final ItemStack clickedItem = event.getCurrentItem();

        if(clickedItem == null || clickedItem.getType() == Material.AIR || !(event.getInventory().contains(clickedItem))) return; //if theyve clicked an empty slot or an item not in the GUI

        final Player player = (Player) event.getWhoClicked();
        int itemAmount = player.getInventory().getItemInMainHand().getAmount(); //save amount of banners holding

        ItemStack giveItem = new ItemStack(clickedItem.getType(), itemAmount);
        giveItem.setItemMeta(clickedItem.getItemMeta());
        player.getInventory().setItemInMainHand(giveItem); //put the same amount of the new banners in their hand
        player.closeInventory(); //close gui
    }

}
