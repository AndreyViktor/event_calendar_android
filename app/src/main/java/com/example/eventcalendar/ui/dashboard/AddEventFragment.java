package com.example.eventcalendar.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.eventcalendar.Event;
import com.example.eventcalendar.EventViewModel;
import com.example.eventcalendar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddEventFragment extends Fragment {

    private EventViewModel eventViewModel;
    private TextInputEditText address;
    private TextInputEditText title;
    private TextInputEditText description;
    private EditText date;
    private Button addBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventViewModel =
                ViewModelProviders.of(getActivity()).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_event, container, false);

        address = root.findViewById(R.id.address_input);
        title = root.findViewById(R.id.title_et);
        description = root.findViewById(R.id.description_ET);
        date = root.findViewById(R.id.date_input);

        addBtn = root.findViewById(R.id.add_event_button);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
                NavController navController = Navigation.findNavController(v);
                navController.popBackStack();
            }
        });
        return root;
    }
    private void saveEvent(){
        String stringTitle = title.getText().toString();
        String stringDescription = description.getText().toString();
        String stringDate = date.getText().toString();
        String stringAddress = address.getText().toString();

        Event event = new Event(stringTitle,stringDescription,stringDate,stringAddress);
        eventViewModel.insert(event);

    }
}