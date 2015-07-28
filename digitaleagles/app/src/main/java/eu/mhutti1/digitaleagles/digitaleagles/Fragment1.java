package eu.mhutti1.digitaleagles.digitaleagles;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Isaac on 28/07/2015.
 */
public class Fragment1 extends NavigationControl.PlaceholderFragment  implements RecognitionListener {

    public TextView demoOutput;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    public Button b;
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
        b=(Button)thisActivity.findViewById(R.id.button);
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

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
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
        }
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

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
