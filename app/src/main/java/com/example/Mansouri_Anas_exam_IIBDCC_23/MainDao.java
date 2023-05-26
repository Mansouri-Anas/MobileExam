package com.example.Mansouri_Anas_exam_IIBDCC_23;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

 @Dao
public interface MainDao {

    // Insert query
    @Insert(onConflict =REPLACE)
    void insert(MainData mainData);
     // Delete query

//     @Delete
//     void reset(List<MainData mainData);


     // Update query
     @Query("UPDATE table_name SET text= :sText,nombrehabitants= :sNombre,capital= :sCapital where ID=:sID")
     void update(int sID, String sText,String sNombre, String sCapital);



     // Get all data query
     @Query("SELECT * FROM table_name")
     List<MainData> getAll();

     @Delete
     void delete(MainData mainData);


     @Delete
     void delete(List<MainData> mainData);

}
