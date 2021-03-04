package com.example.fragmentexample4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class OneFragment extends Fragment {
    private EditText editText_name, editText_zip;
    private Button button;
    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);

        editText_name = v.findViewById(R.id.editText_name);
        editText_zip = v.findViewById(R.id.editText_zip);
        button = v.findViewById(R.id.button_submit);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send name to view
                UserInformation information = new UserInformation(editText_name.getText().toString(), editText_zip.getText().toString());
                viewModel.setInformation(information);
            }
        });

        return v;
    }
}