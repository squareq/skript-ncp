package org.bcdtech.ncpsyntax.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.UUID;

@Name("Exempt Player")
@Description("Exempt or unexempt a player from a NoCheatPlus check type.")
@Since("1.0")
@SuppressWarnings("unused")
public class EffExemptCheck extends Effect {

    static{
        Skript.registerEffect(EffExemptCheck.class, "make %player% [:un]exempt from %ncpchecktype%");
    }

    private Expression<Player> players;
    private Expression<CheckType> checktypes;
    private boolean exempt;

    @Override
    protected void execute(Event event) {
        if (players != null){
            if (checktypes != null){
                ArrayList<UUID> UUID = new ArrayList<>();
                Player[] plist = players.getArray(event);
                for(Player p : plist){
                    if (exempt) {
                        NCPExemptionManager.exemptPermanently(p, checktypes.getSingle(event));
                    } else {
                        NCPExemptionManager.unexempt(p, checktypes.getSingle(event));
                    }
                }
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make " + players.toString() + " " + (exempt ? "exempt" : "unexempt") + " from" + checktypes.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) expressions[0];
        checktypes = (Expression<CheckType>) expressions[1];
        exempt = !parseResult.hasTag("un"); //False - Unexempt and True - Exempt
        return true;
    }
}
