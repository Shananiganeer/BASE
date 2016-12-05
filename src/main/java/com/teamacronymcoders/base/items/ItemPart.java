package com.teamacronymcoders.base.items;

import com.teamacronymcoders.base.Base;
import com.teamacronymcoders.base.api.materials.MaterialRegistry;
import com.teamacronymcoders.base.api.materials.MaterialType;
import com.teamacronymcoders.base.util.IMetaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Jared on 6/30/2016
 */
public class ItemPart extends Item implements IMetaItem, IHasOreDict {

    MaterialType.EnumPartType type;

    public ItemPart(MaterialType.EnumPartType type) {
        this.type = type;
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.addAll(MaterialRegistry.getMaterials().entrySet().stream().filter(ent -> ent.getValue().isTypeSet(this.type)).map(ent -> new ItemStack(itemIn, 1, ent.getKey())).collect(Collectors.toList()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type))
            return String.format("item.base.%s.%s", this.type.name().toLowerCase(), mat.getName().toLowerCase());

        return "item.base.null_part";
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type))
            return String.format("%s %s", mat.getLocalizedName(), this.type.getLocalizedName());

        return TextFormatting.RED + Base.languageHelper.error("null_part");
    }

    @Override
    public List<Integer> getMetaData() {
        return MaterialRegistry.getIDList();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        MaterialType mat = MaterialRegistry.getFromID(stack.getItemDamage());
        if (mat != null && mat.isTypeSet(type)) {
            return mat.isHasEffect();
        }
        return super.hasEffect(stack);
    }

    @Override
    public Map<ItemStack, String> getOreDictNames(Map<ItemStack, String> names) {
        for (Map.Entry<Integer, MaterialType> ent : MaterialRegistry.getMaterials().entrySet()) {
            MaterialType mat = ent.getValue();
            ItemStack itemStack = new ItemStack(this, 1, ent.getKey());
            String oreDictName = type.getName().toLowerCase() + mat.getName().replace(" ", "");
            names.put(itemStack, oreDictName);
        }
        return names;
    }
}
