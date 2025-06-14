package org.bcdtech.ncpsyntax.events.Hooks;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.access.IViolationInfo;
import fr.neatmonster.nocheatplus.hooks.NCPHook;
import org.bukkit.entity.Player;

public class HkAnyCheckFailure implements NCPHook {
    @Override
    public String getHookName() {
        return "SkriptNcpAllViolations";
    }

    @Override
    public String getHookVersion() {
        return "1.0";
    }

    @Override
    public boolean onCheckFailure(CheckType checkType, Player player, IViolationInfo iViolationInfo) {
        LAnyCheckFailure event = new LAnyCheckFailure(checkType, player, iViolationInfo);
        return !(event.callEvent());
    }
}
