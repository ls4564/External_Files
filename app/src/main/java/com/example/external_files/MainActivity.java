package com.example.external_files;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author  Lior Shem Tov
 * @version	1.1
 * @since	03/03/2025
 * All the functionality
 */

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity
{
    /**
     * The Tv 1.
     */
    TextView tv1;
    /**
     * The E d 1.
     */
    EditText eD1;
    private final String FILENAME = "Lior.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weddings();
        /*
        try
        {
            tv1.setText(read());
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

         */


    }

    /**
     * Function that do all the weddings.
     */
    public void weddings()
    {
        tv1 = (TextView) findViewById(R.id.tv1);
        eD1 = (EditText) findViewById(R.id.eD1);
    }
    


    public String read() throws IOException {
        return "s";
    }

    public void write(String data) throws IOException {

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.it2)
        {
            Intent si = new Intent(this, Menu_page.class);
            startActivity(si);
        }
        return true;
    }

    /**
     * Btn 1 save.
     *
     * @param view the view
     * @throws IOException the io exception
     */
    public void btn1_save(View view) throws IOException {
        String data = read();
        data = data + eD1.getText().toString();
        write(data);
        tv1.setText(read());

    }

    /**
     * Btn 2 reset.
     *
     * @param view the view
     * @throws IOException the io exception
     */
    public void btn2_reset(View view) throws IOException {
        write("");
        tv1.setText("");
        eD1.setText("");
    }

    /**
     * Btn 3 exit.
     *
     * @param view the view
     * @throws IOException the io exception
     */
    public void btn3_exit(View view) throws IOException {
        String data = read();
        data = data + eD1.getText().toString();
        write(data);
        finish();
    }
}