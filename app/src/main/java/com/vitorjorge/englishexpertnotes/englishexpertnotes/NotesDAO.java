package com.vitorjorge.englishexpertnotes.englishexpertnotes;

import com.activeandroid.query.Select;

import java.util.List;

public class NotesDAO {

    public List<Notes> findAll(){
        return new Select().from(Notes.class).orderBy("name ASC").execute();
    }

    public static void delete(Notes obj){
        obj.delete();

    }

    public static void update(Integer position, String newItem){
        Notes model = Notes.load(Notes.class, position);
        model.name = newItem;
        model.save();
    }
}
