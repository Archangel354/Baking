package com.example.baking;

public class RecipeList {

    // items for a particular recipe
    private String rName;
    private String rServings;
    private String rImage;

    public RecipeList(String name, String servings, String image) {
        rName = name;
        rServings = servings;
        rImage = image;
    }

    public String getName() {
        return rName;
    }

    public String getServings() {
        return rServings;
    }

    public String getImage() {
        return rImage;
    }
}
