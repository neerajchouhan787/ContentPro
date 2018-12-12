package com.nc.contentpro;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StdDataProvider.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());

        values.put(StdDataProvider.GRADE,
                ((EditText)findViewById(R.id.phone)).getText().toString());

        Uri uri = getContentResolver().insert(
                StdDataProvider.CONTENT_URI, values);
        System.out.println(uri.toString());
        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.nc";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                System.out.println(c.getString(c.getColumnIndex(StdDataProvider._ID)) +
                        ", " +  c.getString(c.getColumnIndex( StdDataProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex( StdDataProvider.GRADE)));
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StdDataProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StdDataProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StdDataProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
