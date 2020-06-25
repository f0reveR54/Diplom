package diplom.diplom;

import android.content.Context;
import android.support.annotation.StringRes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by f0rever on 24.01.2018.
 */

public class InitText {


    static String[] question;
    static String[] id;
    static String[] picture;
    static String[] answer1;
    static String[] answer2;
    static String[] answer3;
    static String[] answer4;
    static String[] rightanswer;
    static int num;
    static int size;

    private static String readText(Context context, int example) throws IOException {//функция считывание Json файла в String текст
        InputStream is = context.getResources().openRawResource(example);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void Jopa(Context context, int resId) throws JSONException, IOException {//присваивание String текста переменным
        String jsonText = readText(context, resId);


        JSONObject jsonRoot = null;

        jsonRoot = new JSONObject(jsonText);

        JSONArray test = jsonRoot.getJSONArray("test");
        num = test.length();
        size = test.length();
        question = new String[num];
        id = new String[num];
        picture = new String[num];
        answer1 = new String[num];
        answer2 = new String[num];
        answer3 = new String[num];
        answer4 = new String[num];
        rightanswer = new String[num];


        for (int i = 0; i < num; i++) {
            JSONObject db = test.getJSONObject(i);
            JSONObject qe = test.getJSONObject(i);


            JSONObject answers = db.getJSONObject("answers");


            question[i] = qe.getString("question");
            id[i] = qe.getString("id");
            picture[i] = qe.getString("picture");
            answer1[i] = answers.getString("answer1");
            answer2[i] = answers.getString("answer2");
            answer3[i] = answers.getString("answer3");
            answer4[i] = answers.getString("answer4");
            rightanswer[i] = answers.getString("rightanswer");


        }
    }



}
