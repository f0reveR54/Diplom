package diplom.diplom;


import android.os.AsyncTask;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class TemplateTest extends AppCompatActivity implements View.OnClickListener{

    private TextView mytext;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    Button ListQuest;
    Button help;
    private Button chekAnswer;
    private EditText answer;
    private ImageView pic;

    MyTask mt;
    IErr ierr;
    cErr cerr;
    casetask ct;

    static Chronometer timer;

    private SharedPreferences mSettings;
    private SharedPreferences mDate;
    static String storage = "Storage";
    static String datestorage = "Date";
    static String without[] = new String[73];

    static int countRuss1=0;
    static int countRuss2=0;
    static int countHistory=0;
    static int countBase=0;
    static int countRus=0;
    static int countHis=0;
    static int countOsn=0;
    static int countQues=0;
    static int next=0;
    static int count=0;
    static int right=0;
    static int wrong=0;
    static int Case=0;
    static int uns=0;
    static int rq=0;
    static int numbers=0;
    static int[] mem = new int[150];
    static int[] memb = new int[150];
    static int size=0;
    static int ticket;

    static boolean errors=false;
    static boolean unanswered=false;
    static boolean chek=false;
    static boolean choice=false;
    static boolean kostil=false;
    static ArrayList<Integer> unansweredID = new ArrayList<Integer>();
    static ArrayList<Integer> unansweredERR = new ArrayList<Integer>();

    Stats a = new Stats();
    MainMenu b = new MainMenu();

    String s1 = "Лексика и грамматика.\nЛексическая и грамматическая проверка оформления высказываний.";
    String s2 = "Чтение.\n Проверка умения чтения и понимания простых предложений которые содержатся в объявлениях, на плакатах, рекламе и т.п., понимать и извлекать основную и дополнительную информацию из небольшого текста.\nЧасть 1. Прочитайте сообщения и найдите логическое продолжение этой информации в вариантах.";
    String s3 = "Часть 2.Прочитайте объявления и выберите информацию, которая соответствует содержанию объявлений.";
    String s4 = "В каком журнале или газете можно прочитать эту информацию?";
    String s5 = "Проверка знаний по истории России как важного компонента социокультурной компетенции.";
    String s6 = "Проверка основ законодательства РФ.";




     class MyTask extends AsyncTask<Context,Void,Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            a.lastDate(contexts,countRus+countHis+countOsn,countRuss1+countRuss2,countRus,countHistory,countHis,countBase,countOsn);
            return null;
        }
    }

    class casetask extends AsyncTask<Context,Void,Void> {

        @Override
        protected Void doInBackground(Context... contexts) {

            Zero();
            wo();

            return null;
        }
    }

    class cErr extends AsyncTask<Context,Void,Void> {

        @Override
        protected Void doInBackground(Context... contexts) {

            countErrors();
            ZeroMass();
            return null;
        }
    }

    class IErr extends AsyncTask<Context,Void,Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            try {
                InitErr();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ZeroMass();

            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_test);

        findViewById(R.id.buttonAnswer1).setOnClickListener(this);//ответ1
        findViewById(R.id.buttonAnswer2).setOnClickListener(this);//ответ2
        findViewById(R.id.buttonAnswer3).setOnClickListener(this);//ответ3
        findViewById(R.id.buttonAnswer4).setOnClickListener(this);//ответ4
        findViewById(R.id.buttonNext).setOnClickListener(this);//далее
        findViewById(R.id.buttonChek).setOnClickListener(this);
        findViewById(R.id.buttonHelp).setOnClickListener(this);
        findViewById(R.id.buttonListQuest).setOnClickListener(this);
        findViewById(R.id.editText).setOnClickListener(this);

        mt = new MyTask();
        ierr = new IErr();
        cerr = new cErr();
        ct = new casetask();


        if(MainMenu.vnj) size=83;
        else size=73;



        mSettings = getSharedPreferences(storage, Context.MODE_PRIVATE);
        mDate = getSharedPreferences(datestorage, Context.MODE_PRIVATE);

        answer1 = (Button) findViewById(R.id.buttonAnswer1);
        answer2 = (Button) findViewById(R.id.buttonAnswer2);
        answer3 = (Button) findViewById(R.id.buttonAnswer3);
        answer4 = (Button) findViewById(R.id.buttonAnswer4);
        chekAnswer = (Button) findViewById(R.id.buttonChek);
        help = (Button) findViewById(R.id.buttonHelp) ;
        mytext = (TextView) findViewById(R.id.textView);
        answer = (EditText) findViewById(R.id.editText);
        ListQuest = (Button) findViewById(R.id.buttonListQuest);
        pic = (ImageView) findViewById(R.id.imageView);

        timer = (Chronometer) findViewById(R.id.chronometer2);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) try {
            init(extras);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public void Start(int num)
    {

        chek=false;

        answer.setText("");

        String proverka;
        if(errors) proverka = Template.pictureErr[num];
        else proverka = InitText.picture[num];


        if(!proverka.equals("0"))
            {
                mytext.setVisibility(View.GONE);
                pic.setVisibility(View.VISIBLE);
                InputStream ims = getClass().getResourceAsStream(proverka);
                pic.setImageDrawable(Drawable.createFromStream(ims,null));
                Log.d("zlupki",  proverka);
            }
        else
            {
                mytext.setVisibility(View.VISIBLE);
                pic.setVisibility(View.GONE);
            }

        if(MainMenu.vnj && ((num>52 && num <=57) || (num>=78 && num <83)))
            {
                chekAnswer.setVisibility(View.VISIBLE);
                answer.setVisibility(View.VISIBLE);
                answer1.setVisibility(View.GONE);
                answer2.setVisibility(View.GONE);
                answer3.setVisibility(View.GONE);
                answer4.setVisibility(View.GONE);
            }
        else
            {
                chekAnswer.setVisibility(View.GONE);
                answer.setVisibility(View.GONE);
                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                answer4.setVisibility(View.VISIBLE);

            }

        if(errors)
            {
                mytext.setText(Template.questionErr[num]);
                answer1.setText(Template.answer1Err[num]);
                answer2.setText(Template.answer2Err[num]);
                answer3.setText(Template.answer3Err[num]);
                answer4.setText(Template.answer4Err[num]);
            }
        else
            {
                mytext.setText(InitText.question[num]);
                answer1.setText(InitText.answer1[num]);
                answer2.setText(InitText.answer2[num]);
                answer3.setText(InitText.answer3[num]);
                answer4.setText(InitText.answer4[num]);
            }

        if(mem[num]==1)
            {
                ButtonStart();

                if(MainMenu.vnj && ((num>52 && num <=57) || (num>=78 && num <83))) findViewById(R.id.buttonChek).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                else findViewById(memb[num]).getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);


                ButtonEnable(false);
            }
        if(mem[num]==2)
            {
                ButtonStart();

                if(MainMenu.vnj && ((num>52 && num <=57) || (num>=78 && num <83))) findViewById(R.id.buttonChek).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                else
                    {
                        findViewById(returnTrue(num)).getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                        findViewById(memb[num]).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    }

                ButtonEnable(false);
            }
        if(mem[num]==0 || mem[num]==4)
            {
                ButtonStart();
                ButtonEnable(true);
            }
        if(mem[num]==3)
            {
                ButtonStart();

                findViewById(memb[num]).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);

                ButtonEnable(false);
            }
    }


    public void Zero()
    {
        next=0;
        right=0;
        wrong=0;
        count=0;
    }

    public void ButtonEnable(boolean tf)
    {
        answer1.setEnabled(tf);
        answer2.setEnabled(tf);
        answer3.setEnabled(tf);
        answer4.setEnabled(tf);
        chekAnswer.setEnabled(tf);
    }

    public void ButtonStart()
    {
        findViewById(R.id.buttonAnswer1).getBackground().setColorFilter(Color.parseColor("#ced0e4"), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.buttonAnswer2).getBackground().setColorFilter(Color.parseColor("#ced0e4"), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.buttonAnswer3).getBackground().setColorFilter(Color.parseColor("#ced0e4"), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.buttonAnswer4).getBackground().setColorFilter(Color.parseColor("#ced0e4"), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.buttonChek).getBackground().setColorFilter(Color.parseColor("#ced0e4"), PorterDuff.Mode.MULTIPLY);
    }


    public int returnTrue(int num)
    {
        String proverka;
        chek=false;

        if(errors) proverka = Template.rightanswerErr[num];
        else proverka = InitText.rightanswer[num];

        if (answer1.getText().toString().equals(proverka)) return R.id.buttonAnswer1;
        if (answer2.getText().toString().equals(proverka)) return R.id.buttonAnswer2;
        if (answer3.getText().toString().equals(proverka)) return R.id.buttonAnswer3;
        if (answer4.getText().toString().equals(proverka)) return R.id.buttonAnswer4;

        return 0;
    }

    public void CheckAnswer(Button b, int buttonid) throws IOException, JSONException {

        //проверка правильности ответа. ответ правильный - кнопка меняет цвет на зелёный, неправильный - красный
        String proverka;
        chek=false;

        if(errors) proverka = Template.rightanswerErr[next];
        else proverka = InitText.rightanswer[next];

        if (b.getText().toString().equals(proverka))
        {
            if(!MainMenu.exam)
                {
                    findViewById(buttonid).getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                    if(mem[next]!=4) mem[next]=1;
                    if(errors) unansweredERR.set(next, 1337);
                }
            else
                {
                    findViewById(buttonid).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                    if(mem[next]!=4) mem[next]=3;
                }
            if(!MainMenu.theme){
            if(next<25) countRuss1++;
            if(next>=25 && next<33) countRuss2++;
            if(next>=33 && next<58) countHistory++;
            if(next>=58) countBase++;}
            right++;
            chek=true;
            memb[next]=buttonid;
        }
        else
        {
            if(!MainMenu.exam)
                {
                    findViewById(buttonid).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    findViewById(returnTrue(next)).getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                     if(mem[next]!=4) mem[next]=2;
                }
            else
                {
                    findViewById(buttonid).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                    if(mem[next]!=4) mem[next]=3;
                }
            wrong++;
            chek=true;
            memb[next]=buttonid;

            if (!errors) if(reiteration(next)) Template.addErr(next);

        }
        //невозможность нажать на кнопку после ответа на вопрос
        //if(errors) Template.questionErr[next]="✔";
        //else InitText.question[next] = "✔";

        if(mem[next]!=4){
            count++;
            countQues++;
            if(!MainMenu.theme){
            if(next<33) countRus++;
            if(next>=33 && next<58) countHis++;
            if(next>=58) countOsn++;}
            }
        ButtonEnable(false);
    }


    public void ChekAnswerv2() throws IOException, JSONException {
        chek=false;
        if(answer.getText().toString().toLowerCase().equals(InitText.rightanswer[next].toLowerCase()))
            {
                findViewById(R.id.buttonChek).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                if(next<25) countRuss1++;
                if(next>=25 && next<33) countRuss2++;
                if(next>=33 && next<58) countHistory++;
                if(next>=58) countBase++;
                right++;
                chek=true;
                if(mem[next]!=4) mem[next]=1;
            }
        else
            {
                findViewById(R.id.buttonChek).getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                wrong++;
                chek = true;
                if(mem[next]!=4) mem[next]=2;

                if (!errors) if(reiteration(next)) Template.addErr(next);
        }
        ButtonEnable(false);
        if(mem[next]!=4){
            count++;
            countQues++;
            if(next<33) countRus++;
            if(next>=33 && next<58) countHis++;
            if(next>=58) countOsn++;
        }
    }



    public void Dialog(String message, String subject, Context context )
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(subject)
                .setMessage(message)
                .setIcon(R.drawable.icon)
                .setCancelable(false)
                .setNegativeButton("Продолжить",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void DialogV(Context cnt)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(cnt);
        builder.setTitle("Привет")
                .setMessage("У вас остались вопросы без ответа. Ответить на них?")
                .setCancelable(false)
                .setNegativeButton("Нет",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // нажато "Отмена"
                                TemplateTest.unanswered=false;
                                Intent intent = new Intent(TemplateTest.this, EndTest.class);
                                intent.putExtra("ACTION",1);
                                startActivity(intent);
                                finish();
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // нажато "OK"

                                TemplateTest.unanswered=true;
                                numbers=unansweredID.size();
                                count=0;
                                Intent intent = new Intent(TemplateTest.this, TemplateTest.class);
                                if(MainMenu.exam) intent.putExtra("ACTION",MainMenu.rnd);
                                else intent.putExtra("ACTION",TemplateTest.Case);
                                startActivity(intent);
                                finish();
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogListQuest(String[] spisok)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Список вопросов:")
                .setItems(spisok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Start(which);
                        next=which;

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void DialogListQuestWithout(String[] spisok)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Список вопросов:")
                .setItems(spisok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which>52) next=which+5;
                        else
                        next=which;
                        Start(next);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void DialogListQuestUnanswered()
    {
        String vopr[] = new String[unansweredID.size()];
        for(int i =0;i<unansweredID.size();i++) vopr[i] = InitText.question[unansweredID.get(i)];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Список вопросов:")
                .setItems(vopr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        next=unansweredID.get(which);
                        Start(unansweredID.get(which));


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogListQuestRandTheme()
    {
        String vopr[] = new String[numbers];
        for(int i =0;i<vopr.length;i++) vopr[i] = InitText.question[MainMenu.randques.get(i)];

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Список вопросов:")
                .setItems(vopr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        next=MainMenu.randques.get(which);
                        Start(next);


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void DialogListQuestTheme()
    {

        String vopr[] = new String[numbers];
        final int num[]= new int[numbers];
        int plus=MainMenu.start;
        for(int i = 0 ;i<numbers;i++) {
            vopr[i] = InitText.question[plus];
            num[i] = plus;
            plus++;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Список вопросов:")
                .setItems(vopr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        next=num[which];
                        Start(next);


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
                        Intent intent = new Intent(TemplateTest.this, Help.class);
                        startActivity(intent);


                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



    void saveDate() {
        Calendar cal = Calendar.getInstance();
        SharedPreferences.Editor editor = mDate.edit();
        editor.putString(Template.Datasave[Case], String.valueOf(cal.get(Calendar.DAY_OF_YEAR)));
        editor.commit();
    }

    void loadDate() {
        Tickets.textViewTicketDate[Case].setText("0 дн.");
    }

    void saveText() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(Template.saved[Case], String.valueOf(size-TemplateTest.right));
        editor.commit();
    }

    void loadText() {
        String savedText = mSettings.getString(Template.saved[Case], "");
        Tickets.textViewTicket[Case].setText(savedText);
    }

    void InitErr() throws ParseException, JSONException, IOException {

        errors=false;


        if  (Template.amount==0) Template.inc=Template.amount;
        else
            {
                Template.inc=Template.amount+1;
                Template.InitErrors(this);
            }

        Template.open(this);
        for (int i=0; i<Template.amount; i++) Template.addErr(i);
    }

    public boolean reiteration(int num)
    {
        for (int i=0; i<Template.amount; i++)
        {
            Log.d("jopka1233123123", String.valueOf(i));
            if (InitText.id[num].equals(Template.idErr[i])) return false;
        }
        return true;
    }

    public void StartTimer()
    {
        if (!unanswered)timer.setBase(SystemClock.elapsedRealtime());
        else timer.setBase(MainMenu.time);
        timer.start();
    }

    static public void StopTimer()
    {
        timer.stop();
    }

    public void ZeroMass()
    {
        for (int i=0; i<mem.length; i++)
        {
            mem[i]=0; memb[i]=0;
        }
    }

    @Override
    public void onBackPressed() {

        if(errors) try {
            writeErrors();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(!errors) mt.execute(this);

        ZeroMass();

        try {
            Template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        MainMenu.vnj=false;
        MainMenu.exam=false;
        unanswered=false;
        unansweredID.clear();
        nolik();
        finish();

    }

    static public void nolik()
    {
        countRus=0;
        countRuss1=0;
        countRuss2=0;
        countHis=0;
        countHistory=0;
        countOsn=0;
        countBase=0;

    }

    public void countErrors()
    {
        for (int i=0; i<Template.questionErr.length;i++) unansweredERR.add(i);

    }

    public void writeErrors() throws IOException, JSONException {
        for (int i = 0; i<unansweredERR.size();i++)
            {
                if(unansweredERR.get(i)!=1337) Template.addErr(unansweredERR.get(i));
            }
            Log.d("pizdaebabaya",String.valueOf(unansweredERR.size()));
    }

    public void wo()
    {
        for(int i=0; i<without.length; i++)
        {
            if(i>52) without[i]=InitText.question[i+5];
            else without[i]=InitText.question[i];

        }

    }

    public void init(Bundle extras) throws IOException, JSONException, ParseException {
        int action = extras.getInt("ACTION", -1);
        Case = action;
        kostil=false;


        TemplateTest.nolik();
        if (action >=1 && action<=13)
            {
                if(!unanswered) Dialog(s1,"Русский язык",this);
                ierr.execute();
                errors=false;
                MainMenu.rand=false;
                MainMenu.all=false;
                MainMenu.theme=false;
                StartTimer();
                if(!MainMenu.vnj && !unanswered) numbers=73;
                if(MainMenu.vnj &&!unanswered) numbers=83;

            }

        switch (action) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                if (unanswered)
                {
                    Start(unansweredID.get(0));
                    uns=0;
                    next=unansweredID.get(0);
                }
                else
                {
                    InitText.Jopa(this, ticket);
                    ct.execute(this);
                    Start(0);
                }
                break;
            case 30:
                nolik();
                MainMenu.randques.removeAll(MainMenu.randques);
                ZeroMass();
                ierr.execute();
                InitText.Jopa(this, R.raw.osnovi);
                Zero();
                if(MainMenu.all) TemplateTest.numbers=InitText.num;
                if(MainMenu.rand)
                {
                    TemplateTest.numbers=InitText.num;
                    for(int i = 0; i<numbers-1; i++) MainMenu.randques.add(i);
                    Collections.shuffle(MainMenu.randques);
                    MainMenu.start = MainMenu.randques.get(0);
                    numbers=20;

                }
                Start(MainMenu.start);
                next=MainMenu.start;
                Log.d("proverka na vshivost", InitText.question[next]);
                unansweredERR.removeAll(unansweredERR);
                count=0;
                unanswered=false;
                Template.inc=0;

                break;

            case 40:
                nolik();
                MainMenu.randques.removeAll(MainMenu.randques);
                ZeroMass();
               ierr.execute();
                InitText.Jopa(this, R.raw.history);
                Zero();
                if(MainMenu.all) TemplateTest.numbers=InitText.num;
                if(MainMenu.rand)
                {
                    TemplateTest.numbers=InitText.num;
                    for(int i = 0; i<numbers-1; i++) MainMenu.randques.add(i);
                    Collections.shuffle(MainMenu.randques);
                    MainMenu.start = MainMenu.randques.get(0);
                    numbers=20;

                }
                Start(MainMenu.start);
                next=MainMenu.start;
                Log.d("proverka na vshivost", InitText.question[next]);
                unansweredERR.removeAll(unansweredERR);
                count=0;
                unanswered=false;
                Template.inc=0;

                break;
            case 50:
                nolik();
                unansweredERR.removeAll(unansweredERR);
                cerr.execute();
                count=0;
                MainMenu.rand=false;
                MainMenu.all=false;
                MainMenu.theme=false;
                unanswered=false;
                errors=true;
                numbers=Template.amount;
                Template.open(this);
                Template.inc=0;
                Start(0);
                Zero();
                break;

        }
    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.buttonAnswer1) try {
            CheckAnswer(answer1,R.id.buttonAnswer1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(view.getId()==R.id.buttonAnswer2) try {
            CheckAnswer(answer2,R.id.buttonAnswer2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(view.getId()==R.id.buttonAnswer3) try {
            CheckAnswer(answer3,R.id.buttonAnswer3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(view.getId()==R.id.buttonAnswer4) try {
            CheckAnswer(answer4,R.id.buttonAnswer4);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(view.getId()==R.id.buttonChek)
        {
            try {
                ChekAnswerv2();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(view.getId()==R.id.buttonListQuest)
        {
            if(!MainMenu.theme &&!unanswered && !errors && MainMenu.vnj) DialogListQuest(InitText.question);
            if(!MainMenu.theme &&unanswered&& !errors) DialogListQuestUnanswered();
            if(!MainMenu.theme && !MainMenu.vnj && !errors && !unanswered) DialogListQuestWithout(without);
            if(!MainMenu.theme && errors) DialogListQuest(Template.questionErr);
            if(MainMenu.theme) {

                if(!MainMenu.all && !MainMenu.rand) DialogListQuestTheme();
                if(MainMenu.all) DialogListQuest(InitText.question);
                if(MainMenu.rand) DialogListQuestRandTheme();
            }

        }

        if(view.getId()==R.id.buttonHelp)
        {
            DialogHELP(TemplateTest.this);
        }

        if(view.getId()==R.id.buttonNext)
        {


            if(!chek && !unanswered && mem[next]==0)
            {
                unansweredID.add(next);
                Log.d("ebanaya proverka", String.valueOf(next));
                count++;
                countQues++;
                mem[next]=4;
            }

            if(!chek && unanswered && mem[next]==0)
            {
                mem[next]=4;
                count++;
            }

            if (count!=numbers)
                {

                    ButtonStart();//возвращение исходного цвета кнопок
                    ButtonEnable(true);//возвращение исходного состояния кнопок
                    if (unanswered)
                        {
                            next=unansweredID.get(uns);
                            uns++;
                        }


                    if (!MainMenu.theme && !unanswered &&!errors && !MainMenu.vnj && ((next==52) || next==77))
                    {
                        next+=5;
                    }

                    if (!MainMenu.theme && unanswered &&!errors && !MainMenu.vnj && ((next==53) || next==78))
                    {
                        next+=5;
                    }


                    if(!unanswered && !errors && !MainMenu.theme)
                    {
                        if(next==InitText.num-1) next=0;
                        else next++;
                    }

                    if(errors)
                    {
                        if(next==numbers-1) next=0;
                        else next++;
                    }
                    if(MainMenu.theme && MainMenu.rand)
                    {

                        next=MainMenu.randques.get(rq);
                            rq++;


                    }


                    if(MainMenu.theme && !MainMenu.rand)
                    {
                        if(next==MainMenu.start+numbers-1) next=MainMenu.start;
                        else next++;
                    }

                    Log.d("proverka svyzsi", String.valueOf(MainMenu.start+numbers-1));
                Log.d("proverka svyzsi", String.valueOf(count));

                Start(next);

                    if (!unanswered && !errors && !MainMenu.theme)
                    {
                        switch (next){
                            case 25: Dialog(s2,"Русский язык",this); break;
                            case 28: Dialog(s3,"Русский язык",this); break;
                            case 30: Dialog(s4,"Русский язык",this); break;
                            case 33: Dialog(s5,"История",this); break;
                            case 53: Dialog("Часть 2. 5 дополнительных вопросов, на которые нужно вписать ответ.","История",this); break;
                            case 58: Dialog(s6,"Основы законодательсва РФ",this); break;
                            case 78: Dialog("Часть 2. 5 дополнительных вопросов, на которые нужно вписать ответ.","Основы",this); break;
                        }
                    }
            }

            else
                {
                    Log.d("ebanaya proverka123", String.valueOf(count));
                    Log.d("ebanaya proverka123", String.valueOf(InitText.num));


                    Template.amount = wrong;

                    if(!chek && !unanswered && mem[next]==0)
                    {
                        unansweredID.add(next);
                        Log.d("ebanaya proverka", String.valueOf(next));
                        mem[next]=4;
                    }


                if (Case == 50)
                    {
                        try {
                            writeErrors();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mt.execute(this);
                        this.finish();
                    }
                    if (Case == 40)
                    {
                        countHis=countQues;
                        countHistory=right;
                        mt.execute(this);
                        this.finish();
                    }
                    if (Case == 30)
                    {
                        countOsn=countQues;
                        countBase=right;
                        mt.execute(this);
                        this.finish();
                    }

                    try {
                        Template.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if(Case!=50 && Case!=40 && Case!=30)
                    {
                        if(!MainMenu.exam)
                            {
                                saveText();
                                saveDate();
                                loadText();
                                loadDate();
                            }

                        if ((count - (TemplateTest.right + TemplateTest.wrong) != 0) && !unanswered)
                            {
                                DialogV(this);
                                TemplateTest.StopTimer();
                                MainMenu.time = TemplateTest.timer.getBase();
                            }
                        else
                            {
                                Intent intent = new Intent(this, EndTest.class);
                                intent.putExtra("ACTION", 1);
                                startActivity(intent);
                                unanswered=false;
                                choice=false;
                                finish();
                            }
                    }
                }
        }

    }
}
