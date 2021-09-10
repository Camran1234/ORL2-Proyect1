package com.example.orl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.orl.R;
import com.example.orl.databinding.FragmenteditorLayoutBinding;
import com.example.orl.databinding.FragmentlistaLayoutBinding;
import com.example.orl.pistas.Central;
import com.example.orl.socket.Client;
import com.example.orl.ui.main.PageViewModel;
import com.google.android.material.snackbar.Snackbar;

public class FragmentLista extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentlistaLayoutBinding binding;


    public static FragmentLista newInstance(int index) {
        FragmentLista fragment = new FragmentLista();
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
        FragmentlistaLayoutBinding binding = FragmentlistaLayoutBinding.inflate(inflater,container, false);
        View view = binding.getRoot();
        EditText editText = binding.solicitudesLista;
        editText.setEnabled(false);

        Button boton = binding.button;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Spinner spinner = binding.spinner;
                String text = spinner.getSelectedItem().toString();
                if(text!=null){
                    if(text.equalsIgnoreCase("")==false){
                        EditText texto = binding.solicitudesLista;
                        String resultado = Central.getListDescription(text);
                        if(resultado.equalsIgnoreCase("")){
                            Snackbar.make(v,"Lista no encontrada", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            texto.setText(Central.getListDescription(text));
                        }

                    }else{
                        Snackbar.make(v,"Dato no seleccionado", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });

        Button solicitud = binding.solictarLista;
        solicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = FragmentEditor.editor;
                if(editText!=null){
                    Spinner spinner = binding.spinner;
                    String text = spinner.getSelectedItem().toString();
                    if(text.equalsIgnoreCase("")==false){
                        String paquete = Central.getSolicitud(text);
                        Client client = new Client("192.168.1.28", 8080, paquete);
                        System.out.println("contactando");
                        Snackbar.make(v,"Enviando solicitud de lista "+text, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else{
                        Snackbar.make(v,"Dato no seleccionado", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }

            }
        });

        return view;
    }
}
