package com.chucknorrisjokes.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.chucknorrisjokes.model.JokeDataClass;

@Database(entities = {JokeDataClass.class},
        version = 1, exportSchema = false)
public abstract class DocumentsRoomDatabase extends RoomDatabase {

    public abstract JokeDao jokeDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile DocumentsRoomDatabase INSTANCE;

    public static DocumentsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DocumentsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DocumentsRoomDatabase.class, "joke_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }


}
