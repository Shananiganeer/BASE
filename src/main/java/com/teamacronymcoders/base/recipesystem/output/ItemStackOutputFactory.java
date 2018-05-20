package com.teamacronymcoders.base.recipesystem.output;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ItemStackOutputFactory implements IOutputFactory {
    @Override
    public Function<List<Ingredient>, ItemStack> parse(JsonContext context, JsonObject json) {
        String itemName = Optional.ofNullable(json.get("item"))
                .map(JsonElement::getAsString)
                .orElse("minecraft:air");

        Item item = Optional.ofNullable(ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .orElseThrow(() -> new JsonParseException("Unknown Item: " + itemName));

        int count = Optional.ofNullable(json.get("count"))
                .map(JsonElement::getAsInt)
                .orElse(1);

        if (item.getHasSubtypes()) {
            int data = Optional.ofNullable(json.get("data"))
                    .map(JsonElement::getAsInt)
                    .orElseThrow(() -> new JsonParseException("Missing data for Item: " + itemName));
            return list -> new ItemStack(item, count, data);
        } else {
            return list -> new ItemStack(item, count);
        }
    }
}
