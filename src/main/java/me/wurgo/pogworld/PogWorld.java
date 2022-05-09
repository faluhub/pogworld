package me.wurgo.pogworld;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PogWorld {
    public static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("pogworld").get();
    public static final String MOD_VERSION = String.valueOf(MOD_CONTAINER.getMetadata().getVersion());
    public static final String LOGGER_NAME = MOD_CONTAINER.getMetadata().getName();
    public static Logger LOGGER = LogManager.getLogger(LOGGER_NAME);

    public static boolean lowTower;
    public static Map<Long, boolean[]> eyesSeed = new ConcurrentHashMap<>();

    public static boolean[] calculateEyes(long seed) {
        if (eyesSeed.containsKey(seed)) {
            return eyesSeed.get(seed);
        }

        boolean[] bls = new boolean[12];

        Random random = new Random();
        random.setSeed(seed);

        for(int m = 0; m < bls.length; ++m) {
            bls[m] = random.nextFloat() > 0.7F;
        }

        eyesSeed.put(seed, bls);

        return bls;
    }

    public static boolean isTwelveEye(boolean[] eyes) {
        boolean i = true;

        for (boolean j : eyes) {
            if (!j) {
                i = false;
                break;
            }
        }

        return i;
    }

    public static void log(String message) {
        LOGGER.info(message);
    }
}
