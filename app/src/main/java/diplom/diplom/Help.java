package diplom.diplom;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    EditText edit;
    Button search;
    TextView text;
    ScrollView sc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        search = (Button) findViewById(R.id.buttonSearch);
        edit = (EditText) findViewById(R.id.editText2);
        text = (TextView) findViewById(R.id.textViewforText);
        sc =  (ScrollView) findViewById(R.id.scroll);
        text.setText(MainMenu.text);

        search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String criteria = edit.getText().toString();
                String fullText = text.getText().toString();
                if (fullText.contains(criteria)) {
                    int indexOfCriteria = fullText.indexOf(criteria);
                    int lineNumber = text.getLayout().getLineForOffset(indexOfCriteria);
                    String highlighted = "<font color='red'>"+criteria+"</font>";
                    fullText = fullText.replace(criteria, highlighted);
                    fullText = fullText.replace("\n", "<br>");
                    text.setText(Html.fromHtml(fullText));

                    //sc.scrollTo(0, text.getLayout().getLineTop(lineNumber));
                    //sc.scrollTo(0,25);
                }
            }
        });
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    //String fullText = text.getText().toString();
                    //fullText = fullText.replace("<font color='red'>", "");
                    //fullText = fullText.replace("</font>", "");
                    text.setText(MainMenu.text);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
