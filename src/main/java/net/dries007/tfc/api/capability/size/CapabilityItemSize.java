/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.capability.size;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import net.dries007.tfc.api.capability.DumbStorage;
import net.dries007.tfc.api.util.TFCConstants;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.util.Helpers;

public final class CapabilityItemSize
{
    @CapabilityInject(IItemSize.class)
    public static final Capability<IItemSize> ITEM_SIZE_CAPABILITY = Helpers.getNull();
    public static final ResourceLocation KEY = new ResourceLocation(TFCConstants.MOD_ID, "item_size");

    public static final Map<IIngredient<ItemStack>, Supplier<ICapabilityProvider>> CUSTOM_ITEMS = new HashMap<>(); //Used inside CT, set custom IItemSize for items outside TFC

    public static void preInit()
    {
        CapabilityManager.INSTANCE.register(IItemSize.class, new DumbStorage<>(), ItemSizeHandler::new);
    }

    /**
     * Adds a simple IItemSize capability to an item instance. Call this from an AttachCapabilitiesEvent handler.
     * This will also override the item's stacksize. If an item uses a custom getStacksize implementation, that will take priority
     *
     * @param event    The AttachCapabilitiesEvent that was fired
     * @param item     The item to attach the capability to
     * @param size     The item size
     * @param weight   The item weight
     * @param canStack An override for if this item can stack or not.
     */
    public static void add(AttachCapabilitiesEvent<ItemStack> event, Item item, Size size, Weight weight, boolean canStack)
    {
        event.addCapability(KEY, new ItemSizeHandler(size, weight, canStack));
        item.setMaxStackSize(IItemSize.getStackSize(size, weight, canStack));
    }

    /**
     * Gets the IItemSize instance from an itemstack, either via capability or via interface
     *
     * @param stack The stack
     * @return The IItemSize if it exists, or null if it doesn't
     */
    @Nullable
    public static IItemSize getIItemSize(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            if (stack.getItem() instanceof IItemSize)
            {
                return (IItemSize) stack.getItem();
            }
            else if (stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof IItemSize)
            {
                return (IItemSize) ((ItemBlock) stack.getItem()).getBlock();
            }
            return stack.getCapability(ITEM_SIZE_CAPABILITY, null);
        }
        return null;
    }

    @Nullable
    public static ICapabilityProvider getCustomSize(ItemStack stack)
    {
        Set<IIngredient<ItemStack>> itemItemSet = CUSTOM_ITEMS.keySet();
        for (IIngredient<ItemStack> ingredient : itemItemSet)
        {
            if (ingredient.testIgnoreCount(stack))
            {
                return CUSTOM_ITEMS.get(ingredient).get();
            }
        }
        return null;
    }
}
