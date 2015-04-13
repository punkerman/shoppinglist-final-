package com.redrex.alejandrobedoya.shoppinglist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class CategoriesActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
        return true;
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
    //boton de sector carnes
    public void meatBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","meat");
        startActivity(act);
    }
    //boton de sector bebidas
    public void drinksBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","drink");
        startActivity(act);
    }
    //boton de sector pastas
    public void pastaBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","pasta");
        startActivity(act);
    }
    //agregar mas categorias
}
