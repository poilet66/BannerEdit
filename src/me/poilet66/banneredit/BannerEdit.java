package me.poilet66.banneredit;

import me.poilet66.banneredit.cmd.BannerEditCMD;
import me.poilet66.banneredit.listener.DesignGUIClick;
import me.poilet66.banneredit.util.ColourGUI;
import me.poilet66.banneredit.util.DesignGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class BannerEdit extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("BannerEdit Enabled.");
        this.getCommand("editbanner").setExecutor(new BannerEditCMD()); //register command
        getServer().getPluginManager().registerEvents(new ColourGUI(), this); //register event listeners
        getServer().getPluginManager().registerEvents(new DesignGUIClick(), this);
    }

    @Override
    public void onDisable() {

    }

}
