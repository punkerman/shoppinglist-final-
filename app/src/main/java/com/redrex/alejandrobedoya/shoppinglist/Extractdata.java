package com.redrex.alejandrobedoya.shoppinglist;

import java.util.List;

/**
 * Created by alejandro on 4/11/2015.
 */
public interface Extractdata {
    public void onFetchComplete(List<Product> data);
    public void onFetchFailure(String msg);
}

