package com.zooi.immortalpets.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.zooi.immortalpets.ImmortalPetsMod;

@Mod(ImmortalPetsMod.MOD_ID)
public final class ImmortalPetsForge {
    public ImmortalPetsForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(ImmortalPetsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        ImmortalPetsMod.init();
    }
}
