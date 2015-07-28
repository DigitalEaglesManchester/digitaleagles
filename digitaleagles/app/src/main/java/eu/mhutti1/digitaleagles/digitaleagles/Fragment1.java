package eu.mhutti1.digitaleagles.digitaleagles;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;

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

    private final int REQ_CODE_SPEECH_INPUT = 100;
    public ImageButton b;
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
    }



    public void demoButton(){
        //demoOutput.setText("hi");
        promptSpeechInput();
    }
    public void demoClick(View view){


    }
    private void promptSpeechInput() {

        if (toggle) {
            speech = SpeechRecognizer.createSpeechRecognizer(thisActivity);
            speech.setRecognitionListener(this);
            recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, thisActivity.getPackageName());
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
            speech.startListening(recognizerIntent);
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
                    TextView t = (TextView)thisActivity.findViewById(R.id.textView);
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
        //returnedText.setText(errorMessage);
        //toggleButton.setChecked(false);
    }

    @Override
    public void onResults(Bundle results) {
        speech.startListening(recognizerIntent);
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = matches.get(0);
        Toast.makeText(thisActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        TextView t = (TextView)thisActivity.findViewById(R.id.textView);
        t.setText(text);
       // returnedText.setText(matches.get(0));
    }


    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
    public static String getErrorText(int errorCode) {
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
