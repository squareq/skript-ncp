package org.bcdtech.ncpsyntax.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bcdtech.ncpsyntax.events.Hooks.LAnyCheckFailure;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
@Name("Any check failure")
@Description("Called when any check is failed by a player.")
@Since("1.0")
@SuppressWarnings("unused")
//To-do: Add event-checktype
public class EvtAnyCheckFailure extends SkriptEvent {

    static{
        Skript.registerEvent("any check failure", EvtAnyCheckFailure.class, LAnyCheckFailure.class, "[any] check failure");
    }


    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "any check failure";
    }
}
