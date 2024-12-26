package com.example.hm3_m3;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class CountriesFragment extends Fragment {

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewContinents);

        String selectedContinent = getArguments() != null ? getArguments().getString("continent") : "";

        List<Item> countries = getCountriesForContinent(selectedContinent);

        ItemAdapter adapter = new ItemAdapter(requireContext(), countries, item -> {
            CitiesFragment citiesFragment = new CitiesFragment();

            Bundle args = new Bundle();
            args.putString("country", item.getText());
            citiesFragment.setArguments(args);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, citiesFragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Item> getCountriesForContinent(String continent) {
        if ("Евразия".equals(continent)) {
            return Arrays.asList(
                    new Item("Казахстан", ""),
                    new Item("Россия", "")
            );
        }
        return Arrays.asList();
    }
}