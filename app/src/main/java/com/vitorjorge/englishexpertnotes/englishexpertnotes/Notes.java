package com.vitorjorge.englishexpertnotes.englishexpertnotes;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Notes")
public class Notes extends Model {
    @Column(name = "name")
    public String name;
}
