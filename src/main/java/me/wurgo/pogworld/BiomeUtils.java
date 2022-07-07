package me.wurgo.pogworld;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;

/*
copy of BiomeLayers
 */
public class BiomeUtils {
    protected static final int WARM_OCEAN_ID;
    protected static final int LUKEWARM_OCEAN_ID;
    protected static final int OCEAN_ID;
    protected static final int COLD_OCEAN_ID;
    protected static final int FROZEN_OCEAN_ID;
    protected static final int DEEP_WARM_OCEAN_ID;
    protected static final int DEEP_LUKEWARM_OCEAN_ID;
    protected static final int DEEP_OCEAN_ID;
    protected static final int DEEP_COLD_OCEAN_ID;
    protected static final int DEEP_FROZEN_OCEAN_ID;

    public static boolean isOcean(int id) {
        return id == WARM_OCEAN_ID || id == LUKEWARM_OCEAN_ID || id == OCEAN_ID || id == COLD_OCEAN_ID || id == FROZEN_OCEAN_ID || id == DEEP_WARM_OCEAN_ID || id == DEEP_LUKEWARM_OCEAN_ID || id == DEEP_OCEAN_ID || id == DEEP_COLD_OCEAN_ID || id == DEEP_FROZEN_OCEAN_ID;
    }

    static {
        WARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.WARM_OCEAN);
        LUKEWARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.LUKEWARM_OCEAN);
        OCEAN_ID = Registry.BIOME.getRawId(Biomes.OCEAN);
        COLD_OCEAN_ID = Registry.BIOME.getRawId(Biomes.COLD_OCEAN);
        FROZEN_OCEAN_ID = Registry.BIOME.getRawId(Biomes.FROZEN_OCEAN);
        DEEP_WARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_WARM_OCEAN);
        DEEP_LUKEWARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_LUKEWARM_OCEAN);
        DEEP_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_OCEAN);
        DEEP_COLD_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_COLD_OCEAN);
        DEEP_FROZEN_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_FROZEN_OCEAN);
    }
}
