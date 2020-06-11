package com.example.android.roomwordssample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ColorDao {

    @Query("SELECT * from color_priorities ORDER BY color_priority ASC")
    LiveData<List<ColorPriority>> getColorLegend();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ColorPriority colorPriority);

    @Query("DELETE FROM color_priorities")
    void deleteAll();
}
