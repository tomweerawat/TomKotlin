package com.example.hotumit.tomkotlin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HOTUM IT on 1/2/2561.
 */

public class TomPoJo {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;

    @Expose
    @SerializedName("price")
    private String price;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("name")
    private String name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", thumbnail = "+thumbnail+", price = "+price+", description = "+description+", name = "+name+"]";
    }
}
