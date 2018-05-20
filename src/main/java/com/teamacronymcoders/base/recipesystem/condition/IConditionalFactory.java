package com.teamacronymcoders.base.recipesystem.condition;

import com.google.gson.JsonObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.Function;

@FunctionalInterface
public interface IConditionalFactory {
    Function<EntityPlayer, Boolean> parse(JsonContext context, JsonObject json);
}
