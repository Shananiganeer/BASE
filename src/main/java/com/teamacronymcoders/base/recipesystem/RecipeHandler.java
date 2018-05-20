package com.teamacronymcoders.base.recipesystem;

import net.minecraft.util.ResourceLocation;

public class RecipeHandler {
    private int maxInputs;
    private int maxOutputs;
    private ResourceLocation handlerName;
    private Class<Recipe> getRecipeClass;

    public RecipeHandler(int maxInputs, int maxOutputs, ResourceLocation handlerName, Class<Recipe> getRecipeClass) {
        this.maxInputs = maxInputs;
        this.maxOutputs = maxOutputs;
        this.handlerName = handlerName;
        this.getRecipeClass = getRecipeClass;
    }

    public int getMaxInputs() {
        return maxInputs;
    }

    public int getMaxOutputs() {
        return maxOutputs;
    }

    public ResourceLocation getHandlerName() {
        return handlerName;
    }

    public Class<Recipe> getGetRecipeClass() {
        return getRecipeClass;
    }
}
