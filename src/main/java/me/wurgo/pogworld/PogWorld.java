package me.wurgo.pogworld;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PogWorld {
    public static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("pogworld").get();
    public static final String MOD_VERSION = String.valueOf(MOD_CONTAINER.getMetadata().getVersion());
    public static final String LOGGER_NAME = MOD_CONTAINER.getMetadata().getName();
    public static Logger LOGGER = LogManager.getLogger(LOGGER_NAME);

    public static boolean highTower;

    public static void log(String message) {
        LOGGER.info(message);
    }
}
