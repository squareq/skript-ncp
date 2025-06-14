package org.bcdtech.ncpsyntax.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import fr.neatmonster.nocheatplus.checks.CheckType;
import org.bcdtech.ncpsyntax.events.Hooks.LAnyCheckFailure;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
@Name("For check failure")
@Description("Called when the check is failed by a player.")
@Since("1.0")
@SuppressWarnings("unused")
public class EvtForCheckFailure extends SkriptEvent {

   static{
       Skript.registerEvent("for check failure", EvtForCheckFailure.class, LAnyCheckFailure.class, "check failure (for|of) %ncpchecktype%");
   }

   private Literal<CheckType> checkTypeLiteral;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        checkTypeLiteral = (Literal<CheckType>) args[0];
        return true;
    }

    @Override
    public boolean check(Event event) {
        LAnyCheckFailure e = (LAnyCheckFailure) event;
        return (e.getCheck() == checkTypeLiteral.getSingle());
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "check failure for " + checkTypeLiteral.toString();
    }
}
