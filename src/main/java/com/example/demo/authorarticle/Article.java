package com.example.demo.authorarticle;

public class Article {
    private String name;
    private int numComments;

    public Article(String name, int numComments) {
        this.name = name;
        this.numComments = numComments;
    }

    public String getName() {
        return name;
    }

    public int getNumComments() {
        return numComments;
    }

}
