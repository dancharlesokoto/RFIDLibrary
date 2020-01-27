package org.cxhubnigeria.rfidlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class BookSearchActivity extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);


        mUserDatabase = FirebaseDatabase.getInstance().getReference("Books");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString().toLowerCase();

                firebaseUserSearch(searchText);

            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(BookSearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("bookName").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Books, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Books, UsersViewHolder>(

                Books.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Books model, int position) {


                viewHolder.setDetails(getApplicationContext(), model.getBookName(), model.getIsdn(), model.getStatus());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String bookName, String isdn, String status){

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_isdn = (TextView) mView.findViewById(R.id.isdn_text);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            //ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);


            user_name.setText(bookName);
            user_isdn.setText(isdn);
            user_status.setText(status);

           // Glide.with(ctx).load(userImage).into(user_image);


        }




    }

}
