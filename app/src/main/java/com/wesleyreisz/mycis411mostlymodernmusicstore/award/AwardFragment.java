package com.wesleyreisz.mycis411mostlymodernmusicstore.award;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wesleyreisz.mycis411mostlymodernmusicstore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AwardFragment extends Fragment {


    public AwardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_award, container, false);
    }

}
