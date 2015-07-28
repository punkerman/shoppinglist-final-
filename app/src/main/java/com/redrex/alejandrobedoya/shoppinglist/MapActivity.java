package com.redrex.alejandrobedoya.shoppinglist;

import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListCursorAdapter;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListDbAdapter;
import com.redrex.alejandrobedoya.shoppinglist.BdConexion.MyListMapCursorAdapter;

import java.sql.SQLException;


public class MapActivity extends ActionBarActivity {
    private ImageButton boton1;
    private ImageButton boton2;
    private ImageButton boton3;
    private ImageButton boton4;
    private ImageButton boton5;
    private ImageButton boton6;
    private ImageButton boton7;
    private ImageButton boton8;
    private ImageButton boton9;
    private ImageButton boton10;
    private ImageButton boton11;
    private ImageButton boton12;
    private ImageButton boton13;
    private ImageButton boton14;
    private ImageButton boton15;
    private ImageButton boton16;
    private ImageButton boton17;
    private ImageButton boton18;
    private ImageButton boton19;
    private ImageButton boton20;
    private ImageButton boton21;
    private ImageButton boton22;
    private ImageButton boton23;
    private ImageButton boton24;
    private ImageButton boton25;
    private ImageButton boton26;
    private ImageButton boton27;
    private ImageButton boton28;


    MyListDbAdapter ldbAdapter;
    MyListCursorAdapter lcursor;
    MyListMapCursorAdapter mcursor;
    ListView lv;
    Cursor cursor;
    Integer S1;
    String S2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ldbAdapter = new MyListDbAdapter(this);
        int i;

        try {
            ldbAdapter.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (i = 1; i <= 28; i++) {
            S2 = "sector" + i;
            S1 = ldbAdapter.contar(S2);
            Log.d(null, String.valueOf(S1));
            if (i == 1) {
                boton1 = (ImageButton) findViewById(R.id.star1);

            }
            if (i == 2) {
                boton2 = (ImageButton) findViewById(R.id.star2);

            }
            if (i == 3) {
                boton3 = (ImageButton) findViewById(R.id.star3);
            }
            if (i == 4) {
                boton4 = (ImageButton) findViewById(R.id.star4);
            }
            if (i == 5) {
                boton5 = (ImageButton) findViewById(R.id.star5);
            }
            if (i == 6) {
                boton6 = (ImageButton) findViewById(R.id.star6);
            }
            if (i == 7) {
                boton7 = (ImageButton) findViewById(R.id.star7);
            }
            if (i == 8) {
                boton8 = (ImageButton) findViewById(R.id.star8);
            }
            if (i == 9) {
                boton9 = (ImageButton) findViewById(R.id.star9);
            }
            if (i == 10) {
                boton10 = (ImageButton) findViewById(R.id.star10);
            }
            if (i == 11) {
                boton11 = (ImageButton) findViewById(R.id.star11);
            }
            if (i == 12) {
                boton12 = (ImageButton) findViewById(R.id.star12);
            }
            if (i == 13) {
                boton13 = (ImageButton) findViewById(R.id.star13);
            }
            if (i == 14) {
                boton14 = (ImageButton) findViewById(R.id.star14);
            }
            if (i == 15) {
                boton15 = (ImageButton) findViewById(R.id.star15);
            }
            if (i == 16) {
                boton16 = (ImageButton) findViewById(R.id.star16);
            }
            if (i == 17) {
                boton17 = (ImageButton) findViewById(R.id.star17);
            }
            if (i == 18) {
                boton18 = (ImageButton) findViewById(R.id.star18);
            }
            if (i == 19) {
                boton19 = (ImageButton) findViewById(R.id.star19);
            }
            if (i == 20) {
                boton20 = (ImageButton) findViewById(R.id.star20);
            }
            if (i == 21) {
                boton21 = (ImageButton) findViewById(R.id.star21);
            }
            if (i == 22) {
                boton22 = (ImageButton) findViewById(R.id.star22);
            }
            if (i == 23) {
                boton23 = (ImageButton) findViewById(R.id.star23);
            }
            if (i == 24) {
                boton24 = (ImageButton) findViewById(R.id.star24);
            }
            if (i == 25) {
                boton25 = (ImageButton) findViewById(R.id.star25);
            }
            if (i == 26) {
                boton26 = (ImageButton) findViewById(R.id.star26);
            }
            if (i == 27) {
                boton27 = (ImageButton) findViewById(R.id.star27);
            }
            if (i == 28) {
                boton28 = (ImageButton) findViewById(R.id.star28);
            }


            if (S1 == 0) {

                if ( i == 1) {
                    boton1.setVisibility(View.GONE);
                }
                if ( i == 2) {
                    boton2.setVisibility(View.GONE);
                }
                if ( i == 3) {
                    boton3.setVisibility(View.GONE);
                }
                if (i == 4) {
                    boton4.setVisibility(View.GONE);
                }
                if (i == 5) {
                    boton5.setVisibility(View.GONE);
                }
                if (i == 6) {
                    boton6.setVisibility(View.GONE);
                }
                if (i == 7) {
                    boton7.setVisibility(View.GONE);
                }
                if (i == 8) {
                    boton8.setVisibility(View.GONE);
                }
                if (i == 9) {
                    boton9.setVisibility(View.GONE);
                }
                if (i == 10) {
                    boton10.setVisibility(View.GONE);
                }
                if (i == 11) {
                    boton11.setVisibility(View.GONE);
                }
                if (i == 12) {
                    boton12.setVisibility(View.GONE);
                }
                if (i == 13) {
                    boton13.setVisibility(View.GONE);
                }
                if (i == 14) {
                    boton14.setVisibility(View.GONE);
                }
                if (i == 15) {
                    boton15.setVisibility(View.GONE);
                }
                if (i == 16) {
                    boton16.setVisibility(View.GONE);
                }
                if (i == 17) {
                    boton17.setVisibility(View.GONE);
                }
                if (i == 18) {
                    boton18.setVisibility(View.GONE);
                }
                if (i == 19) {
                    boton19.setVisibility(View.GONE);
                }
                if (i == 20) {
                    boton20.setVisibility(View.GONE);
                }
                if (i == 21) {
                    boton21.setVisibility(View.GONE);
                }
                if (i == 22) {
                    boton22.setVisibility(View.GONE);
                }
                if (i == 23) {
                    boton23.setVisibility(View.GONE);
                }
                if (i == 24) {
                    boton24.setVisibility(View.GONE);
                }
                if (i == 25) {
                    boton25.setVisibility(View.GONE);
                }
                if (i == 26) {
                    boton26.setVisibility(View.GONE);
                }
                if (i == 27) {
                    boton27.setVisibility(View.GONE);
                }
                if (i == 28) {
                    boton28.setVisibility(View.GONE);
                }


            }

        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector1");
            }
        });


        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector2");
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector3");
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector4");
            }
        });


        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector5");
            }
        });
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector6");
            }
        });
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector7");
            }
        });


        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector8");
            }
        });
        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector9");
            }
        });
        boton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector10");
            }
        });


        boton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector11");
            }
        });
        boton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector12");
            }
        });
        boton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector13");
            }
        });


        boton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector14");
            }
        });
        boton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector15");
            }
        });
        boton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector16");
            }
        });


        boton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector17");
            }
        });
        boton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector18");
            }
        });
        boton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector19");
            }
        });


        boton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector20");
            }
        });
        boton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector21");
            }
        });
        boton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector22");
            }
        });


        boton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector23");
            }
        });
        boton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector24");
            }
        });
        boton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector25");
            }
        });


        boton26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector26");
            }
        });
        boton27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector27");
            }
        });
        boton28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopupWindow(v, "sector28");
            }
        });





    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
    private void displayPopupWindow(View anchorView,String sector){
        PopupWindow popup = new PopupWindow(MapActivity.this);
        View layout = getLayoutInflater().inflate(R.layout.popup_content,null);
        lv = (ListView) layout.findViewById(R.id.popUpList);
        ldbAdapter= new MyListDbAdapter(this);
        try {
            ldbAdapter.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            consultar2(sector);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        popup.setContentView(layout);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);


    }

    private void consultar() throws SQLException {
        cursor=ldbAdapter.getCursor();
        startManagingCursor(cursor);
        lcursor =new MyListCursorAdapter(this,cursor);
        lv.setAdapter(lcursor);

    }
    private void consultar2(String sector) throws SQLException {
        cursor=ldbAdapter.getMapProdList(sector);
        startManagingCursor(cursor);
        mcursor =new MyListMapCursorAdapter(this,cursor);
        lv.setAdapter(mcursor);
    }
}
