package eu.mhutti1.digitaleagles.digitaleagles;

import android.content.Context;
import android.content.Intent;
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
        t = (TextView)thisActivity.findViewById(R.id.textView);
        AudioManager amanager=(AudioManager)thisActivity.getSystemService(Context.AUDIO_SERVICE);
        //amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        //amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        //amanager.setStreamMute(AudioManager.STREAM_RING, true);
        //amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        list = (ListView) thisActivity.findViewById(R.id.listView);
        textList = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(thisActivity, android.R.layout.simple_list_item_1, textList);

        list.setAdapter(listAdapter);

    }

    public TextView t;

    public void demoButton(){
        //demoOutput.setText("hi");
        promptSpeechInput();
    }
    public void demoClick(View view){


    }
    private void startSpeech(){
        speech = SpeechRecognizer.createSpeechRecognizer(thisActivity);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, thisActivity.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            /*recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 5000);*/
        speech.startListening(recognizerIntent);

    }
    private void promptSpeechInput() {


        if (toggle) {
           startSpeech();

            toggle = false;
        }else
        {
            speech.destroy();
            toggle = true;
        }
        //toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            //@Override
            //public void onCheckedChanged(CompoundButton buttonView,
                                       //  boolean isChecked) {
              //  if (isChecked) {
                    //progressBar.setVisibility(View.VISIBLE);
                    //progressBar.setIndeterminate(true);

               // }/* else {
                   // progressBar.setIndeterminate(false);
                   // progressBar.setVisibility(View.INVISIBLE);
                    //speech.stopListening();
            //    }
         //   }
      //  });*/





        /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(thisActivity.getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (/*resultCode == RESULT_OK &&*/ null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //for (String word : result){

                    // demoOutput.setText(word);
                    Toast.makeText(thisActivity.getApplicationContext(), result.get(0), Toast.LENGTH_SHORT).show();

                    t.setText(result.get(0));
                    // }
                    // demoOutput.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        //progressBar.setIndeterminate(false);
        //progressBar.setMax(10);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (speech != null) {
            //speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }
    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        //progressBar.setProgress((int) rmsdB);
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
        speech.startListening(recognizerIntent);
        //returnedText.setText(errorMessage);
        //toggleButton.setChecked(false);
    }

    @Override
    public void onResults(Bundle results) {
        //add stuff
        speech.startListening(recognizerIntent);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = matches.get(0);
        //Toast.makeText(thisActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
       listAdapter.add(text.toString());
        listAdapter.notifyDataSetChanged();

        //t.setText(text);
       // returnedText.setText(matches.get(0));
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
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";

                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                speech.destroy();
                startSpeech();
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
