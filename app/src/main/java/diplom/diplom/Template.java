package diplom.diplom;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by f0rever on 23.01.2018.
 */

public class Template {

        static int num=14;
        static int amount=0;
        static String[] saved;
        static String[] Datasave;
        static int inc=0;
        static int incerrors=0;
        static FileOutputStream gov;
        static JSONObject name;

        static String[] questionErr;
        static String[] idErr;
        static String[] pictureErr;
        static String[] answer1Err;
        static String[] answer2Err;
        static String[] answer3Err;
        static String[] answer4Err;
        static String[] rightanswerErr;


        static ArrayList<JSONObject> answers;
        static ArrayList<JSONObject> st;

        static JSONArray answerArray;


        static void TicketSave()
        {
            saved = new String[num];
            Datasave = new String[num];

            for (int i = 1; i<num; i++) {
                saved[i] = "bilet" + String.valueOf(i);
                Datasave[i] = "Databilet" + String.valueOf(i);
            }
        }


        public static void addErr(int id) throws JSONException, IOException {

            if (TemplateTest.errors) inc = incerrors;
            st.add(new JSONObject());
            answers.add(new JSONObject());
            Log.d("zalupa", String.valueOf(st.size()));

            if (TemplateTest.errors)
            {
                Log.d("pizda", questionErr[id]);
                st.get(incerrors).put("question",questionErr[id]);
                st.get(incerrors).put("id",idErr[id]);
                st.get(incerrors).put("picture",pictureErr[id]);
                st.get(incerrors).put("answers", answers.get(incerrors));
                answers.get(incerrors).put("answer1",answer1Err[id]);
                answers.get(incerrors).put("answer2",answer2Err[id]);
                answers.get(incerrors).put("answer3",answer3Err[id]);
                answers.get(incerrors).put("answer4",answer4Err[id]);
                answers.get(incerrors).put("rightanswer",rightanswerErr[id]);
                incerrors++;

            }
            else
            {
                if ( amount==0 || (inc>=amount && amount!=0))
                {
                    Log.d("pizda", InitText.question[id]);
                    st.get(inc).put("question",InitText.question[id]);
                    st.get(inc).put("id",InitText.id[id]);
                    st.get(inc).put("picture",InitText.picture[id]);
                    st.get(inc).put("answers", answers.get(inc));
                    answers.get(inc).put("answer1",InitText.answer1[id]);
                    answers.get(inc).put("answer2",InitText.answer2[id]);
                    answers.get(inc).put("answer3",InitText.answer3[id]);
                    answers.get(inc).put("answer4",InitText.answer4[id]);
                    answers.get(inc).put("rightanswer",InitText.rightanswer[id]);
                    inc++;
                }
                if(amount!=0 && inc<amount)
                {
                    Log.d("pizda", questionErr[id]);
                    st.get(inc).put("question",questionErr[id]);
                    st.get(inc).put("id",idErr[id]);
                    st.get(inc).put("picture",pictureErr[id]);
                    st.get(inc).put("answers", answers.get(inc));
                    answers.get(inc).put("answer1",answer1Err[id]);
                    answers.get(inc).put("answer2",answer2Err[id]);
                    answers.get(inc).put("answer3",answer3Err[id]);
                    answers.get(inc).put("answer4",answer4Err[id]);
                    answers.get(inc).put("rightanswer",rightanswerErr[id]);
                    inc++;
                }
            }

            Log.d("jopa1", "sqq");

        }

        public static void open (Context cnt) throws FileNotFoundException
        {
            gov = new FileOutputStream(new File(cnt.getExternalFilesDir(null), "errors.json"));
            name = new JSONObject() ;
            answers = new ArrayList<JSONObject>();
            st = new ArrayList<JSONObject>();

            answerArray = new JSONArray();;

            inc=0;
            incerrors=0;
        }

        public static void close() throws IOException {

            name.put("test", answerArray);

            if (TemplateTest.errors)
            {
                for (int i = 0; i<incerrors; i++) answerArray.add(st.get(i));
            }
            else
            {
                for (int i = 0; i<inc; i++)  answerArray.add(st.get(i));
            }

            amount=TemplateTest.wrong;
            gov.write(name.toJSONString().getBytes());
            gov.close();
        }

    static public void InitErrors(Context cnt) throws IOException, ParseException, JSONException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader((new File(cnt.getExternalFilesDir(null), "errors.json"))));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray msg = (JSONArray) jsonObject.get("test");

        amount = msg.size();
        questionErr = new String[amount];
        idErr = new String[amount];
        pictureErr = new String[amount];
        answer1Err = new String[amount];
        answer2Err = new String[amount];
        answer3Err = new String[amount];
        answer4Err = new String[amount];
        rightanswerErr = new String[amount];

        for (int i = 0; i < amount; i++)
            {
                JSONObject db = (JSONObject) msg.get(i);
                JSONObject qe = (JSONObject) msg.get(i);
                JSONObject qwe = (JSONObject) msg.get(i);

                JSONObject answer = (JSONObject) qe.get("answers");
                Log.d("hui", String.valueOf(amount));
                answer1Err[i] = (String) answer.get("answer1");
                answer2Err[i] = (String) answer.get("answer2");
                answer3Err[i] = (String) answer.get("answer3");
                answer4Err[i] = (String) answer.get("answer4");
                rightanswerErr[i] = (String) answer.get("rightanswer");
                questionErr[i] = (String) db.get("question");
                idErr[i] = (String) qwe.get("id");

                pictureErr[i] = (String) db.get("picture");
                Log.d("hui777", idErr[i]);
            }

    }



}
