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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author  Lior Shem Tov
 * @version	1.1
 * @since	27/03/2025
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
    private static final int REQUEST_CODE_PERMISSION = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weddings();


        try
        {
            tv1.setText(read());
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }

    /**
     * Function that do all the weddings.
     */
    public void weddings()
    {
        tv1 = (TextView) findViewById(R.id.tv1);
        eD1 = (EditText) findViewById(R.id.eD1);
    }

    /**
     * Checks if external storage is available for read and write.
     *
     * @return true if external storage is mounted and available, false otherwise.
     */
    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * Checks if the app has permission to write to external storage.
     *
     * @return true if permission is granted, false otherwise.
     */
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Requests permission to write to external storage if it has not been granted.
     */
    private void requestPermission() {
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * Handles the result of the permission request.
     *
     * @param requestCode  The request code passed in requestPermissions.
     * @param permissions  The requested permissions.
     * @param grantResults The grant results for the corresponding permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission to access external storage granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission to access external storage NOT granted", Toast.LENGTH_LONG).show();
            }
        }
    }


    /**
     * Read string.
     *
     * @return the string
     * @throws IOException the io exception
     */
    public String read() throws IOException {
        if(isExternalStorageAvailable() && checkPermission())
        {
            File externalDir = Environment.getExternalStorageDirectory();
            File file = new File(externalDir, FILENAME);
            file.getParentFile().mkdirs();
            FileReader reader = new FileReader(file);
            BufferedReader bR = new BufferedReader(reader);
            StringBuilder sB = new StringBuilder();
            String line = "";
            while ((line = bR.readLine()) != null) {
                sB.append(line);
            }
            bR.close();
            reader.close();
            return sB.toString();
        }
        else
        {
            requestPermission();
        }
        return "";
    }

    /**
     * Write.
     *
     * @param data the data
     * @throws IOException the io exception
     */
    public void write(String data) throws IOException {

        if(isExternalStorageAvailable() && checkPermission())
        {
            File externalDir = Environment.getExternalStorageDirectory();
            File file = new File(externalDir, FILENAME);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();
        }
        else
        {
            requestPermission();
        }
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