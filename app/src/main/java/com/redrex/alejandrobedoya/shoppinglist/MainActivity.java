package com.redrex.alejandrobedoya.shoppinglist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void categoriesBtn(View v){
        Intent act = new Intent(this,CategoriesActivity.class);
        startActivity(act);
    }
    public void listBtn(View v){
        Intent act = new Intent(this,MyShopListActivity.class);
        startActivity(act);
    }
    public void mapBtn(View v){
        Intent act = new Intent(this,MapActivity.class);
        startActivity(act);
    }
    public void promoBtn(View v){
        Intent act = new Intent(this,PromosActivity.class);
        startActivity(act);
    }
}
