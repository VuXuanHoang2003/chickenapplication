package com.pbl5.chickenapplication.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Chicken")
public class ChickenBreed {
    @SerializedName("id")
    @PrimaryKey()
    @NonNull
    private long id;
    @SerializedName("uuid")
    @ColumnInfo()
    private String uuid;
    @SerializedName("predict")
    @ColumnInfo()
    private String url;

    public ChickenBreed(long id, String uuid, String url) {
        this.id = id;
        this.uuid = uuid;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
