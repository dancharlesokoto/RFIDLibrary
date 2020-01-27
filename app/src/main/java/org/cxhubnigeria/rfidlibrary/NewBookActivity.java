package org.cxhubnigeria.rfidlibrary;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewBookActivity extends AppCompatActivity implements MovieQuoteAdapter.Callback {

    String rfidTAG = ReadWActivity.rfidTag;
    String mSize;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    private static final String TAG = "MainActivity";
    DatePickerDialog.OnDateSetListener dateSetListener;

    private org.cxhubnigeria.rfidlibrary.MovieQuoteAdapter mAdapter;
    //private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        getSupportActionBar().setTitle("Book Inventry Dashboard");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //  mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEditDialog(null);
            }
        });

        mAdapter = new org.cxhubnigeria.rfidlibrary.MovieQuoteAdapter(this);
        RecyclerView view = (RecyclerView) findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setHasFixedSize(true);
        view.setAdapter(mAdapter);

    }
    private void showAddEditDialog(final MovieQuote movieQuote) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(movieQuote == null ? R.string.add_dialog_title : R.string.edit_dialog_title));
        View view = getLayoutInflater().inflate(R.layout.dialog_add, null, false);
        builder.setView(view);

        final EditText addBookName = (EditText) view.findViewById(R.id.addBookName);
        final EditText addBookInfo = (EditText) view.findViewById(R.id.addBookInfo);
        final EditText addIsdn = (EditText) view.findViewById(R.id.addIsdn);
        final EditText addPosition = (EditText) view.findViewById(R.id.addPosition);

        final TextView addRfidTag = (TextView) view.findViewById(R.id.addRfidTag);
        final TextView dateText = (TextView) view.findViewById(R.id.date_entry);

        //String status = "PENDING";
        //String nin = Long.toString(numbGen());
        //String timestamp = getDateTime();


       /* ArrayAdapter<String> adapter= new ArrayAdapter<String>(NewBookActivity.this, android.R.layout.simple_spinner_item,

                getResources().getStringArray(R.array.states));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        soriginText.setAdapter(adapter);

        ArrayAdapter<String> statadapter= new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,

                getResources().getStringArray(R.array.status));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusText.setAdapter(statadapter);*/


        if (movieQuote != null) {
            // pre-populate
            addBookName.setText(movieQuote.getBookName());
            addBookInfo.setText(movieQuote.getBookInfo());
            addIsdn.setText(movieQuote.getIsdn());
            addPosition.setText(movieQuote.getPosition());
            addRfidTag.setText(movieQuote.getRfidTag());

            dateText.setText(movieQuote.getTimestamp());
            //statusText.setText(movieQuote.getStatus());
            //timestamp = movieQuote.getTimestamp();
            // nin = movieQuote.getNin();
            // status = movieQuote.getStatus();




            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // empty
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // empty
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String bookN = addBookName.getText().toString();
                    String bookI = addBookInfo.getText().toString();
                    String isdn = addIsdn.getText().toString();
                    String position = addPosition.getText().toString();
                    String status = "New";

                    String timestamp = stamp();


                    //String status = "PENDING";
                    //String nin = Long.toString(numbGen());
                    //String timestamp = getDateTime();
                    mAdapter.update(movieQuote, bookN, bookI, isdn, position, status, timestamp);
                    //  mAdapter.update(movieQuote, pnin, address);
                    //  mAdapter.update(movieQuote, dob, pob);
                    //   mAdapter.update(movieQuote, sex, sorigin);
                    //   mAdapter.update(movieQuote, lga, signature);
                }
            };

            addBookName.addTextChangedListener(textWatcher);
            addBookInfo.addTextChangedListener(textWatcher);
            addIsdn.addTextChangedListener(textWatcher);
            addPosition.addTextChangedListener(textWatcher);
            addRfidTag.addTextChangedListener(textWatcher);

            dateText.addTextChangedListener(textWatcher);


        }

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (movieQuote == null) {

                    String bookN = addBookName.getText().toString();
                    String bookI = addBookInfo.getText().toString();
                    String isdn = addIsdn.getText().toString();
                    String position = addPosition.getText().toString();
                    // String nin = Long.toString(numbGen());

                    String timestamp = stamp();


                    String status = "NEW";
                    String rfid = rfidTAG;




                    mAdapter.add(new MovieQuote(bookN, bookI, isdn,rfid, position, status, timestamp));
                    //  mAdapter.add(new MovieQuote(pnin, address));
                    //  mAdapter.add(new MovieQuote(dob, pob));
                    //  mAdapter.add(new MovieQuote(sex, sorigin));
                    // mAdapter.add(new MovieQuote(lga, signature));
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        builder.create().show();
    }


    @Override
    public void onEdit(final MovieQuote movieQuote) {
        showAddEditDialog(movieQuote);
    }

    public static long numbGen() {
        while (true) {
            long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 13)
                return numb;
        }
    }
    public String stamp() {
        String date;

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = simpleDateFormat.format(calendar.getTime());
        return date;


    }



}
