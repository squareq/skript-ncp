package org.bcdtech.ncpsyntax;

import fr.neatmonster.nocheatplus.checks.ViolationHistory;

public class Util {

    public static String Beautify(ViolationHistory.ViolationLevel violation){
        final String[] parts = violation.check.split("\\.");
        if(parts.length > 0){
            final String check = parts[parts.length - 1].toLowerCase();
            final String parent = parts[parts.length - 2].toLowerCase();
            return parent + "." + check;
        } else {
            return parts[0].toLowerCase();
        }

    }
}
