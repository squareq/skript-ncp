package org.bcdtech.ncpsyntax.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import fr.neatmonster.nocheatplus.checks.ViolationHistory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

@Name("Player Violation History")
@Description("Return the violations of a player for the session.")
@Since("1.0")
@SuppressWarnings("unused")
public class ExprViolationHistory extends SimpleExpression<ViolationHistory.ViolationLevel> {

    static{
        Skript.registerExpression(ExprViolationHistory.class, ViolationHistory.ViolationLevel.class, ExpressionType.PROPERTY, "[(all [[of] the]|the)] violation[s] [level(s)] (of|for) %players%");
    }

    Expression<Player> players;

    @Override
    protected ViolationHistory.ViolationLevel @Nullable[] get(Event event) {
        ArrayList<ViolationHistory.ViolationLevel> list = new ArrayList<>();
        for (Player p : players.getArray(event)){
            final ViolationHistory history = ViolationHistory.getHistory(p);
            final ViolationHistory.ViolationLevel[] violations = history.getViolationLevels();
            Collections.addAll(list, violations);
        }
        return list.toArray(new ViolationHistory.ViolationLevel[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends ViolationHistory.ViolationLevel> getReturnType() {
        return ViolationHistory.ViolationLevel.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "[(all [[of] the]|the)] violation[s] [level(s)] (of|for) " + players.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) expressions[0];
        return true;
    }
}
