package com.example.hatice.ders32camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2,btn_temizle;
    EditText text;
    TextView tv;
    String dosya_ad="dosya";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.btn1_id);
        btn2=(Button)findViewById(R.id.btn2_id);
        btn_temizle=(Button)findViewById(R.id.btn_temizle) ;
        text=(EditText)findViewById(R.id.et_id);
        tv=(TextView)findViewById(R.id.textView);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fs=openFileOutput("dosya",MODE_PRIVATE);//mode append de ekliyor. üzerine yazıyor bunda falan
                    fs.write(text.getText().toString().getBytes());
                    fs.close();
                    Log.d("dosyaYazma","dosya kaydedildi");//debug açılsın oldu mu bakalım diye

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fi=openFileInput(dosya_ad);
                    String tempt=" ";
                    int i;
                    while ((i=fi.read())!=-1){
                        tempt +=(char)i;
                    }
                    tv.setText(tempt);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_temizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File f=new File(getFilesDir(),dosya_ad);
                f.delete();

            }
        });

    }
}
