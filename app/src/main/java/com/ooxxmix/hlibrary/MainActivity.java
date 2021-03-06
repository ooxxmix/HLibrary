package com.ooxxmix.hlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.ooxxmix.hlibrary.model.BooksSearcher;
import com.ooxxmix.hlibrary.model.Parser;
import com.ooxxmix.hlibrary.model.bean.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, ChildEventListener {

    private Gson gson;
    private bookAdapter adapter;

    private HandlerThread queryThread = new HandlerThread("query");
    private Handler queryHandler;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.scan);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);

        fab.setOnClickListener(this);

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new bookAdapter();
        list.setAdapter(adapter);

        gson = new Gson();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://hlibary-cb601.firebaseio.com/");
        myRef.addChildEventListener(this);

//        mainHandler = new Handler();
        queryThread.start();
        queryHandler = new Handler(queryThread.getLooper());
        queryHandler.post(new Runnable() {
            @Override public void run () {
                Book book = null;
                try {
                    book = new Parser(new BooksSearcher()).parse("9780596520687");
                    Log.d("!!!", book.getName() + "  " + book.getImage());
                } catch (Book.ISBNFormatError isbnFormatError) {
                    Log.d("!!!", "ISBN格式錯誤");
                } catch (IOException e) {
                    Log.d("!!!", "查無資料");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        return true;
    }


    @Override
    public void onChildAdded (DataSnapshot dataSnapshot, String s) {
        Log.d("added", dataSnapshot.getValue().toString());
        adapter.addItem(gson.fromJson(dataSnapshot.getValue().toString(), Book.class));
    }

    @Override
    public void onChildChanged (DataSnapshot dataSnapshot, String s) {
        Log.d("changed", dataSnapshot.toString());
    }

    @Override
    public void onChildRemoved (DataSnapshot dataSnapshot) {
        Log.d("removed", dataSnapshot.toString());
    }

    @Override
    public void onChildMoved (DataSnapshot dataSnapshot, String s) {
        Log.d("moved", dataSnapshot.toString());
    }

    @Override
    public void onCancelled (DatabaseError databaseError) {
        Log.d("cancellid", databaseError.toString());
    }

    @Override
    public void onClick (View v) {
        startActivity(new Intent(this, ScanActivity.class));
    }

    class bookAdapter extends RecyclerView.Adapter<bookHolder> {

        private List<Book> list;

        bookAdapter () {
            list = new ArrayList<Book>(0);
        }

        public void addItem (Book b) {
            list.add(b);
            notifyDataSetChanged();
        }

        @Override public bookHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            return new bookHolder(LayoutInflater.from(MainActivity.this)
                    .inflate(android.R.layout.simple_list_item_2, parent, false));
        }

        @Override public void onBindViewHolder (bookHolder holder, int position) {
            Book item = list.get(position);
            holder.name.setText(item.getName());
            holder.isbn.setText(item.getIsbn());
        }

        @Override public int getItemCount () {
            return list.size();
        }
    }

    class bookHolder extends RecyclerView.ViewHolder {

        TextView name, isbn;

        bookHolder (View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(android.R.id.text1);
            isbn = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }

}
