package com.alexlowe.tobe;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Keyes on 6/10/2016.
 */
public class ToBe implements Serializable{
    private String action;
    private String result;

    public static ArrayList<ToBe> shortTerm = new ArrayList<>();
    public static ArrayList<ToBe> longTerm = new ArrayList<>();

    public ToBe(String action, String result){
        this.action = action;
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static void logToBes(){
        String str = "";

        for(ToBe toBe : shortTerm){
            str += String.format("Action: %s Result: %s \n", toBe.getAction(), toBe.getResult());
        }

        Log.i("rimjob", "logToBes: " + str);
    }


}
