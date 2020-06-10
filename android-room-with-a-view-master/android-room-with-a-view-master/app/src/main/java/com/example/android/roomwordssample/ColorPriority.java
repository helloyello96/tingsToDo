package com.example.android.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "color_priorities")
public class ColorPriority {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "color_priority")
    private int mPriority;

    @ColumnInfo(name = "hexcolor")
    private String hexcolor;

    public ColorPriority(){}

    public ColorPriority(@NonNull int priority, String color) {
        this.mPriority = priority;
        this.hexcolor = color;
    }


    public String getHexcolor() {
        return hexcolor;
    }

    public void setHexcolor(String hexcolor) {
        this.hexcolor = hexcolor;
    }

    public void setPriority(int mPriority) {
        this.mPriority = mPriority;
    }

    public int getPriority() {
        return mPriority;
    }
}
