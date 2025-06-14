package org.bcdtech;

import ch.njol.skript.util.Version;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPHookManager;
import org.bcdtech.ncpsyntax.events.Hooks.HkAnyCheckFailure;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import java.io.IOException;

@SuppressWarnings("unused")
public class SkriptNcpInit extends JavaPlugin {

    static SkriptNcpInit pinstance;
    static SkriptAddon ainstance;

    @Override
    public void onEnable() {
        super.onEnable();
        pinstance = this;
        boolean pluginEnabled = true;
        if (!Skript.isAcceptRegistrations()) {
            pluginEnabled = false;
            Bukkit.getConsoleSender().sendMessage("&4Skript is no longer accepting registrations, and addons can no longer be loaded!");
            Bukkit.getConsoleSender().sendMessage("&4...Ensure that no odd software is altering plugin order, such as Plugman.");
        }
        if (!Skript.getVersion().isSmallerThan(new Version(2,10, 99))){
            pluginEnabled = false;
            Bukkit.getConsoleSender().sendMessage("&4Skript must be running at a version no less than 2.10.");
            Bukkit.getConsoleSender().sendMessage("&4...You are using a very old version of Skript, please update it.");
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("nocheatplus")){
            pluginEnabled = false;
            Bukkit.getConsoleSender().sendMessage("&4NoCheatPlus could not be detected.");
            Bukkit.getConsoleSender().sendMessage("&4...Ensure that you have it downloaded, as well as that no odd software is altering plugin order, such as Plugman.");
        }
        if(pluginEnabled){
            try {
                ainstance = Skript.registerAddon(pinstance);
                ainstance.setLanguageFileDirectory("lang");
                ainstance.loadClasses("org.bcdtech.ncpsyntax", "expressions", "types", "events.Hooks", "events", "effects", "conditions");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            NCPHookManager.addHook(CheckType.ALL, new HkAnyCheckFailure());
        } else {
            Bukkit.getConsoleSender().sendMessage("&4Disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(getPluginInstance());
        }

    }

    public static SkriptAddon getAddonInstance(){
        return ainstance;
    }
    public static SkriptNcpInit getPluginInstance() { return pinstance;}
}