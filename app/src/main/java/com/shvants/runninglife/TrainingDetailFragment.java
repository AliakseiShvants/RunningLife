package com.shvants.runninglife;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingDetailFragment extends Fragment {

    public static final String LIKE = "Like +";
    public static final String COUNTER_KEY = "counter";
    private static int counter = 0;

    public TrainingDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_detail, container, false);
        final Button button = view.findViewById(R.id.like_fragment_button);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY);
            button.setText(LIKE + counter);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                counter = counter + 1;
                button.setText(LIKE + counter);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNTER_KEY, counter);
    }
}
