package com.example.bottomnavigationbar.interviewtesJAVA;

import android.util.Log;

public class ChildJava extends Parentjava {
    String childname = parentname;

    void setChildname() {
        Log.e("Set ChildName", childname);

        childname = parentname;
    }
}
