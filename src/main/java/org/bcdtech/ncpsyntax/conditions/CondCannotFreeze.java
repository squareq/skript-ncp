package org.bcdtech.ncpsyntax.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import fr.neatmonster.nocheatplus.compat.Bridge1_17;
import org.bukkit.entity.Player;
@SuppressWarnings("unused")
@Name("Player Cannot Freeze")
@Description("Checks whether the given player has any type of material that prevents them from freezing.")
@Since("1.0")
public class CondCannotFreeze extends PropertyCondition<Player> {

    static{
        register(CondCannotFreeze.class, "immune to freezing", "players");
    }
    @Override
    public boolean check(Player p) {
        return Bridge1_17.isImmuneToFreezing(p);
    }

    @Override
    protected String getPropertyName() {
        return "immune to freezing";
    }
}
