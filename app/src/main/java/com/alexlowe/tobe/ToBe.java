package com.alexlowe.tobe;

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
}
