package org.bcdtech.ncpsyntax.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import fr.neatmonster.nocheatplus.utilities.collision.CollisionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
/*
CLASS IS UNUSED
CLASS IS UNUSED
CLASS IS UNUSED
CLASS IS UNUSED
CLASS IS UNUSED
 */
public class unused1 extends SimpleExpression<Double> {

    private Expression<Player> player;
    private Expression<Location> location;
    private Expression<Double> width;
    private Expression<Double> height;
    private Expression<Double> precision;

    /*
    static{
        Skript.registerExpression(ExprDirectionCheck.class, Double.class, ExpressionType.SIMPLE, "direction check for %player% with location %location% and height %number% and width %number% and precision %number%");
    }
     */

    @Override
    protected Double @Nullable [] get(Event event) {
        return new Double[]{CollisionUtil.directionCheck(player.getSingle(event), location.getSingle(event).getX(), location.getSingle(event).getY(), location.getSingle(event).getZ(), width.getSingle(event), height.getSingle(event), precision.getSingle(event))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        location = (Expression<Location>) expressions[1];
        width = (Expression<Double>) expressions[2];
        height = (Expression<Double>) expressions[3];
        precision = (Expression<Double>) expressions[4];
        return true;
    }
}
