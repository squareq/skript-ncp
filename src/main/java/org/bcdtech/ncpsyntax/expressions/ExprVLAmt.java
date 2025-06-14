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
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.ViolationHistory;
import org.bcdtech.ncpsyntax.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Name("Violation Level Amount")
@Description("Return the sum or maximum amount of a given violation level.")
@Since("1.0")
@SuppressWarnings("unused")
public class ExprVLAmt extends SimpleExpression<Long> {

    private boolean Sum;
    private Expression<Player> players;
    private Expression<CheckType> checkType;

    static{
        Skript.registerExpression(ExprVLAmt.class, Long.class, ExpressionType.PROPERTY, "(:sum|max[imum]) [amount] for check %ncpchecktype% of %players%");
    }

    @Override
    protected Long @Nullable [] get(Event event) {
        double n = 0.0;
        for(Player p : players.getArray(event)){
            for (ViolationHistory.ViolationLevel VL : ViolationHistory.getHistory(p).getViolationLevels()){
                String VLstring = Util.Beautify(VL).split("\\.")[1];

                String checktypesplit = Objects.requireNonNull(checkType.getSingle(event)).toString().contains("_") ? Objects.requireNonNull(checkType.getSingle(event)).toString().split("_")[1] : String.valueOf(checkType.getSingle(event));
                if(VLstring.equalsIgnoreCase(checktypesplit)){
                    if(Sum){
                        n = n + VL.sumVL;
                    } else{
                        n = n + VL.maxVL;
                    }
                }







            }
        }
        return new Long[]{Math.round(n)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return Sum ? "(:sum) [amount] for check " + Objects.requireNonNull(checkType.toString()) + " of" + Objects.requireNonNull(players.toString()) : "(max[imum]) [amount] for check " + Objects.requireNonNull(checkType.toString()) + " of" + Objects.requireNonNull(players.toString());
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        Sum = parseResult.hasTag("sum");
        checkType = (Expression<CheckType>) expressions[0];
        players = (Expression<Player>) expressions[1];
        return true;
    }
}
