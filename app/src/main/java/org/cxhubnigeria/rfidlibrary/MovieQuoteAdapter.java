package org.cxhubnigeria.rfidlibrary;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MovieQuoteAdapter extends RecyclerView.Adapter<MovieQuoteAdapter.ViewHolder> {

    private List<MovieQuote> mMovieQuotes;
    private Callback mCallback;
    private DatabaseReference mdf;


    public MovieQuoteAdapter(Callback callback) {
        mCallback = callback;
        mMovieQuotes = new ArrayList<>();
        mdf = FirebaseDatabase.getInstance().getReference().child("book");
        mdf.addChildEventListener(new qChildEventListener());
    }

    class qChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            MovieQuote quote = dataSnapshot.getValue(MovieQuote.class);
            quote.setKey(dataSnapshot.getKey());
            mMovieQuotes.add(0, quote);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            notifyDataSetChanged();

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

            String key = dataSnapshot.getKey();
            for (MovieQuote mq: mMovieQuotes){

                if (key.equals(mq.getKey())){
                    mMovieQuotes.remove(mq);
                    notifyDataSetChanged();
                    break;
                }
            }

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

            Log.e("MQ","On Cancelled: " + databaseError.getMessage());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_quote_row_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MovieQuote movieQuote = mMovieQuotes.get(position);
        holder.bookNameTextView.setText(movieQuote.getBookName());
        holder.bookInfoTextView.setText(movieQuote.getBookInfo());
        holder.bookIsdnTextView.setText(movieQuote.getIsdn());
        //holder.statusTextView.setText(movieQuote.getRfidTag());
       holder.statusTextView.setText(movieQuote.getStatus());
       // holder.mMovieTextView.setText(movieQuote.getTimestamp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onEdit(movieQuote);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
              // remove(mMovieQuotes.get(position));
                return true;
            }
        });
    }

    public void remove(MovieQuote movieQuote) {
        //TODO: Remove the next line(s) and use Firebase instead
       // mdf.child(movieQuote.getKey()).removeValue();
       // mMovieQuotes.remove(movieQuote);
       // notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mMovieQuotes.size();
    }

    public void add(MovieQuote movieQuote) {
        //TODO: Remove the next line(s) and use Firebase instead

        mdf.push().setValue(movieQuote);
        //mdf.push().child("status").setValue("PENDING");

       // mMovieQuotes.add(0, movieQuote);
        //notifyDataSetChanged();
    }

    public void update(MovieQuote movieQuote,String newbookName, String newbookInfo, String newisdn,String newposition, String newstatus, String newtimestamp) {
        //TODO: Remove the next line(s) and use Firebase instead
        movieQuote.setBookName(newbookName);
        movieQuote.setBookInfo(newbookInfo);
        movieQuote.setIsdn(newisdn);
        //movieQuote.setRfidTag(newrfidTag);
        movieQuote.setPosition(newposition);

        movieQuote.setStatus(newstatus);
        movieQuote.setTimestamp(newtimestamp);
        //notifyDataSetChanged();
        mdf.child(movieQuote.getKey()).setValue(movieQuote);
    }

    public interface Callback {
        public void onEdit(MovieQuote movieQuote);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bookNameTextView;
        private TextView bookInfoTextView;
        private TextView bookIsdnTextView;
        private TextView bookPositionView;
        private TextView statusTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            bookNameTextView = (TextView) itemView.findViewById(R.id.bName_text);
            bookInfoTextView = (TextView) itemView.findViewById(R.id.bInfo_text);
            bookIsdnTextView = (TextView) itemView.findViewById(R.id.bIsdn);
            bookPositionView = (TextView) itemView.findViewById(R.id.bPosition);
            statusTextView = (TextView) itemView.findViewById(R.id.bStatus);
        }
    }


}
