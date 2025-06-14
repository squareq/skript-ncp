package org.bcdtech.ncpsyntax.types;
import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleLiteral;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.ViolationHistory;
import org.bcdtech.ncpsyntax.Util;
import org.bcdtech.ncpsyntax.events.Hooks.LAnyCheckFailure;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class NCPClasses {

    static
    {
        Classes.registerClass(new EnumClassInfo<>(CheckType.class, "ncpchecktype", "ncp check type", new SimpleLiteral<>(CheckType.ALL, true))
                .user("check ?types?")
                .name("NCP Check Type"));
        Classes.registerClass(new ClassInfo<>(ViolationHistory.ViolationLevel.class, "ncpviolationlevel")
                .parser(new Parser<>() {
                    @Override
                    public String toString(ViolationHistory.ViolationLevel violation, int flags) {
                        return Util.Beautify(violation);
                    }

                    @Override
                    public String toVariableNameString(ViolationHistory.ViolationLevel violation) {
                        return Util.Beautify(violation);
                    }

                    @Override
                    public boolean canParse(final ParseContext context) {
                        return false;
                    }
                }));
        EventValues.registerEventValue(LAnyCheckFailure.class, CheckType.class, LAnyCheckFailure::getCheck);
        EventValues.registerEventValue(LAnyCheckFailure.class, Player.class, LAnyCheckFailure::getPlayer);
    }

}
