package pl.chom.lab10;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert toolbar != null;
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_refresh:
                        refreshItems();
                        break;
                }

                return true;
            }
        });

        itemList = (ListView) findViewById(R.id.listView);

        refreshItems();
    }

    private void refreshItems() {
        class HashMapAdapter extends BaseAdapter {

            private final Map<Integer, String> map;
            private final Integer[] keys;

            public HashMapAdapter(Map<Integer, String> map) {
                this.map = map;
                this.keys = map.keySet().toArray(new Integer[map.size()]);
            }

            @Override
            public int getCount() {
                return map.size();
            }

            @Override
            public String getItem(int position) {
                return map.get(keys[position]);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                final int id = keys[position];
                final String value = map.get(id);

                CardView view = new CardView(getApplicationContext());

                TextView text = new TextView(getApplicationContext());
                text.setText("(" + id + ") " + value);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });

                view.addView(text);
                return view;
            }
        }

        WebService service = new WebService();
        HashMapAdapter adapter = new HashMapAdapter(service.getItems());
        itemList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
