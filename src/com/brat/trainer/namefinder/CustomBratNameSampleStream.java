package com.brat.trainer.namefinder;

import opennlp.tools.formats.brat.BratDocument;
import opennlp.tools.formats.brat.BratNameSampleStream;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.ObjectStream;

/**
 * Created by root on 26/7/17.
 */
public class CustomBratNameSampleStream extends BratNameSampleStream {

    protected CustomBratNameSampleStream(SentenceDetector sentDetector, Tokenizer tokenizer, ObjectStream<BratDocument> samples) {
        super(sentDetector, tokenizer, samples);
    }
}
