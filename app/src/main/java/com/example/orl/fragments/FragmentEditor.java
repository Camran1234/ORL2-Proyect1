package com.example.orl.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.orl.R;
import com.example.orl.databinding.FragmenteditorLayoutBinding;
import com.example.orl.socket.Client;
import com.example.orl.socket.Server;
import com.example.orl.ui.main.PageViewModel;
import com.example.orl.ui.main.PlaceholderFragment;
import com.google.android.material.snackbar.Snackbar;

public class FragmentEditor extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmenteditorLayoutBinding binding;
    public static EditText editor;

    public static FragmentEditor newInstance(int index) {
        FragmentEditor fragment = new FragmentEditor();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmenteditorLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        editor = binding.solicitudes;
        Button boton = binding.comprobarSolicitud;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = binding.solicitudes;
                String text = editText.getText().toString();
                Client client = new Client("192.168.1.28", 8080, text);
                System.out.println("contactando");
                Snackbar.make(v,"Conectando servidor", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        EditText editText = binding.respuesta;
        editText.setEnabled(false);

        /*EditText respuesta = binding.respuesta;
        Server server = new Server(4555, respuesta);
        server.start();*/

        return view;
    }


}
