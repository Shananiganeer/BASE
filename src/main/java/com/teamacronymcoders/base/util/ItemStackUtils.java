package com.teamacronymcoders.base.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ItemStackUtils {
    private ItemStackUtils() {
    }

    public static boolean isSmeltable(ItemStack itemStack) {
        return isValid(itemStack) && isValid(FurnaceRecipes.instance().getSmeltingResult(itemStack));
    }

    public static boolean isItemInstanceOf(ItemStack itemStack, @Nonnull Class itemClass) {
        return isValid(itemStack) && itemClass.isInstance(itemStack.getItem());
    }

    public static boolean doItemsMatch(ItemStack itemStack, Item item) {
        return isValid(itemStack) && itemStack.getItem() == item;
    }

    public static boolean isValid(@Nullable ItemStack itemStack) {
        return itemStack != null && !itemStack.isEmpty();
    }

    public static ItemStack getItemStackFromBlockState(IBlockState blockState) {
        Item item = Item.getItemFromBlock(blockState.getBlock());
        ItemStack itemStack = ItemStack.EMPTY;
        if (item != Items.AIR) {
            itemStack = new ItemStack(item, 1, blockState.getBlock().getMetaFromState(blockState));
        }
        return itemStack;
    }

    public static boolean isPlayerCarrying(Item item, EntityPlayer player) {
        return ItemStackUtils.doItemsMatch(player.getHeldItemMainhand(), item)
                || ItemStackUtils.doItemsMatch(player.getHeldItemOffhand(), item);
    }

    @Nonnull
    public static String getModIdFromItemStack(@Nonnull ItemStack itemStack) {
        String modid = "";
        Item item = itemStack.getItem();
        if (item.getRegistryName() != null) {
            modid = item.getRegistryName().getResourceDomain();
        } else {
            Platform.attemptLogErrorToCurrentMod("Could not find modid for Item: " + item.getUnlocalizedName());
        }
        return modid;
    }

    public static boolean containsItemStack(ItemStack stack, ItemStack inputStack) {
        return ItemStack.areItemsEqual(stack, inputStack) && stack.getCount() >= inputStack.getCount();
    }

    public static boolean canStacksMerge(ItemStack original, ItemStack addition) {
        return ItemStack.areItemsEqual(original, addition) &&
                ItemStack.areItemStackTagsEqual(original, addition) &&
                original.getItemDamage() == addition.getItemDamage();
    }

    public static boolean canStacksMergeCompletely(ItemStack original, ItemStack addition) {
        return canStacksMerge(original, addition) && original.getCount() + addition.getCount() <= original.getMaxStackSize();
    }

    public static ItemStack mergeStacks(ItemStack original, ItemStack addition) {
        if (ItemStackUtils.canStacksMerge(original, addition)) {
            int spaceToAdd = original.getMaxStackSize() - original.getCount();
            if (spaceToAdd > 0) {
                int amountToAdd = addition.getCount();
                if (amountToAdd > spaceToAdd) {
                    amountToAdd = spaceToAdd;
                }

                addition.shrink(amountToAdd);
                original.grow(amountToAdd);
            }
        }

        return addition;
    }
}
