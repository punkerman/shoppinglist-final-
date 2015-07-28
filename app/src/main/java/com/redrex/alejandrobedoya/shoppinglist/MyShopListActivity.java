package com.redrex.alejandrobedoya.shoppinglist;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListContract;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListCursorAdapter;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListDbAdapter;

import java.sql.SQLException;


public class MyShopListActivity extends ActionBarActivity {
    MyListDbAdapter ldbAdapter;
    SwipeMenuListView lv;
    MyListCursorAdapter lcursor;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop_list);
        lv= (SwipeMenuListView) findViewById(R.id.listView);
        ldbAdapter =new MyListDbAdapter(this);

        try {
            ldbAdapter.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            consultar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {


                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(95));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        lv.setMenuCreator(creator);
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open

                     //  Log.i(null, String.valueOf(lcursor.getCursor().getLong(Integer.parseInt(MyListContract.ResultEntry._ID))));
                       Cursor c = (Cursor) lv.getItemAtPosition(position);
                       int c2 = c.getInt(c.getColumnIndex("_id"));

                        ldbAdapter.borrar(c2);
                        Intent act = new Intent(getApplicationContext(),MyShopListActivity.class);
                        startActivity(act);
                        break;
                    case 1:
                        // delete

                       // Log.d(null, String.valueOf(position));

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


    }

    private void consultar() throws SQLException {
        cursor=ldbAdapter.getCursor();
        startManagingCursor(cursor);
        lcursor =new MyListCursorAdapter(this,cursor);
        lv.setAdapter(lcursor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_shop_list, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
