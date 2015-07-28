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
    public void babyBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","bebe");
        startActivity(act);
    }
    public void cerealsBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","cereal");
        startActivity(act);
    }
    public void embBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","embutidos");
        startActivity(act);
    }
    public void enlBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","enlatados");
        startActivity(act);
    }
    public void fruitsBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","fruta");
        startActivity(act);
    }
    public void toysBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","juguete");
        startActivity(act);
    }
    public void lactBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","lacteos");
        startActivity(act);
    }
    public void cleanBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","limpieza");
        startActivity(act);
    }public void breadBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","panaderia");
        startActivity(act);
    }
    public void bakeryBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","pasteleria");
        startActivity(act);
    }
    public void vegisBtn(View v){
        Intent act= new Intent(this,CategoryProductsActivity.class);
        act.putExtra("categoria","vegetales");
        startActivity(act);
    }





}
