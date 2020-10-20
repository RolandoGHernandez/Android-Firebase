package com.example.favoriteorders;
//author: beatrice
public class Chicken {
    private String name;
    private String spice;
    private String sauce;
    private String size;
    private String key;

    public Chicken()
    {
        this.name = "";
        this.size = "";
        this.spice = "";
        this.sauce = "";
        this.key = "";
    }

    public Chicken(String n, String s1, String s2, String s3, String k)
    {
        this.name = n;
        this.size = s1;
        this.spice = s2;
        this.sauce = s3;
        this.key = k;
    }

    public String toString()
    {
        return (this.size + this.spice + this.sauce);
    }

    public String getName()
    {
        return this.name;
    }


    public String getSize()
    {
        return this.size;
    }

    public String getSpice()
    {
        return this.spice;
    }

    public String getSauce()
    {
        return this.sauce;
    }

    public String getKey()
    {
        return this.key;
    }





}
