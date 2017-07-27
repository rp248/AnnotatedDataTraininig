package com.brat.trainer.namefinder;

import com.brat.trainer.Trainer;

import java.io.File;

/**
 * Created by root on 27/7/17.
 */
public class BratNamedEntityTester {
    public static void main(String a[]) {
        BratNamedEntityTrainer trainer = new BratNamedEntityTrainer();
        trainer.setConfigurationURL("/home/rajeshp/Documents/OpenNLP/Brat/brat-v1.3_Crunchy_Frog/data/tutorials/assist/annotation.conf");
        trainer.setDataDirectory(new File("/home/rajeshp/Documents/OpenNLP/Brat/brat-v1.3_Crunchy_Frog/data/tutorials/assist/"));
        trainer.train();
    }
}
