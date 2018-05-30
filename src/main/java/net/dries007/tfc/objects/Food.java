package net.dries007.tfc.objects;

public enum Food
{
    //mineral, carb, fat, protein, vitamin
    BANANA(1, 1, 1, 1, 1, Category.NORMAL),
    BLACKBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    BLUEBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    BUNCHBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    CHERRY(1, 1, 1, 1, 1, Category.NORMAL),
    CLOUDBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    CRANBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    ELDERBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    GOOSEBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    GREEN_APPLE(1, 1, 1, 1, 1, Category.NORMAL),
    LEMON(1, 1, 1, 1, 1, Category.NORMAL),
    OLIVE(1, 1, 1, 1, 1, Category.NORMAL),
    ORANGE(1, 1, 1, 1, 1, Category.NORMAL),
    PEACH(1, 1, 1, 1, 1, Category.NORMAL),
    PLUM(1, 1, 1, 1, 1, Category.NORMAL),
    RASPBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    RED_APPLE(1, 1, 1, 1, 1, Category.NORMAL),
    SNOWBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    STRAWBERRY(1, 1, 1, 1, 1, Category.NORMAL),
    WINTERGREENBERRY(1, 1, 1, 1, 1, Category.NORMAL),

    BARLEY_GRAIN(1, 1, 1, 1, 1, Category.NORMAL),
    BARLEY_BREAD(1, 1, 1, 1, 1, Category.NORMAL),
    MAIZE(1, 1, 1, 1, 1, Category.NORMAL),
    CORN_BREAD(1, 1, 1, 1, 1, Category.NORMAL),
    OAT_GRAIN(1, 1, 1, 1, 1, Category.NORMAL),
    OAT_BREAD(1, 1, 1, 1, 1, Category.NORMAL),
    RICE_GRAIN(1, 1, 1, 1, 1, Category.NORMAL),
    RICE_BREAD(1, 1, 1, 1, 1, Category.NORMAL),
    RYE_GRAIN(1, 1, 1, 1, 1, Category.NORMAL),
    RYE_BREAD(1, 1, 1, 1, 1, Category.NORMAL),
    WHEAT_GRAIN(1, 1, 1, 1, 1, Category.NORMAL),
    WHEAT_BREAD(1, 1, 1, 1, 1, Category.NORMAL),

    CABBAGE(1, 1, 1, 1, 1, Category.NORMAL),
    CARROT(1, 1, 1, 1, 1, Category.NORMAL),
    GARLIC(1, 1, 1, 1, 1, Category.NORMAL),
    GREENBEAN(1, 1, 1, 1, 1, Category.NORMAL),
    GREEN_BELL_PEPPER(1, 1, 1, 1, 1, Category.NORMAL),
    ONION(1, 1, 1, 1, 1, Category.NORMAL),
    POTATO(1, 1, 1, 1, 1, Category.NORMAL),
    RED_BELL_PEPPER(1, 1, 1, 1, 1, Category.NORMAL),
    RUTABAGA(1, 1, 1, 1, 1, Category.NORMAL),
    SEAWEED(1, 1, 1, 1, 1, Category.NORMAL),
    SQUASH(1, 1, 1, 1, 1, Category.NORMAL),
    TOMATO(1, 1, 1, 1, 1, Category.NORMAL),
    YELLOW_BELL_PEPPER(1, 1, 1, 1, 1, Category.NORMAL),

    BEEF(1, 1, 1, 1, 1, Category.NORMAL),
    CALAMARI(1, 1, 1, 1, 1, Category.NORMAL),
    EGG(1, 1, 1, 1, 1, Category.NORMAL),
    FISH(1, 1, 1, 1, 1, Category.NORMAL),
    HORSE(1, 1, 1, 1, 1, Category.NORMAL),
    MUTTON(1, 1, 1, 1, 1, Category.NORMAL),
    PORK(1, 1, 1, 1, 1, Category.NORMAL),
    POULTRY(1, 1, 1, 1, 1, Category.NORMAL),
    VENISON(1, 1, 1, 1, 1, Category.NORMAL),
    SOYBEAN(1, 1, 1, 1, 1, Category.NORMAL),

    CHEESE(1, 1, 1, 1, 1, Category.NORMAL),
    MILK(1, 1, 1, 1, 1, Category.NORMAL),
    ;

    public final int mineral;
    public final int carb;
    public final int fat;
    public final int protein;
    public final int vitamin;

    public enum FoodPrep
    {
        RAW,
        COOKED,
        SALTED,
        BRINED,
        PICKLED,
        SMOKED,
        DRIED,

    }

    public final Category category;

    Food(int calcium, int carb, int fat, int protein, int vitamin, Food.Category category)
    {
        // Nutrient values for each food item todo set nutrient values.
        this.carb = carb;
        this.fat = fat;
        this.protein = protein;
        this.vitamin = vitamin;
        this.mineral = calcium;
        this.category = category;
    }

    public enum Category {
        //DECAY RATE todo set decay rates.
        VERY_LOW,
        LOW,
        NORMAL,
        HIGH,
        VERY_HIGH,
        ;
    }
}
