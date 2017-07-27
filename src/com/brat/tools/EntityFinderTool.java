package com.brat.tools;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by root on 26/7/17.
 */
public class EntityFinderTool {
    public EntityFinderTool() {

    }

    public void find(String sentence) throws IOException {

        File file = new File("/home/rajeshp/Documents/OpenNLP/IntelliJIdea_Opennlp_Workspace/ner-call-dail-model.bin");

        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(file);
        NameFinderME nameFinderME = new NameFinderME(tokenNameFinderModel);

        Tokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        String tokens[] = tokenizer.tokenize(sentence);
        Span spans[] = nameFinderME.find(tokens);
        NameSample nameSample = new NameSample(tokens, spans, true);
        System.out.println(nameSample.toString());
        for (int i = 0;i<spans.length; i++) {
            System.out.println(spans[i].getType()+"-"+tokens[spans[i].getStart()]);
        }
    }
}
