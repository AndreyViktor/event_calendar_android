package com.example.eventcalendar;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EventRepository {
    private EventDAO eventDAO;
    private LiveData<List<Event>> allEvents;

    public EventRepository(Application application){
        EventDatabase db = EventDatabase.getInstance(application);
        eventDAO = db.eventDAO();
        allEvents = eventDAO.getAllEvents();
    }
    public void insert(Event event){
        new InsertEventAsyncTask(eventDAO).execute(event);
    }
    public void update(Event event){
        new UpdateEventAsyncTask(eventDAO).execute(event);
    }
    public void delete(Event event){
        new DeleteEventAsyncTask(eventDAO).execute(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    private static class InsertEventAsyncTask extends AsyncTask<Event, Void,Void>{
        private EventDAO eventDao;

        private InsertEventAsyncTask(EventDAO eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }
    private static class UpdateEventAsyncTask extends AsyncTask<Event, Void,Void>{
        private EventDAO eventDao;

        private UpdateEventAsyncTask(EventDAO eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.update(events[0]);
            return null;
        }
    }
    private static class DeleteEventAsyncTask extends AsyncTask<Event, Void,Void>{
        private EventDAO eventDao;

        private DeleteEventAsyncTask(EventDAO eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.delete(events[0]);
            return null;
        }
    }
}
