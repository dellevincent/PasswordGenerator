/*
 *This app was created by Vincent Delle
 *Date 10/15/2021
 * Version 1.0
 * This was created out of boredom and learning purposes
 **/

package com.PasswordGenerator.passwordgenerator;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
