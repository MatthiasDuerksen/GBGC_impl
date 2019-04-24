package control;

import model.Digram.DigramType;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private static Map<DigramType, Integer> digramTypeWeighed = new HashMap<>();

    private static Map<DigramType, Boolean> digranTypeUsed = new HashMap<>();

    public static final int NUMBER_OF_SEARCHWORKERS = 1;


    public static Integer getDigramTypeWeight(DigramType digramType) {
        initDigramTypeWeight();
        return digramTypeWeighed.get(digramType);
    }

    private static void initDigramTypeWeight() {
        digramTypeWeighed.put(DigramType.BASIC_NODE_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.ADJACENCY_DIGRAM, 1);
        digramTypeWeighed.put(DigramType.CIRCLE_DIGRAM, 1);
    }

    public static void initConfigs() {
        initDigramTypeWeight();
        initDigramTypeInUse();
    }

    private static void initDigramTypeInUse() {
        digranTypeUsed.put(DigramType.BASIC_NODE_DIGRAM, true);
        digranTypeUsed.put(DigramType.CIRCLE_DIGRAM, false);
        digranTypeUsed.put(DigramType.ADJACENCY_DIGRAM, false);
    }

    public static boolean isDigramTypeInUse(DigramType digramType) {
        if (digranTypeUsed.get(digramType) != null && digranTypeUsed.get(digramType)) {
            return true;
        }
        return false;
    }
}
