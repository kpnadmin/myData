package com.android.intentcapture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private File file = null;
    private final int REQUEST_IMAGE_CAPTURE = 12121;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()  + "/test.jpg");


        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        });
         imageView = (ImageView) findViewById(R.id.imageView);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            if (file != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                imageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "File is null.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
