package com.alexlowe.tobe;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Keyes on 6/10/2016.
 */
public class ToBeFragment extends Fragment{
    public static final String TOBE_OBJ = "tobe_object";

    private ToBe mToBe;
    private ViewPager mViewPager;
    private TextView tvToBe;
    private ImageView ivHome, ivDone, ivDelete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tobe, container, false);

        Bundle bundle = this.getArguments();
        mToBe = (ToBe) bundle.getSerializable(TOBE_OBJ);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.viewpager);

        tvToBe = (TextView) view.findViewById(R.id.toBe_text);
        ivDelete = (ImageView) view.findViewById(R.id.delete_button);
        ivDone = (ImageView) view.findViewById(R.id.done_button);
        ivHome = (ImageView) view.findViewById(R.id.home_button);

        setUpClicks();

        setTobeText(tvToBe);

        return view;
    }

    private void setUpClicks() {
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvToBe.getPaintFlags() != Paint.STRIKE_THRU_TEXT_FLAG) {
                    tvToBe.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    tvToBe.setPaintFlags(0);
                }
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Remove Entry");
                builder.setMessage("Are you sure you want to remove this entry?");
                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RemoveEntry();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }

    private void RemoveEntry() {
        int itemNumber = mViewPager.getCurrentItem() - 1;
        Log.i("rimjob", "RemoveEntry: " + itemNumber);

        MainActivity.adapter.removeFragment(itemNumber);
        MainActivity.adapter.notifyDataSetChanged();
        ToBe.logToBes();

        mViewPager.setCurrentItem(itemNumber);
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
