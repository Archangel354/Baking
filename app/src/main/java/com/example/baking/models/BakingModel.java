package com.example.baking.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class BakingModel {

    private String recipeName;
    private int servings;
    private String ingredientsList;
    private ArrayList<ArrayList<Steps>> stepsList;

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

    public String getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public ArrayList<ArrayList<Steps>> getStepsList() {
        return stepsList;
    }

    public void setStepsList(ArrayList<ArrayList<Steps>> stepsList) {
        this.stepsList = stepsList;
    }

    public static class Ingredients {
        private double quantity;
        private String measure;
        private String ingredient;

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
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

    public static class Steps implements Parcelable {
        private int position;
        private String shortDescription;
        private String description;
        private String videoURL;
        private String thumbnailURL;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public Steps(Parcel in) {
            position = in.readInt();
            shortDescription = in.readString();
            description = in.readString();
            videoURL = in.readString();
            thumbnailURL = in.readString();
        }

        // This contructor was necessary in order to instantiate  Steps in MainActivity
        public Steps() {
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(position);
            dest.writeString(shortDescription);
            dest.writeString(description);
            dest.writeString(videoURL);
            dest.writeString(thumbnailURL);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Steps> CREATOR = new Creator<Steps>() {
            @Override
            public Steps createFromParcel(Parcel in) {
                return new Steps(in);
            }

            @Override
            public Steps[] newArray(int size) {
                return new Steps[size];
            }
        };

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

        public String getThumbnailURL() {
            return thumbnailURL;
        }

        public void setThumbnailURL(String thumbnailURL) {
            this.thumbnailURL = thumbnailURL;
        }
    }


}
