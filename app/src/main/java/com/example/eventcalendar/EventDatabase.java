package com.example.eventcalendar;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Event.class, version = 2)
public abstract class EventDatabase extends RoomDatabase {

    private static EventDatabase instance;

    public abstract EventDAO eventDAO();

    public static synchronized EventDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class,"event_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{
        private EventDAO eventDAO;

        private PopulateDBAsyncTask(EventDatabase db) {
            this.eventDAO = db.eventDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0; i<=33;i++){
                eventDAO.insert(new Event(i + "º mini curso de Android",
                        "mini curso de android gratuito durante a semana academica",
                        i+"/3","centro politecnico UFPR"));
                eventDAO.insert(new Event(i + "º meet up de react e react native",
                        "tradicional meet up em curitiba para discutir as tecnologias react no desenvolvimento front end",
                        i+"/5","rua das peras 650"));
                eventDAO.insert(new Event(i + "º aula de SQL e bancos de dados relacionais",
                        "aula completa sobre a linguagem SQL e os principais bancos de dados relacionais utilizados no mercado",
                        i+"/6","avenida das araucarias 830"));

            }
            return null;
        }
    }
}
