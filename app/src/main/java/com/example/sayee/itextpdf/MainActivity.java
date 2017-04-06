package com.example.sayee.itextpdf;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTableFooter;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1= (Button)findViewById(R.id.button1);
        b2= (Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPDF();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailPDF();
            }
        });
    }
    public void createPDF(){
        Document doc = new Document();
        try {
            String path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";


            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();
            System.out.println(dir.getAbsolutePath());
            // Toast.makeText(getApplicationContext(),"hiii",Toast.LENGTH_LONG).show();

            Log.d("PDFCreator", "PDF Path: " + path);


            File file = new File(dir, "sample.pdf");
            // System.out.println(file.getAbsolutePath());
//Toast.makeText(getApplicationContext(),file.getAbsolutePath(),Toast.LENGTH_LONG).show();
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter.getInstance(doc,fOut);
            //PdfWriter.getInstance(doc, fOut);


            //open the document
            doc.open();
            Log.v("jhhghfggg","jhgkuyuhjjjjj");
            // Toast.makeText(getApplicationContext(),"hiii",Toast.LENGTH_LONG).show();

            Paragraph p1 = new Paragraph("Hi! I am Sayee!!!! GOOD TO SEE THIS PDF" +
                    "I used to believe\n" +
                    "We were burnin' on the edge of somethin' beautiful\n" +
                    "Somethin' beautiful\n" +
                    "Selling a dream\n" +
                    "Smoke and mirrors keep us waitin' on a miracle\n" +
                    "On a miracle\n" +
                    "Say, go through the darkest of days\n" +
                    "Heaven's a heartbreak away\n" +
                    "Never let you go, never let me down\n" +
                    "Oh, it's been a hell of a ride\n" +
                    "Driving the edge of a knife\n" +
                    "Never let you go, never let me down\n" +
                    "Don't you give up, nah-nah-nah\n" +
                    "I won't give up, nah-nah-nah\n" +
                    "Let me love you\n" +
                    "Let me love you\n" +
                    "Don't you give up, nah-nah-nah\n" +
                    "I won't give up, nah-nah-nah\n" +
                    "Let me love you\n" +
                    "Let me love you\n" +
                    "Oh baby, baby\n" +
                    "Don't fall asleep\n" +
                    "At the wheel, we've got a million miles ahead of us\n" +
                    "Miles ahead of us\n" +
                    "All that we need\n" +
                    "Is a rude awakening to know we're good enough\n" +
                    "Know we're good enough");
            Font paraFont= new Font(Font.FontFamily.COURIER);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            p1.setFont(paraFont);
            doc.add(p1);
            //add paragraph to document
            //doc.add(p1);

            Paragraph p2 = new Paragraph("This is an example of a simple paragraph");
            Font paraFont2= new Font(Font.FontFamily.COURIER,14.0f, Color.GREEN);
            p2.setAlignment(Paragraph.ALIGN_CENTER);
            p2.setFont(paraFont2);

            doc.add(p2);


            // set footer
//            Phrase footerText = new Phrase("This is an example of a footer");
//            PdfPTableFooter pdfFooter = new PdfPTableFooter(footerText, false);
//            doc.setFooter(pdfFooter);
//            Toast.makeText(getApplicationContext(), "Created...", Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType( Uri.fromFile( file ), "sample/pdf" );
//            startActivity(intent);

        }
        catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally
        {
            doc.close();
        }

        // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";

        //  File file = new File(path, "demo.pdf");




    }
    void mailPDF()
    {
        String[] mailto = {"sayee0612@gmail.com"};
        Uri uri =Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF/","sample.pdf"));

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "My Body");
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(emailIntent, "Send email using:"));
    }
}
