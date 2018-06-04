package net.dries007.tfc.objects.items.food;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class ItemFoodTFC extends ItemFood
{
    // TODO: 4/6/18 This is temporarily functioning like vanilla food until food system implemented.
    /** Number of ticks to run while 'EnumAction'ing until result. */
    public final int itemUseDuration;
    /** The amount this food item heals the player. */
    private final int healAmount;
    private final float saturationModifier;
    /** If this field is true, the food can be consumed even if the player don't need to eat. */
    private boolean alwaysEdible;
    /** represents the potion effect that will occurr upon eating this food. Set by setPotionEffect */
    private PotionEffect potionId;
    /** probably of the set potion effect occurring */
    private float potionEffectProbability;


    public ItemFoodTFC(int amount, float saturation)
    {
        super(amount,saturation, false);
        this.itemUseDuration = 32;
        this.healAmount = amount;
        //this.isWolfsFavoriteMeat = isWolfFood;
        this.saturationModifier = saturation;
    }

    public ItemFoodTFC(int amount, boolean isWolfFood)
    {
        this(amount, 0.6F);
    }

}
