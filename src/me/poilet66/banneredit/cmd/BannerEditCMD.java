package me.poilet66.banneredit.cmd;

import me.poilet66.banneredit.util.ColourGUI;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BannerEditCMD implements CommandExecutor {

    final private List<Material> banners = new ArrayList<Material>(Arrays.asList(Material.BLUE_BANNER, Material.BLACK_BANNER,
            Material.BROWN_BANNER, Material.BROWN_BANNER, Material.CYAN_BANNER, Material.GRAY_BANNER, Material.GREEN_BANNER,
            Material.LIGHT_BLUE_BANNER, Material.LIGHT_GRAY_BANNER, Material.LIGHT_GRAY_BANNER, Material.LIME_BANNER, Material.MAGENTA_BANNER,
            Material.ORANGE_BANNER, Material.PINK_BANNER, Material.PURPLE_BANNER, Material.RED_BANNER, Material.WHITE_BANNER, Material.YELLOW_BANNER));

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("editbanner")) {

                if(player.hasPermission("banneredit.edit")) {

                    if(banners.contains(player.getInventory().getItemInMainHand().getType())) { //if holding banner

                        BannerMeta bMeta = (BannerMeta) player.getInventory().getItemInMainHand().getItemMeta();

                        ColourGUI colourGUI = new ColourGUI();
                        colourGUI.openGUI(player);

                    }
                    else {
                        player.sendMessage("§cYou aren't holding a banner.");
                    }

                }
                return true;
            }

        }
        return false;
    }
}
