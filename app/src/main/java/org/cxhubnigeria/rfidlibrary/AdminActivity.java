package org.cxhubnigeria.rfidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    private Button addbooks;
    private Button borrow;
    private Button returnbook;
    private Button students;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addbooks = (Button) findViewById(R.id.newbook);
        borrow = (Button) findViewById(R.id.borrowed);
        returnbook = (Button) findViewById(R.id.request);
        students = (Button) findViewById(R.id.students);
        logout = (Button) findViewById(R.id.logout_admin);

        addbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, RfidActivity.class));
            }
        });

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, LendingListActivity.class));
            }
        });

        returnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ReturnListActivity.class));
            }
        });

        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, StudentListActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });

    }
}
