package me.poilet66.banneredit.util;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColourGUI implements Listener {

    private final Inventory inv;


    public ColourGUI() {

        inv = Bukkit.createInventory(new ColourInvHolder(), 18, "Colours"); //create new colour inventory (gui)
        initItems(); //add items
    }

    public void initItems() { //format and add items into GUI
        inv.setItem(0, createGUIItem(Material.CYAN_CONCRETE, "§3§lCyan", "§3Make your banner design cyan"));
        inv.setItem(1, createGUIItem(Material.BLACK_CONCRETE, "§8§lBlack", "§8Make your banner design black"));
        inv.setItem(2, createGUIItem(Material.BLUE_CONCRETE, "§1§lBlue", "§1Make your banner design blue"));
        inv.setItem(3, createGUIItem(Material.BROWN_CONCRETE, "§6§lBrown", "§6Make your banner design brown"));
        inv.setItem(4, createGUIItem(Material.GRAY_CONCRETE, "§8§lGray", "§7Make your banner design gray"));
        inv.setItem(5, createGUIItem(Material.GREEN_CONCRETE, "§2§lGreen", "§2Make your banner design green"));
        inv.setItem(6, createGUIItem(Material.LIGHT_BLUE_CONCRETE, "§b§lLight Blue", "§bMake your banner design light blue"));
        inv.setItem(7, createGUIItem(Material.LIGHT_GRAY_CONCRETE, "§7§lLight Gray", "§7Make your banner design light gray"));
        inv.setItem(8, createGUIItem(Material.LIME_CONCRETE, "§a§lLime", "§aMake your banner design lime"));
        inv.setItem(10, createGUIItem(Material.MAGENTA_CONCRETE, "§d§lMagenta", "§dMake your banner design magenta"));
        inv.setItem(11, createGUIItem(Material.ORANGE_CONCRETE, "§6§lOrange", "§6Make your banner design orange"));
        inv.setItem(12, createGUIItem(Material.PINK_CONCRETE, "§d§lPink", "§dMake your banner design pink"));
        inv.setItem(13, createGUIItem(Material.PURPLE_CONCRETE, "§5§lPurple", "§5Make your banner design purple"));
        inv.setItem(14, createGUIItem(Material.RED_CONCRETE, "§4§lRed", "§4Make your banner design red"));
        inv.setItem(15, createGUIItem(Material.WHITE_CONCRETE, "§l§f§lWhite", "§fMake your banner design white"));
        inv.setItem(16, createGUIItem(Material.YELLOW_CONCRETE, "§e§lYellow", "§eMake your banner design yellow"));

    }

    protected ItemStack createGUIItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);

        return item;
    }

    public void openGUI(final HumanEntity entity) {
        entity.openInventory(inv);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) { //in future perhaps split this into its own class or move design listener into design class

        if(!(event.getInventory().getHolder() instanceof ColourInvHolder)) return; //if not in colour gui

        event.setCancelled(true);

        final ItemStack clickedItem = event.getCurrentItem();

        if(clickedItem == null || clickedItem.getType() == Material.AIR || !(inv.contains(clickedItem))) return; //if the item clicked wasn't part of the gui

        final Player player = (Player) event.getWhoClicked();

        String itemName = clickedItem.getItemMeta().getDisplayName();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        player.sendMessage("You chose " + clickedItem.getItemMeta().getDisplayName());

        DesignGUI designGUI = null;

        switch(itemName) { //update later to save the DyeColor as an NBT tag of the wool (that way you can just parse it into a dyecolor rather than switch case) also i think this is slightly buggy regarding firing after theyve left the GUI

            case("§3§lCyan"):
                designGUI = new DesignGUI(DyeColor.CYAN, itemInHand);
                break;

            case("§8§lBlack"):
                designGUI = new DesignGUI(DyeColor.BLACK, itemInHand);
                break;

            case("§1§lBlue"):
                designGUI = new DesignGUI(DyeColor.BLUE, itemInHand);
                break;

            case("§6§lBrown"):
                designGUI = new DesignGUI(DyeColor.BROWN, itemInHand);
                break;

            case("§8§lGray"):
                designGUI = new DesignGUI(DyeColor.GRAY, itemInHand);
                break;

            case("§2§lGreen"):
                designGUI = new DesignGUI(DyeColor.GREEN, itemInHand);
                break;

            case("§b§lLight Blue"):
                designGUI = new DesignGUI(DyeColor.LIGHT_BLUE, itemInHand);
                break;

            case("§a§lLime"):
                designGUI = new DesignGUI(DyeColor.LIME, itemInHand);
                break;

            case("§d§lMagenta"):
                designGUI = new DesignGUI(DyeColor.MAGENTA, itemInHand);
                break;

            case("§6§lOrange"):
                designGUI = new DesignGUI(DyeColor.ORANGE, itemInHand);
                break;

            case("§d§lPink"):
                designGUI = new DesignGUI(DyeColor.PINK, itemInHand);
                break;

            case("§5§lPurple"):
                designGUI = new DesignGUI(DyeColor.PURPLE, itemInHand);
                break;

            case("§4§lRed"):
                designGUI = new DesignGUI(DyeColor.RED, itemInHand);
                break;

            case("§l§f§lWhite"):
                designGUI = new DesignGUI(DyeColor.WHITE, itemInHand);
                break;

            case("§7§lLight Gray"):
                designGUI = new DesignGUI(DyeColor.LIGHT_GRAY, itemInHand);
                break;

            case("§e§lYellow"):
                designGUI = new DesignGUI(DyeColor.YELLOW, itemInHand);
                break;

            default:
                player.sendMessage("§cError - not added yet");
                break;
        }

        player.closeInventory();
        designGUI.openInv(player);
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }

}
