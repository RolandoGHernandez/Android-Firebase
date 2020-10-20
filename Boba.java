package com.example.favoriteorders;
//author: beatrice
public class Boba {
    private String name;
    private String tea;
    private String milk;
    private String boba;
    private String key;

    public Boba ()
    {
        this.name = "";
        this.tea = "";
        this.milk = "";
        this.boba = "";
        this.key = "";
    }
    public Boba(String n, String t, String m, String b, String k)
    {
        this.name = n;
        this.tea = t;
        this.milk = m;
        this.boba = b;
        this.key = k;
    }

    public String toString()
    {
        return (this.tea + this.milk + this.boba);
    }

    public String getName()
    {
        return this.name;
    }

    public  String getTea() {
        return this.tea;
    }

    public String getMilk() {
        return this.milk;
    }

    public String getBoba() {
        return this.boba;
    }

    public String getKey() {
        return this.key;
    }
}
