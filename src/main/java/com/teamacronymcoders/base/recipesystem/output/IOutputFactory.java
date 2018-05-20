package com.teamacronymcoders.base.recipesystem.output;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface IOutputFactory {
    Function<List<Ingredient>, ItemStack> parse(JsonContext context, JsonObject json);
}
