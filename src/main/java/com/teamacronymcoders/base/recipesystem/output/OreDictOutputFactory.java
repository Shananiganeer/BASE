package com.teamacronymcoders.base.recipesystem.output;

import com.google.gson.JsonObject;
import com.teamacronymcoders.base.util.OreDictUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.List;
import java.util.function.Function;

public class OreDictOutputFactory implements IOutputFactory {
    @Override
    public Function<List<Ingredient>, ItemStack> parse(JsonContext context, JsonObject json) {
        return list -> OreDictUtils.getPreferredItemStack(JsonUtils.getString(json, "ore"));
    }
}
