package diplom.diplom;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.Random;


public class EndTest extends AppCompatActivity implements View.OnClickListener {

    private TextView right;
    private TextView His;
    private TextView ignor;
    private TextView wrong;
    private TextView timer;
    private TextView result;
    String s = "Экзамен не сдан по дисциплинам: ";
    int porog;
    int gg=0;
    static int exam=0;
    static int col=0;

    Stats a = new Stats();

    MyTask mt;

    class MyTask extends AsyncTask<Context,Void,Void> {

        @Override
        protected Void doInBackground(Context... contexts) {
            a.lastDate(contexts,col,TemplateTest.countRuss1+TemplateTest.countRuss2,33,TemplateTest.countHistory,exam,TemplateTest.countBase,exam);
            return null;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_test);

        mt = new MyTask();



        if(!MainMenu.vnj) {exam=20; col=73;}
        else {exam=25; col=83;}
        mt.execute(this);
        porog=10;
        if(MainMenu.pat) porog=5;
        if(MainMenu.rvp) porog=10;
        if(MainMenu.vnj) porog=15;


        MainMenu.exam=false;
        MainMenu.vnj=false;
        MainMenu.pat=false;
        MainMenu.rvp=false;


        TemplateTest.unansweredID.removeAll(TemplateTest.unansweredID);
        TemplateTest.StopTimer();


        findViewById(R.id.buttonRepeat).setOnClickListener(this);
        findViewById(R.id.buttonMenu).setOnClickListener(this);

        right = (TextView) findViewById(R.id.textView2);
        ignor = (TextView) findViewById(R.id.textView3);
        wrong = (TextView) findViewById(R.id.textView4);
        His = (TextView) findViewById(R.id.textViewHis);
        timer = (TextView) findViewById(R.id.textView5);
        result = (TextView) findViewById(R.id.textViewResult);


        Bundle extras = getIntent().getExtras();
        if (extras!=null) try {
            init(extras);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    @Override
    public void onClick(View view)
    {
        if (view.getId()==R.id.buttonRepeat)//кнопка повтора
        {

            Random random = new Random();
            int num = 1 + random.nextInt(14 - 1);

            Intent intent = new Intent(EndTest.this, TemplateTest.class);
            if(MainMenu.exam) intent.putExtra("ACTION",num);
            else intent.putExtra("ACTION",TemplateTest.Case);
            startActivity(intent);
            finish();

        }
        if (view.getId()==R.id.buttonMenu)//возврат в меню
        {
            this.finish();
        }


    }


    public void init(Bundle extras) throws IOException, JSONException {
        int action = extras.getInt("ACTION", -1);
        switch (action) {
            case 1:

                if(TemplateTest.countHistory<porog) {s=s+"История "; gg++;}
                if(TemplateTest.countBase<porog) {s=s+"Основы "; gg++;}
                if(TemplateTest.countRuss1<porog+7) {s=s+"Русский язык. Лексика и Грамматика "; gg++;}
                if(TemplateTest.countRuss1<5) {s=s+"Русский язык. Чтение "; gg++;}
                if(gg==0) s="Экзамен сдан!";


                right.setText("Русский язык. Лексика и Грамматика - " + String.valueOf(TemplateTest.countRuss1) + "/25");
                ignor.setText("Русский язык. Чтение - " + String.valueOf(TemplateTest.countRuss2) + "/8");
                wrong.setText("История - " + String.valueOf(TemplateTest.countHistory) +"/"+String.valueOf(exam));
                His.setText("Основы - " + String.valueOf(TemplateTest.countBase) +"/"+String.valueOf(exam));
                timer.setText("Время выполнения: " + TemplateTest.timer.getText());
                result.setText(s);
                break;
        }
    }
}
