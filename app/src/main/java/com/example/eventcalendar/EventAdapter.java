package com.example.eventcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private List<Event> eventList = new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentEvent = eventList.get(position);
        holder.address.setText(currentEvent.getAddress());
        holder.description.setText(currentEvent.getDescription());
        holder.title.setText(currentEvent.getTitle());
        holder.date.setText(currentEvent.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEventList(List<Event> eventList){
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    public Event getEventAt(int position){
        return eventList.get(position);
    }

    class EventHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView address;

        EventHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            description = itemView.findViewById(R.id.description_tv);
            date = itemView.findViewById(R.id.date_tv);
            address = itemView.findViewById(R.id.address_tv);
        }
    }
}
