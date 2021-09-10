package com.example.orl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.orl.R;
import com.example.orl.databinding.FragmentcreacionLayoutBinding;
import com.example.orl.databinding.FragmentlistaLayoutBinding;
import com.example.orl.ui.main.PageViewModel;

public class FragmentPiano extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentcreacionLayoutBinding binding;


    public static FragmentPiano newInstance(int index) {
        FragmentPiano fragment = new FragmentPiano();
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
        FragmentcreacionLayoutBinding binding = FragmentcreacionLayoutBinding.inflate(inflater,container, false);
        View view = binding.getRoot();
        return view;
    }
}
