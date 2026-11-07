package io.github.achilleus1.noairship.mixin;

import io.github.achilleus1.noairship.NoAirshipVillages;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin {
    @Unique
    private static final ResourceKey<Structure> noAirshipVillages$airship = ResourceKey.create(
            Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath("integrated_villages", "airship_village"));

    @Inject(method = "tryGenerateStructure", at = @At("HEAD"), cancellable = true, remap = false)
    private void noAirshipVillages$preventGeneration(
            StructureSet.StructureSelectionEntry entry, StructureManager manager,
            RegistryAccess registries, RandomState random, StructureTemplateManager templates,
            long seed, ChunkAccess chunk, ChunkPos chunkPos, SectionPos sectionPos,
            CallbackInfoReturnable<Boolean> callback) {
        if (entry.structure().is(noAirshipVillages$airship) && NoAirshipVillages.DISABLE_AIRSHIPS.get()) {
            callback.setReturnValue(false);
        }
    }
}
