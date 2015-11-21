package com.example.android.login;

/**
 * Created by medha on 17/11/15.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;




/**
 * Created by medha on 17/11/15.
 */
public class ExtendPeriod
{
    Context context;
    AlertDialog.Builder alert;
    LayoutInflater factory;
    public ExtendPeriod(Context context)
    {
        this.context = context;
        factory = LayoutInflater.from(context);
        alert = new AlertDialog.Builder(context);
        final View textEntryView = factory.inflate(R.layout.edittext1,null);
        alert.setView(textEntryView);
        alert.setTitle("Enter No Of Days To Extend");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }
        );

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }

        );


    }

    public void show(){

        alert.show();
    }

}
