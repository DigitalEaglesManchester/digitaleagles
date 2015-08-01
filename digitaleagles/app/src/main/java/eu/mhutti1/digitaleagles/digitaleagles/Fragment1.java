package eu.mhutti1.digitaleagles.digitaleagles;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Isaac on 28/07/2015.
 */
public class Fragment1 extends NavigationControl.PlaceholderFragment  implements RecognitionListener {
    private TextView returnedText;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    public Boolean toggle =true;
    public TextView demoOutput;
    public String[] textArray;
    ArrayAdapter<String> listAdapter;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public ImageButton b;
    public ListView list;
    public ArrayList<String> textList;
    public DatabaseHandler dataService;
    GetData dataHandler;
    public Boolean on = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.screen1, container, false);
        /**/

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        b=(ImageButton)thisActivity.findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View viewer) {
                demoButton();
            }
            });
        progressBar = (ProgressBar) thisActivity.findViewById(R.id.progressBar);
        t = (TextView)thisActivity.findViewById(R.id.textView);
        AudioManager amanager=(AudioManager)thisActivity.getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        list = (ListView) thisActivity.findViewById(R.id.listView);
        textList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(thisActivity, android.R.layout.simple_list_item_1, textList);

        list.setAdapter(listAdapter);
        dataService = new DatabaseHandler(thisActivity);
        SQLiteDatabase a = dataService.getWritableDatabase();
        dataService.data = a;
        //dataService.onCreate(a);
        dataHandler = new GetData();
    }

    public TextView t;

    public void demoButton(){
        promptSpeechInput();
    }

    private void startSpeech(){
        if (on) {
            speech = SpeechRecognizer.createSpeechRecognizer(thisActivity);
            speech.setRecognitionListener(this);
            recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, thisActivity.getPackageName());
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            /*
            These have been disabled by the Android Speech service and although it would be great to coniniously
             record speech without small "deaf spots" google have not supported this since jellybean.

            recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);*/
            speech.startListening(recognizerIntent);

        }

    }
    private void promptSpeechInput() {
        if (toggle) {
            listAdapter.clear();
            listAdapter.notifyDataSetChanged();
            t.setText("");
            on = true;
            startSpeech();
            b.setAlpha((float)0.3);
            toggle = false;

        }else
        {
            on = false;
            stopVoiceRecognition();
            toggle = true;
            b.setAlpha((float)1);
            String tesxt="";
            if (t.getText().toString()!=""){
                listAdapter.add(t.getText().toString());
                listAdapter.notifyDataSetChanged();
            }
            for (String text : textList){
                tesxt = tesxt + text + ";";
            }
            DBResponseBean bean = new DBResponseBean(tesxt,dataHandler.getDate(),dataHandler.getTime(),dataHandler.findLocation()[0],dataHandler.findLocation()[1]);
            dataService.addResponse(bean);
        }
    }



    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (speech != null) {
            stopVoiceRecognition();
            Log.i(LOG_TAG, "destroy");
        }

    }
    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }


    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        startSpeech();

    }

    @Override
    public void onResults(Bundle results) {


        stopVoiceRecognition();
        startSpeech();

        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = matches.get(0);
        t.setText("");
       listAdapter.add(text.toString());
        listAdapter.notifyDataSetChanged();
        DBResponseBean bean = new DBResponseBean(text,dataHandler.getDate(),dataHandler.getTime(),dataHandler.findLocation()[0],dataHandler.findLocation()[1]);

    }

    public void stopVoiceRecognition()
    {


        if (speech != null) {
            speech.stopListening();
            speech.cancel();
            speech.destroy();

            speech = null;
        }

    }
    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

       t.setText(matches.get(0));
    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
    public  String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                Toast.makeText(thisActivity.getApplicationContext(), "No Network Detected", Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                Toast.makeText(thisActivity.getApplicationContext(), "Insufficient Network Connectivity", Toast.LENGTH_SHORT).show();
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                stopVoiceRecognition();
                startSpeech();
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                stopVoiceRecognition();
                startSpeech();
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                stopVoiceRecognition();
                startSpeech();
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                stopVoiceRecognition();
                //startSpeech();
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

}
