package com.sampleweb.models;

public class Product implements Comparable<Product> {

    private String name;
    private String path;
    private int amountOfReviews;
    private int overallRating;
    private double fiveStarRatings;
    private double fourStarRatings;
    private double threeStarRatings;
    private double twoStarRatings;
    private double oneStarRatings;

    public int compareTo(Product compareProduct) {
        //TODO - FIGURE OUT AMAZON SORT ALGORITHM
        int compareOverallRating = ((Product) compareProduct).getOverallRating();

        //descending order
        return compareOverallRating - this.overallRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAmountOfReviews() {
        return amountOfReviews;
    }

    public void setAmountOfReviews(int amountOfReviews) {
        this.amountOfReviews = amountOfReviews;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public double getFiveStarRatings() {
        return fiveStarRatings;
    }

    public void setFiveStarRatings(double fiveStarRatings) {
        this.fiveStarRatings = fiveStarRatings;
    }

    public double getFourStarRatings() {
        return fourStarRatings;
    }

    public void setFourStarRatings(double fourStarRatings) {
        this.fourStarRatings = fourStarRatings;
    }

    public double getThreeStarRatings() {
        return threeStarRatings;
    }

    public void setThreeStarRatings(double threeStarRatings) {
        this.threeStarRatings = threeStarRatings;
    }

    public double getTwoStarRatings() {
        return twoStarRatings;
    }

    public void setTwoStarRatings(double twoStarRatings) {
        this.twoStarRatings = twoStarRatings;
    }

    public double getOneStarRatings() {
        return oneStarRatings;
    }

    public void setOneStarRatings(double oneStarRatings) {
        this.oneStarRatings = oneStarRatings;
    }
}
