package com.example.baking.models;

import java.util.List;

public class BakingModel {

    private String recipeName;
    private int servings;
    private List<Ingredients> ingredientsList;
    private List<Steps> stepsList;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    public static class Ingredients {
        private float quantity;
        private String measure;
        private String ingredient;

        public float getQuantity() {
            return quantity;
        }

        public void setQuantity(float quantity) {
            this.quantity = quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getIngredient() {
            return ingredient;
        }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }
    }

    public static class Steps {
        private String shortDescription;
        private String description;
        private String videoURL;

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public void setVideoURL(String videoURL) {
            this.videoURL = videoURL;
        }
    }


}
