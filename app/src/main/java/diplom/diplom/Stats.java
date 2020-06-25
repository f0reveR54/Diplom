package diplom.diplom;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import diplom.diplom.R;

import static android.content.ContentValues.TAG;






public class Stats extends Activity {


    DBHelper dbHelper;
    int order=0;
    String order_way="ASC";

    MainMenu a = new MainMenu();

    public void onClick(){

            a.db.execSQL("INSERT INTO stats ('data','ques','rus','his','osn') " +
                    "VALUES " +
                    "(" +

                    "'"+MainMenu.strDate+"'," +
                    "'"+TemplateTest.count+"'," +
                    "'"+TemplateTest.countRuss1+"/"+TemplateTest.countRus+"'," +
                    "'"+TemplateTest.countHistory+"/"+TemplateTest.countHis+"'," +
                    "'"+TemplateTest.countBase+"/"+TemplateTest.countOsn+"');"); // Запрос для добавления таблицу записи
            //Log.d("db write","записали name: "+names[name_index]+" вес: "+String.format("%d",weight)+" рост: "+String.format("%d",height)+" возраст: "+String.format("%d",age));


    }

    public void update(int ques,String rus,String his, String osn)
    {
        ContentValues jj = new ContentValues();
        jj.put("ques",ques);
        jj.put("rus",rus);
        jj.put("his",his);
        jj.put("osn",osn);

        int jopa = a.db.update("stats",jj,"data=" + "'" + MainMenu.strDate + "'" ,null);
        Log.d("ya ne znau pizda",String.valueOf(jopa));

    }

    public void setOrder(View column)
    {
        int old_order=order;
        order=Integer.parseInt(column.getTag().toString());

        if ((old_order==order)&&(order_way=="ASC")) order_way="DESC";
        else order_way="ASC";

        refreshTable();
    }
    public TableRow createRow(int key, String data, int ques, String rus, String his, String osn)
    {
        TextView keyView = new TextView(this);
        keyView.setText(String.format("%d",key));
        keyView.setTextColor(Color.RED);

        TextView dataView = new TextView(this);
        dataView.setText(data);

        TextView quesView = new TextView(this);
        quesView.setText(String.format("%d",ques));

        TextView rusView = new TextView(this);
        rusView.setText(rus);

        TextView histView = new TextView(this);
        histView.setText(his);

        TextView osnView = new TextView(this);
        osnView.setText(osn);

        TableRow resultRow = new TableRow(this);
        Resources res = this.getResources();
        // resultRow.setBackgroundColor(res.getColor(R.color.white)); // Ставим bg color белым для нового ряда

        resultRow.addView(keyView);
        resultRow.addView(dataView); // Добавляем в ряд все textView
        resultRow.addView(quesView);
        resultRow.addView(rusView);
        resultRow.addView(histView);
        resultRow.addView(osnView);

        return resultRow;

    }

    public void nuke(){
        a.db.execSQL("DROP TABLE IF EXISTS stats");
        a.db.execSQL("CREATE TABLE stats (id integer primary key autoincrement,data text,ques integer,rus text, his text, osn text);");
        refreshTable();
    }


    public void lastDate(Context[] cnt, int vsego, int pravrus, int vsegorus, int pravhis, int vsegohis, int pravosn, int vsegoosn)
    {
        DBHelper dbHelper = new DBHelper(cnt[0]);

        a.db = dbHelper.getWritableDatabase();
        Cursor c;
        c = a.db.rawQuery ("Select * from stats ",null);
        if (c.moveToLast())
        {
            int ColIndex = c.getColumnIndex("data");
            String data = c.getString(ColIndex);
            ColIndex=c.getColumnIndex("ques");
            int ques = c.getInt(ColIndex)+vsego;

            ColIndex = c.getColumnIndex("rus");
            String rus = c.getString(ColIndex);
            String rus1 = rus.split("/")[0];
            String rus2 = rus.split("/")[1];
            int rup = pravrus + Integer.valueOf(rus1);
            int ruv = vsegorus + Integer.valueOf(rus2);
            rus = String.valueOf(rup) +"/" + String.valueOf(ruv);

            ColIndex = c.getColumnIndex("his");
            String his = c.getString(ColIndex);
            String his1 = his.split("/")[0];
            String his2 = his.split("/")[1];
            int hp = pravhis + Integer.valueOf(his1);
            int hv = vsegohis + Integer.valueOf(his2);
            his = String.valueOf(hp) +"/" + String.valueOf(hv);


            ColIndex = c.getColumnIndex("osn");
            String osn = c.getString(ColIndex);
            String osn1 = osn.split("/")[0];
            String osn2 = osn.split("/")[1];
            int op = pravosn + Integer.valueOf(osn1);
            int ov = vsegoosn + Integer.valueOf(osn2);
            osn = String.valueOf(op) +"/" + String.valueOf(ov);

            if(MainMenu.strDate.equals(data))  update(ques,rus,his,osn);
            else onClick();

        }
        else onClick();


    }

    public void refreshTable()
    {
        a.table.removeAllViewsInLayout();

        Cursor c;
        String sort_column="id";
        switch (order)
        {
            case 0: sort_column="id"; break;
            case 1: sort_column="data"; break;
            case 2: sort_column="ques"; break;
            case 3: sort_column="right"; break;
        }
        c = a.db.rawQuery ("Select * from stats ORDER BY "+sort_column+" "+order_way,null);


        if (c.moveToFirst()) { //переходим на первый элемент если он есть
            do{
                int ColIndex = c.getColumnIndex("id");
                int key = c.getInt(ColIndex);
                ColIndex = c.getColumnIndex("data");
                String data = c.getString(ColIndex);
                ColIndex=c.getColumnIndex("ques");
                int ques = c.getInt(ColIndex);
                ColIndex=c.getColumnIndex("rus");
                String rus=c.getString(ColIndex);
                ColIndex=c.getColumnIndex("his");
                String his=c.getString(ColIndex);
                ColIndex=c.getColumnIndex("osn");
                String osn=c.getString(ColIndex);


                a.table.addView(createRow(key, data,ques,rus,his,osn));
            }while(c.moveToNext()); //переходим к следующему элементу
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        a.table=(TableLayout)findViewById(R.id.table);
        dbHelper=new DBHelper(this);
        Log.d("etopizda",String.valueOf(dbHelper));
        a.db=dbHelper.getWritableDatabase(); // Открываем базу для записи

        refreshTable();
        //nuke();


    }
}
