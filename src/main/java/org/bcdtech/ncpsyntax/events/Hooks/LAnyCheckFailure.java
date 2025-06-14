package org.bcdtech.ncpsyntax.events.Hooks;

import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.access.IViolationInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class LAnyCheckFailure extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final CheckType check;
    private final Player p;
    private final IViolationInfo VL;
    private boolean cancel;

    public LAnyCheckFailure(CheckType checkType, Player player, IViolationInfo iViolationInfo){
        super(false);
        this.check = checkType;
        this.p = player;
        this.VL = iViolationInfo;
        this.cancel = false;
    }

    public CheckType getCheck(){
        return check;
    }

    public Player getPlayer(){
        return p;
    }

    public IViolationInfo getViolationInfo(){
        return VL;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
