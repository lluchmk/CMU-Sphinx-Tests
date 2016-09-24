package com.mycompany.test.cmu_sphinx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;
import java.io.IOException;

public class TranscriberDemo {

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
            StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
                    configuration);
            InputStream stream = new FileInputStream(new File("test.wav"));

            recognizer.startRecognition(stream);
            System.out.println("---------------------- RESULT ----------------------");
            SpeechResult result;
            while ((result = recognizer.getResult()) != null) {
                System.out.format("Hypothesis: %s\n", result.getHypothesis());
                System.out.println("List of recognized words and their times:");
                for (WordResult r : result.getWords()) {
                    System.out.println(r);
                }

                System.out.println("Best 3 hypothesis:");
                for (String s : result.getNbest(3)) {
                    System.out.println(s);
                }
            }
            System.out.println("------------------- END OF RESULT -------------------");

            recognizer.stopRecognition();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
