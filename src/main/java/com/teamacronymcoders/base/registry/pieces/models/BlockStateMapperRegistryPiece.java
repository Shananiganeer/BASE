package com.teamacronymcoders.base.registry.pieces.models;

import com.teamacronymcoders.base.registry.Registry;
import com.teamacronymcoders.base.registry.pieces.RegistryPiece;
import com.teamacronymcoders.base.registry.pieces.RegistryPieceBase;
import com.teamacronymcoders.base.registry.pieces.RegistrySide;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

@RegistryPiece(RegistrySide.CLIENT)
public class BlockStateMapperRegistryPiece extends RegistryPieceBase<Block> {
    @Override
    public boolean acceptsRegistry(Registry registry) {
        return "BLOCK".equalsIgnoreCase(registry.getName());
    }

    @Override
    public boolean acceptsEntry(ResourceLocation name, Object entry) {
        return entry instanceof Block;
    }

    @Override
    public void preInit(ResourceLocation name, Block entry) {

    }
}
