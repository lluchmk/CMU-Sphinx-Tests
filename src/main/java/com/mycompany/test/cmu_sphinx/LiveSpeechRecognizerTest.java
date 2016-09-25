package com.mycompany.test.cmu_sphinx;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

import java.io.IOException;

/**
 *
 * @author Kevin
 */
public class LiveSpeechRecognizerTest {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();

        //Set path to acoustic model
        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        //Set path to directory
        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        //Set Language model
        configuration
                .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        try {
            LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
            recognizer.startRecognition(true);
            System.out.println("---------- R STARTED ----------");
            while (true) {
                String utterance = recognizer.getResult().getHypothesis();
                System.out.println("Hypothisis: " + utterance);
                if (utterance.equals("finish")) {
                    System.out.println("I should stop");
                    break;
                }
            }
            recognizer.stopRecognition();
            System.out.println("---------- R ENDED ----------");
        } catch (IOException e) {
            System.out.println("Recognizer configuration error: " + e.getMessage());
        }
    }
}
