package com.redrex.alejandrobedoya.shoppinglist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends Fragment implements Extractdata {
    ListView listViewProducts;
    public ProductListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //se obtiene la categoria
       // String a=getActivity().getIntent().getStringExtra("categoria");

        View rootView = inflater.inflate(R.layout.fragment_category_products, container, false);
        listViewProducts = (ListView)rootView.findViewById(R.id.result_list_view);
        starter();

         return rootView;
    }
    private void starter() {
        WebServiceProducts prods = new WebServiceProducts(this,getActivity().getIntent().getStringExtra("categoria"));
        prods.execute("","");

    }

    @Override
    public void onFetchComplete(List<Product> data) {
        listViewProducts.setAdapter(new ProductsAdaper(getActivity(), data));
    }

    @Override
    public void onFetchFailure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
