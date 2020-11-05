package me.poilet66.banneredit.util;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class DesignGUI implements Listener {

    private final Inventory page1;
    private final DyeColor colour;
    private final ItemStack item;

    public DesignGUI(DyeColor dye, ItemStack item) { //pass in dye selected and banner

        this.colour = dye;
        this.item = item;
        page1 = Bukkit.createInventory(new CustomInvHolder(), 54, "Patterns"); //open new 'patterns' inventory
        initItems(colour); //fill with pattern designs
    }

    public void initItems(DyeColor colour) {
        int count = 0;
        for(PatternType patternValue : PatternType.values()) { //loop through each pattern design
            if(count == 36) { count += 3; } //just for last line to format it to middle
            if(patternValue != PatternType.BASE) { //excluding base (is screwy if its added as a layer when its the base)
                ItemStack addItem = new ItemStack(item.getType(), 1); //get new instance of held banner
                BannerMeta addItemMeta = (BannerMeta) item.getItemMeta(); //get its meta
                addItemMeta.addPattern(new Pattern(colour, patternValue)); //add the pattern in the colour thats been decided to the banner
                addItem.setItemMeta(addItemMeta);

                page1.setItem(count, addItem); //add banner to GUI (just fills left to right right now but can be changed for a border etc)
                count++;
            }
        }
    }

    public void openInv(HumanEntity entity) {
        entity.openInventory(page1); //open gui function
    }

}
