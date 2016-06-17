package com.alexlowe.tobe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Keyes on 6/10/2016.
 */
public class EntryFragment extends Fragment {
    private Button btnEnter;
    private EditText etAction;
    private EditText etToBe;
    private ToBePagerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        adapter = MainActivity.adapter;

        etAction = (EditText) view.findViewById(R.id.action_editText);
        etToBe = (EditText) view.findViewById(R.id.toBe_editText);
        btnEnter = (Button) view.findViewById(R.id.enter_button);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasInput()){
                    addToBe();
                }else{
                    Toast.makeText(getContext(), "Fill both fields to enter", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean hasInput() {
        return etToBe.getText().toString().trim().length() > 0 &&
                etAction.getText().toString().trim().length() > 0;
    }


    private void addToBe() {
        String action = String.valueOf(etAction.getText()).trim();
        String result = String.valueOf(etToBe.getText()).trim();

        ToBe toBe = new ToBe(action, result);
        ToBe.shortTerm.add(toBe);

        ToBeFragment fragment = ToBeFragment.newInstance(toBe);
        adapter.addFragment(fragment);
        adapter.notifyDataSetChanged();
        clear();
        ToBe.logToBes();
    }

    private void clear() {
        etAction.setText("");
        etToBe.setText("");
        Toast.makeText(getContext(), "ToBe added", Toast.LENGTH_SHORT).show();
    }
}
