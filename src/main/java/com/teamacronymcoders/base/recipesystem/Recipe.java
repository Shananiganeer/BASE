package com.teamacronymcoders.base.recipesystem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Function;

public class Recipe {
    private List<Ingredient> inputs;
    private List<Function<List<Ingredient>, ItemStack>> outputs;
    private List<Function<EntityPlayer, Boolean>> conditions;
}
