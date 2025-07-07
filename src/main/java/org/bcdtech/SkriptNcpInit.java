package org.bcdtech;

import ch.njol.skript.util.Version;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPHookManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            pinstance.getServer().getConsoleSender().sendMessage(Component.text("Skript is no longer accepting registrations, and addons can no longer be loaded!").color(NamedTextColor.RED));
			pinstance.getServer().getConsoleSender().sendMessage(Component.text("...Ensure that no odd software is altering plugin order, such as Plugman.").color(NamedTextColor.RED));

		}
        if (Skript.getVersion().isSmallerThan(new Version(2,10, 99))){
            pluginEnabled = false;
			pinstance.getServer().getConsoleSender().sendMessage(Component.text("Skript must be running at a version no less than 2.10.").color(NamedTextColor.RED));
			pinstance.getServer().getConsoleSender().sendMessage(Component.text("...You are using a very old version of Skript, please update it.").color(NamedTextColor.RED));
		}
        if (!Bukkit.getPluginManager().isPluginEnabled("nocheatplus")){
            pluginEnabled = false;
			pinstance.getServer().getConsoleSender().sendMessage(Component.text("NoCheatPlus could not be detected.").color(NamedTextColor.RED));
			pinstance.getServer().getConsoleSender().sendMessage(Component.text("...Ensure that you have it downloaded, as well as that no odd software is altering plugin order, such as Plugman.").color(NamedTextColor.RED));

		}
        if(pluginEnabled){
            try {
                ainstance = Skript.registerAddon(pinstance);
                ainstance.setLanguageFileDirectory("lang");
                ainstance.loadClasses("org.bcdtech.ncpsyntax", "expressions", "types", "events.Hooks", "events", "effects", "conditions");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
				NCPHookManager.addHook(CheckType.ALL, new HkAnyCheckFailure());
			}
        } else {
			pinstance.getServer().getConsoleSender().sendMessage(Component.text("Disabling plugin.").color(NamedTextColor.RED));
			Bukkit.getPluginManager().disablePlugin(getPluginInstance());
        }

    }

    public static SkriptAddon getAddonInstance(){
        return ainstance;
    }
    public static SkriptNcpInit getPluginInstance() { return pinstance;}
}