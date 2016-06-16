package com.alexlowe.tobe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Keyes on 6/10/2016.
 */
public class ToBeFragment extends Fragment{
    public static final String TOBE_OBJ = "tobe_object";
    private ToBe mToBe;
    private TextView tvToBe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tobe, container, false);

        Bundle bundle = this.getArguments();
        mToBe = (ToBe) bundle.getSerializable(TOBE_OBJ);
        tvToBe = (TextView) view.findViewById(R.id.toBe_text);

        setTobeText(tvToBe);

        return view;
    }

    private void setTobeText(TextView textView) {
        String action = mToBe.getAction();
        String result = mToBe.getResult();

        String fullText = String.format("I am going to %s, to be a %s", action, result);

        textView.setText(fullText);
    }

    public static ToBeFragment newInstance(ToBe toBe) {
        ToBeFragment fragment = new ToBeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TOBE_OBJ, toBe);
        fragment.setArguments(bundle);

        return fragment;
    }


}
