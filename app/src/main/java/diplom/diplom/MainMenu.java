package diplom.diplom;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "studentDB", null, 1); //myDB – имя базы данных
    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE stats (id integer primary key autoincrement,data text,ques integer,rus text, his text, osn text);");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS stats"); //перезапишем старую таблицу
        db.execSQL("CREATE TABLE stats ("
                + "id integer primary key autoincrement,"
                + "data text,"
                + "ques integer,"
                + "rus text,"
                + "his text,"
                + "osn text"+");");
    }
}

public class MainMenu extends AppCompatActivity implements View.OnClickListener {


    int chs=0;
    static int rnd;
    SQLiteDatabase db;
    DBHelper dbHelper;
    TableLayout table;
    static long time;
    static boolean exam=false;
    static boolean vnj=false;
    static boolean pat=false;
    static boolean rvp=false;
    static int text;
    static int[] idtickets = new int[14];
    static int start=0;
    static boolean theme=false;
    static boolean rand=false;
    static boolean all=false;

    static ArrayList<Integer> randques = new ArrayList<>();
    static Calendar c = Calendar.getInstance();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static String strDate = sdf.format(c.getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Template.TicketSave();

        for(int i = 0; i<14; i++) idtickets[i] = R.raw.bilet1;


        findViewById(R.id.buttonStats).setOnClickListener(this);
        findViewById(R.id.buttonExam).setOnClickListener(this);
        findViewById(R.id.buttonRuss).setOnClickListener(this);//тест по русскому
        findViewById(R.id.buttonHistory).setOnClickListener(this);//тест по истории
        findViewById(R.id.buttonSocial).setOnClickListener(this);//тест по основам
        findViewById(R.id.buttonInformation).setOnClickListener(this);//вывод информации
        findViewById(R.id.buttonTickets).setOnClickListener(this);//билеты
        findViewById(R.id.buttonExit).setOnClickListener(this);//выход
        findViewById(R.id.buttonErrors).setOnClickListener(this);//ошибки



    }




    public void DialogComm()
    {
        String[] vibor = {"Все вопросы", "Вопросы по теме", "20 случайных вопросов"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите вариант:")
                .setItems(vibor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==1)
                        {
                            theme=true;
                            if(chs==1) DialogRuss();
                            if(chs==2) DialogHistory();
                            if(chs==3) DialogOsn();
                        }

                        if (which==0)
                        {
                            theme=true;
                            all=true;
                            if(chs==3)
                            {

                                Intent intent = new Intent(MainMenu.this,TemplateTest.class);
                                intent.putExtra("ACTION", 30);
                                startActivity(intent);

                            }

                            if(chs==2)
                            {

                                Intent intent = new Intent(MainMenu.this,TemplateTest.class);
                                intent.putExtra("ACTION", 40);
                                startActivity(intent);

                            }

                        }
                        if (which==2)
                        {
                            theme=true;
                            rand=true;
                            if(chs==3)
                            {

                                Intent intent = new Intent(MainMenu.this,TemplateTest.class);
                                intent.putExtra("ACTION", 30);
                                startActivity(intent);

                            }
                            if(chs==2)
                            {

                                Intent intent = new Intent(MainMenu.this,TemplateTest.class);
                                intent.putExtra("ACTION", 40);
                                startActivity(intent);

                            }

                        }




                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogHistory()
    {
        String[] vibor = {"Тема 1. Древняя Русь (IХ – ХIII века)", "Тема 2.Московское государство(ХIV – ХVII вв.)", "Тема 3. Россия в ХVIII веке",
                "Тема 4. Россия в ХIХ веке", "Тема 5. Российская империя в начале ХХ века ","Тема 6. История СССР до Великой Отечественной войны",
        "Тема 7. СССР в годы Великой Отечественной  войны (1941 – 1945 годы)","Тема 8. СССР в  послевоенный период (1945 – 1991 годы)  "
        ,"Тема 9. Реформы в Российской Федерации  в 1991-1999 годах.","Россия в ХХI веке","Блок культурологических вопросов (Современные праздники России)"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите тему:")
                .setItems(vibor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                start=0;
                                TemplateTest.numbers=8 - start;
                                break;
                            case 1:
                                start=8;
                                TemplateTest.numbers=18 - start;
                                break;
                            case 2:
                                start=18;
                                TemplateTest.numbers=26 - start;
                                break;
                            case 3:
                                start=26;
                                TemplateTest.numbers=39 - start;
                                break;
                            case 4:
                                start=39;
                                TemplateTest.numbers=46 - start;
                                break;
                            case 5:
                                start=46;
                                TemplateTest.numbers=54 - start;
                                break;
                            case 6:
                                start=54;
                                TemplateTest.numbers=61 - start;
                                break;
                            case 7:
                                start=61;
                                TemplateTest.numbers=72 - start;
                                break;
                            case 8:
                                start=72;
                                TemplateTest.numbers=79 - start;
                                break;
                            case 9:
                                start=79;
                                TemplateTest.numbers=84 - start;
                                break;
                            case 10:
                                start=84;
                                TemplateTest.numbers=90 - start;
                                break;

                        }
                        Intent intent = new Intent(MainMenu.this,TemplateTest.class);
                        intent.putExtra("ACTION", 40);
                        startActivity(intent);


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogExam(final Context cnt)
    {
        String[] vibor = {"Экзамен для получения патента на трудовую деятельность","Экзамен для получения РВП","Экзамен для получения ВНЖ"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите экзамен:")
                .setItems(vibor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==2)
                            {
                                vnj=true;
                            }
                        if(which==0)
                        {
                            pat=true;
                        }
                        if(which==1)
                        {
                            rvp=true;
                        }
                        try {
                            Template.InitErrors(cnt);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        exam=true;
                        Random random = new Random();
                        rnd = 1 + random.nextInt(14 - 1);

                        TemplateTest.ticket=idtickets[rnd];
                        Intent intent = new Intent(MainMenu.this, TemplateTest.class);
                        intent.putExtra("ACTION", rnd);
                        startActivity(intent);


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogRuss()
    {
        String[] vibor = {"здесь", "будут", "темы","по", "русскому"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите тему:")
                .setItems(vibor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogHELP(final Context cnt)
    {
        String[] vibor = {"Основы законодательства РФ: необходимые для запоминания сроки", "Деятели науки и культуры, общественные деятели России", "Государственные и военные деятели России","Праздники современной России",
                "Хронология событий, обязательных для запоминания","Словарь сложной терминологии"};
        AlertDialog.Builder builder = new AlertDialog.Builder(cnt);
        builder.setTitle("Выберите тему:")
                .setItems(vibor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: MainMenu.text = R.string.osnovi; break;
                            case 1: MainMenu.text = R.string.dnkor; break;
                            case 2: MainMenu.text = R.string.gvdr; break;
                            case 3: MainMenu.text = R.string.psr; break;
                            case 4: MainMenu.text = R.string.hsoz; break;
                            case 5: MainMenu.text = R.string.slovar; break;
                        }
                        Intent intent = new Intent(MainMenu.this, Help.class);
                        startActivity(intent);


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogOsn()
    {
        String[] vibor = {"Тема 1. Государственная символика РФ", "Тема 2. Конституционный строй Российской Федерации", "Тема 3. Въезд в Россию и выезд из России, пребывание и проживание иностранных граждан в РФ",
                "Темы 4 и 5. Права человека в РФ", "Тема 6. Трудовая деятельность иностранных граждан в РФ", "Тема 7. Основы гражданского права РФ",
                "Тема 8. Основы семейного права РФ","Тема 9. Обязанности и ответственность иностранных граждан в РФ","Тема 10. Взаимоотношения иностранных граждан с Федеральной миграционной службой РФ",
        "Тема 11. Взаимоотношения иностранных граждан с другими органами государственной власти РФ","Тема 12. Взаимодействие иностранных граждан с консульскими учреждениями государства своего гражданства"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите вариант:")
                .setItems(vibor, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                start=0;
                                TemplateTest.numbers=2 - start;
                                break;
                            case 1:
                                start=2;
                                TemplateTest.numbers=6 - start;
                                break;
                            case 2:
                                start=6;
                                TemplateTest.numbers=14 - start;
                                break;
                            case 3:
                                start=14;
                                TemplateTest.numbers=16 - start;
                                break;
                            case 4:
                                start=16;
                                TemplateTest.numbers=23 - start;
                                break;
                            case 5:
                                start=23;
                                TemplateTest.numbers=32 - start;
                                break;
                            case 6:
                                start=32;
                                TemplateTest.numbers=38 - start;
                                break;
                            case 7:
                                start=38;
                                TemplateTest.numbers=46 - start;
                                break;
                            case 8:
                                start=46;
                                TemplateTest.numbers=55 - start;
                                break;
                            case 9:
                                start=55;
                                TemplateTest.numbers=58 - start;
                                break;
                            case 10:
                                start=58;
                                TemplateTest.numbers=66 - start;
                                break;
                            case 11:
                                start=66;
                                TemplateTest.numbers=69 - start;
                                break;

                        }
                        Intent intent = new Intent(MainMenu.this,TemplateTest.class);
                        intent.putExtra("ACTION", 30);
                        startActivity(intent);



                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public  void onClick(View view)
    {
        if(view.getId() == R.id.buttonRuss)
        {
            chs=1;
            DialogComm();

        }

        if(view.getId() == R.id.buttonHistory)
        {
            chs=2;
            DialogComm();
        }

        if(view.getId() == R.id.buttonSocial)
        {
            chs=3;
            DialogComm();
        }

        if(view.getId() == R.id.buttonExam)
        {
            DialogExam(this);
        }

        if(view.getId() == R.id.buttonErrors)
        {
            TemplateTest a = new TemplateTest();
            try {
                Template.InitErrors(this);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (Template.amount==0) a.Dialog("Ошибок нет","Инормация",this);
            else {
                Intent intent = new Intent(MainMenu.this, TemplateTest.class);
                intent.putExtra("ACTION", 50);
                startActivity(intent);
            }
        }

        if(view.getId() == R.id.buttonTickets)
        {

           try {
                Template.InitErrors(this);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MainMenu.this, Tickets.class);
            startActivity(intent);
        }

        if(view.getId()== R.id.buttonInformation)
        {
            DialogHELP(MainMenu.this);

        }

        if(view.getId() == R.id.buttonExit)
        {
            this.finish();
        }

        if(view.getId() == R.id.buttonStats)
        {
            Intent intent = new Intent(MainMenu.this, Stats.class);
            startActivity(intent);
        }


    }


}
