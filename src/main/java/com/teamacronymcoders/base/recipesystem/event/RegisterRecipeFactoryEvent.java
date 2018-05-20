package com.teamacronymcoders.base.recipesystem.event;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.common.eventhandler.GenericEvent;

import java.util.Map;

public class RegisterRecipeFactoryEvent<T> extends GenericEvent<T> {
    private Map<String, T> factories;

    public RegisterRecipeFactoryEvent(Class<T> tClass) {
        super(tClass);
    }

    public void registerFactory(String name, T factory) {
        this.factories.put(name, factory);
    }

    public Map<String, T> getFactories() {
        return ImmutableMap.copyOf(factories);
    }
}
