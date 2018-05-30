package net.dries007.tfc.objects;

public enum Plant
{
    DANDELION(Category.PLAINS),
    NASTURTIUM(Category.PLAINS),
    MEADS_MILKWEED(Category.PLAINS),
    TROPICAL_MILKWEED(Category.PLAINS),
    BUTTERFLY_MILKWEED(Category.PLAINS),
    CALENDULA(Category.PLAINS),
    ROSE(Category.PLAINS),
    BLUE_ORCHID(Category.PLAINS),
    ALLIUM(Category.PLAINS),
    HOUSTONIA(Category.PLAINS),
    RED_TULIP(Category.PLAINS),
    ORANGE_TULIP(Category.PLAINS),
    WHITE_TULIP(Category.PLAINS),
    PINK_TULIP(Category.PLAINS),
    OXEYE_DAISY(Category.PLAINS),
    GOLDENROD(Category.PLAINS),
    ;

    public final Plant.Category category;

    Plant(Plant.Category category)
    {
        this.category = category;
    }

    public enum Category
    {
        PLAINS,
        CROP,
        DESERT,
        CAVE,
        WATER,
        BEACH,
        ;
        }
}
