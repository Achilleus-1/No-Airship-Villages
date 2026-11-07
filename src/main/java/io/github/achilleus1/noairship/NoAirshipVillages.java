package io.github.achilleus1.noairship;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod(NoAirshipVillages.MOD_ID)
public final class NoAirshipVillages {
    public static final String MOD_ID = "no_airship_villages";
    public static final ModConfigSpec.BooleanValue DISABLE_AIRSHIPS;
    public static final ModConfigSpec CONFIG;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        DISABLE_AIRSHIPS = builder
                .comment("Prevent new Integrated Villages Airship Villages from generating. Existing structures are kept.")
                .define("disableAirshipVillages", true);
        CONFIG = builder.build();
    }

    public NoAirshipVillages(ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, CONFIG, MOD_ID + "-common.toml");
    }
}
