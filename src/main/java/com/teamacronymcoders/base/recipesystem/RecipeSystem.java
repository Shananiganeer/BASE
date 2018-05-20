package com.teamacronymcoders.base.recipesystem;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.recipesystem.condition.IConditionalFactory;
import com.teamacronymcoders.base.recipesystem.event.RegisterRecipeFactoryEvent;
import com.teamacronymcoders.base.recipesystem.output.IOutputFactory;
import net.minecraftforge.common.MinecraftForge;

import java.util.Map;

public class RecipeSystem {
    private static RecipeSystem instance;

    private final Map<String, IOutputFactory> outputFactories;
    private final Map<String, IConditionalFactory> conditionalFactories;

    private RecipeSystem() {
        this.outputFactories = Maps.newHashMap();
        this.conditionalFactories = Maps.newHashMap();
    }

    public static void init() {
        RegisterRecipeFactoryEvent<IOutputFactory> outputFactoryEvent = new RegisterRecipeFactoryEvent<>(IOutputFactory.class);
        MinecraftForge.EVENT_BUS.post(outputFactoryEvent);
        getInstance().outputFactories.putAll(outputFactoryEvent.getFactories());

        RegisterRecipeFactoryEvent<IConditionalFactory> conditionalFactoryEvent = new RegisterRecipeFactoryEvent<>(IConditionalFactory.class);
        MinecraftForge.EVENT_BUS.post(conditionalFactoryEvent);
        getInstance().conditionalFactories.putAll(conditionalFactoryEvent.getFactories());


    }

    private static RecipeSystem getInstance() {
        if (instance == null) {
            instance = new RecipeSystem();
        }
        return instance;
    }
}
