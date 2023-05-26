package com.example.Mansouri_Anas_exam_IIBDCC_23;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName ="table_name")

public class MainData implements Serializable {

    //Create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;


    //Nouveaux champs
    @ColumnInfo(name="text")
    private String text;

    @ColumnInfo(name="nombrehabitants")
    private String nombrehabitants;

    @ColumnInfo(name="capital")
    private String capital;

    public String getNombrehabitants() {
        return nombrehabitants;
    }

    public void setNombrehabitants(String nombrehabitants) {
        this.nombrehabitants = nombrehabitants;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

//Generate getters and setters

    public int getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setText(String text) {
        this.text = text;
    }
}
