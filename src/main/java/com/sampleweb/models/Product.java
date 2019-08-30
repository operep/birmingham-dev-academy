package com.sampleweb.models;

public class Product implements Comparable<Product> {

    private String name;
    private String path;
    private double amountOfReviews;
    private double overallRating;
    private double fiveStarRatings;
    private double fourStarRatings;
    private double threeStarRatings;
    private double twoStarRatings;
    private double oneStarRatings;

    public int compareTo(Product compareProduct) {
        //TODO - FIGURE OUT AMAZON SORT ALGORITHM
        double compareOverallRating = ((Product) compareProduct).getOverallRating();

        //descending order
        return (int) compareOverallRating - (int) this.overallRating;
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

    public double getAmountOfReviews() {
        return amountOfReviews;
    }

    public void setAmountOfReviews(double amountOfReviews) {
        this.amountOfReviews = amountOfReviews;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
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
