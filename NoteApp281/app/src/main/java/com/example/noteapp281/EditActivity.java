package com.example.noteapp281;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.noteapp281.bean.Note;
import com.example.noteapp281.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private Note note;
    private EditText etTitle,etWriter,etContent;

    private NoteDbOpenHelper mNoteDbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = findViewById(R.id.et_title);
        etWriter = findViewById(R.id.et_writer);
        etContent = findViewById(R.id.et_content);

        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if (note != null) {
            etTitle.setText(note.getTitle());
            etWriter.setText(note.getWriter());
            etContent.setText(note.getContent());
        }
        mNoteDbOpenHelper = new NoteDbOpenHelper(this);
    }

    public void save(View view) {
        String title = etTitle.getText().toString();
        String writer = etWriter.getText().toString();
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.toastShort(this, "标题不能为空！");
            return;
        }

        note.setTitle(title);
        note.setWriter(writer);
        note.setContent(content);
        note.setCreatedTime(getCurrentTimeFormat());
        long rowId = mNoteDbOpenHelper.updateData(note);
        if (rowId != -1) {
            ToastUtil.toastShort(this, "修改成功！");
            this.finish();
        }else{
            ToastUtil.toastShort(this, "修改失败！");
        }

    }

    private String getCurrentTimeFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");
        Date date = new Date();
        return sdf.format(date);
    }
}