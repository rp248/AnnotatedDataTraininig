package com.brat.trainer.namefinder;

import com.brat.trainer.AbstractTrainer;
import opennlp.tools.cmdline.TerminateToolException;
import opennlp.tools.formats.brat.AnnotationConfiguration;
import opennlp.tools.formats.brat.BratDocumentStream;
import opennlp.tools.namefind.*;
import opennlp.tools.sentdetect.NewlineSentenceDetector;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;

import java.io.*;
import java.util.Collections;
/**
 * Created by root on 27/7/17.
 */
public class BratNamedEntityTrainer extends AbstractTrainer {
    private AnnotationConfiguration configuration;
    private String configURL;
    private File dataDirectory;


    public void setConfigurationURL(String configURL) {
        this.configURL = configURL;
    }

    public void setDataDirectory(File file) {
        this.dataDirectory = file;
    }
    @Override
    public void train() {
        if (configURL != null) {
            ObjectStream<NameSample> objectStream = createObjectSampleStream();


            TrainingParameters parameters = TrainingParameters.defaultParams();
//            TrainingParameters parameters = new TrainingParameters();
//            parameters.put(TrainingParameters.ITERATIONS_PARAM, "100");
            parameters.put(TrainingParameters.CUTOFF_PARAM, "1");
//            parameters.put(TrainingParameters.TRAINER_TYPE_PARAM, "brat");

            try {

                TokenNameFinderModel tokenNameFinderModel = NameFinderME.train("en", "brat",
                        objectStream, parameters, TokenNameFinderFactory.create(null, null, Collections.emptyMap(),
                                new BioCodec()));

                File outFile = new File("ner-call-dail-model.bin");

                FileOutputStream outputStream = new FileOutputStream(outFile);
                tokenNameFinderModel.serialize(outputStream);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    objectStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            System.out.println("Model creating failed");
        }
    }

    private ObjectStream<NameSample> createObjectSampleStream() {
        FileInputStream configIn = null;
        SentenceDetector sentenceDetector = null;
        Tokenizer tokenizer = null;
        BratDocumentStream samples = null;

        try {
            configIn = new FileInputStream(this.configURL);
            configuration = AnnotationConfiguration.parse(configIn);
            samples = createBratDocumentStream();
            sentenceDetector = new NewlineSentenceDetector();
            tokenizer = WhitespaceTokenizer.INSTANCE;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            closeInputStream(configIn);
        }

        return new CustomBratNameSampleStream(sentenceDetector, tokenizer, samples);
    }

    private BratDocumentStream createBratDocumentStream() {
        BratDocumentStream samples = null;
        try {
            samples = new BratDocumentStream(configuration, dataDirectory, true, null);
        } catch (IOException var19) {
            throw new TerminateToolException(-1, var19.getMessage());
        }
        return samples;
    }

    private void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
