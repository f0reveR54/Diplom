package diplom.diplom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class Tickets extends AppCompatActivity implements View.OnClickListener {

    static TextView textViewTicket[] = new TextView[Template.num];
    static TextView textViewTicketDate[] = new TextView[Template.num];
    String savedText[] = new String[Template.num];
    String savedDate[] = new String[Template.num];

    int data;

    private SharedPreferences mSettings;
    private SharedPreferences mDate;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        getWindow().setBackgroundDrawable(null);

        mSettings = getSharedPreferences(TemplateTest.storage, Context.MODE_PRIVATE);
        mDate = getSharedPreferences(TemplateTest.datestorage, Context.MODE_PRIVATE);

        for (int i = 1; i<Template.num; i++)
            {
                savedText[i] = mSettings.getString(Template.saved[i], "");
                //Log.d("mda",savedDate[i]);
                savedDate[i] = mDate.getString(Template.Datasave[i], "");
            }


        findViewById(R.id.buttonTicket1).setOnClickListener(this);//,bilet 1
        textViewTicket[1] = (TextView) findViewById(R.id.textViewTicket1);
        textViewTicketDate[1] = (TextView) findViewById(R.id.dataView1);
        findViewById(R.id.buttonTicket2).setOnClickListener(this);//,bilet 2
        textViewTicket[2] = (TextView) findViewById(R.id.textViewTicket2);
        textViewTicketDate[2] = (TextView) findViewById(R.id.dataView2);
        findViewById(R.id.buttonTicket3).setOnClickListener(this);//,bilet 3
        textViewTicket[3] = (TextView) findViewById(R.id.textViewTicket3);
        textViewTicketDate[3] = (TextView) findViewById(R.id.dataView3);
        findViewById(R.id.buttonTicket4).setOnClickListener(this);//,bilet 4
        textViewTicket[4] = (TextView) findViewById(R.id.textViewTicket4);
        textViewTicketDate[4] = (TextView) findViewById(R.id.dataView4);
        findViewById(R.id.buttonTicket5).setOnClickListener(this);//,bilet 5
        textViewTicket[5] = (TextView) findViewById(R.id.textViewTicket5);
        textViewTicketDate[5] = (TextView) findViewById(R.id.dataView5);
        findViewById(R.id.buttonTicket6).setOnClickListener(this);//,bilet 6
        textViewTicket[6] = (TextView) findViewById(R.id.textViewTicket6);
        textViewTicketDate[6] = (TextView) findViewById(R.id.dataView6);
        findViewById(R.id.buttonTicket7).setOnClickListener(this);//,bilet 7
        textViewTicket[7] = (TextView) findViewById(R.id.textViewTicket7);
        textViewTicketDate[7] = (TextView) findViewById(R.id.dataView7);
        findViewById(R.id.buttonTicket8).setOnClickListener(this);//,bilet 8
        textViewTicket[8] = (TextView) findViewById(R.id.textViewTicket8);
        textViewTicketDate[8] = (TextView) findViewById(R.id.dataView8);
        findViewById(R.id.buttonTicket9).setOnClickListener(this);//,bilet 9
        textViewTicket[9] = (TextView) findViewById(R.id.textViewTicket9);
        textViewTicketDate[9] = (TextView) findViewById(R.id.dataView9);
        findViewById(R.id.buttonTicket10).setOnClickListener(this);//,bilet 10
        textViewTicket[10] = (TextView) findViewById(R.id.textViewTicket10);
        textViewTicketDate[10] = (TextView) findViewById(R.id.dataView10);
        findViewById(R.id.buttonTicket11).setOnClickListener(this);//,bilet 11
        textViewTicket[11] = (TextView) findViewById(R.id.textViewTicket11);
        textViewTicketDate[11] = (TextView) findViewById(R.id.dataView11);
        findViewById(R.id.buttonTicket12).setOnClickListener(this);//,bilet 12
        textViewTicket[12] = (TextView) findViewById(R.id.textViewTicket12);
        textViewTicketDate[12] = (TextView) findViewById(R.id.dataView12);
        findViewById(R.id.buttonTicket13).setOnClickListener(this);//,bilet 13
        textViewTicket[13] = (TextView) findViewById(R.id.textViewTicket13);
        textViewTicketDate[13] = (TextView) findViewById(R.id.dataView13);

        for (int i = 1; i<Template.num; i++)
            {
                if (savedText[i]!="")
                    {
                        if (Integer.valueOf(savedText[i])<=5) textViewTicket[i].setBackgroundResource(R.drawable.zeleny);
                        if (Integer.valueOf(savedText[i])>5 && Integer.valueOf(savedText[i])<=10) textViewTicket[i].setBackgroundResource(R.drawable.zhelty);
                        if (Integer.valueOf(savedText[i])>10) textViewTicket[i].setBackgroundResource(R.drawable.krasny);
                    }
                else textViewTicket[i].setBackgroundResource(R.drawable.bely);
            }

        for (int i = 1; i<Template.num; i++)
            {
                if (savedText[i]!="") textViewTicket[i].setText(savedText[i]);
                if (savedDate[i]!="")
                    {
                        Calendar cal = Calendar.getInstance();
                        data = (cal.get(Calendar.DAY_OF_YEAR) - Integer.valueOf(savedDate[i]));
                        Log.d("jopa1",String.valueOf(data));
                        Log.d("jopa",String.valueOf(i));
                        textViewTicketDate[i].setText(String.valueOf(data) + " дн.");
                    }
            }

    }


    public  void Start(int CASE)
    {
        TemplateTest.ticket=MainMenu.idtickets[CASE];
        Intent intent = new Intent(Tickets.this, TemplateTest.class);
        intent.putExtra("ACTION",CASE);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.buttonTicket1) Start(1);
        if(view.getId() == R.id.buttonTicket2) Start(2);
        if(view.getId() == R.id.buttonTicket3) Start(3);
        if(view.getId() == R.id.buttonTicket4) Start(4);
        if(view.getId() == R.id.buttonTicket5) Start(5);
        if(view.getId() == R.id.buttonTicket6) Start(6);
        if(view.getId() == R.id.buttonTicket7) Start(7);
        if(view.getId() == R.id.buttonTicket8) Start(8);
        if(view.getId() == R.id.buttonTicket9) Start(9);
        if(view.getId() == R.id.buttonTicket10) Start(10);
        if(view.getId() == R.id.buttonTicket11) Start(11);
        if(view.getId() == R.id.buttonTicket12) Start(12);
        if(view.getId() == R.id.buttonTicket13) Start(13);
    }


}
