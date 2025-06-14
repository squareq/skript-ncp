package org.bcdtech.ncpsyntax.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
@Name("Player Is Exempt")
@Description("Checks whether the given player is exempt from the given check.")
@Since("1.0")
@SuppressWarnings("unused")
public class CondIsExempt extends Condition {

    static{
        Skript.registerCondition(CondIsExempt.class, "%players% (is|are) exempt (from|of) %ncpchecktype%");
    }

    private Expression<Player> player;
    private Expression<CheckType> checkType;

    @Override
    public boolean check(Event event) {
        return player.check(event, player1 -> NCPExemptionManager.isExempted(player1.getUniqueId(), checkType.getSingle(event)));
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "is exempt";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        checkType = (Expression<CheckType>) expressions[1];
        return true;
    }
}
