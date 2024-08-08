package com.example.aplikasilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEditActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private Button btnSave;
    private DatabaseHelper2 dbHelper;
    private Note note;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);
        dbHelper = new DatabaseHelper2(this);

        if(getIntent().hasExtra("note_id")){
            int noteId = getIntent().getIntExtra("note_id",-1);
            note = dbHelper.getNote(noteId);
            if(note != null){
                etTitle.setText(note.getContent());
                etContent.setText(note.getContent());
                isEdit = true;
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                String date = new SimpleDateFormat("yyyy-,MM-dd HH:mm;;ss",
                        Locale.getDefault()).format(new Date());

                if (title.isEmpty() || content.isEmpty()){
                    Toast.makeText(AddEditActivity.this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(isEdit){
                    note.setTitle(title);
                    note.setContent(content);
                    note.setDate(date);
                    dbHelper.updateNote(note);
                } else {
                    note = new Note();
                    note.setTitle(title);
                    note.setContent(content);
                    note.setDate(date);
                    dbHelper.addNote(note);
                }
                finish();
            }
        });
    }
}