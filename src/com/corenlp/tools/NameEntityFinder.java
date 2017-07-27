package com.corenlp.tools;

import edu.stanford.nlp.simple.Sentence;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by root on 27/7/17.
 */
public class NameEntityFinder {
    public static void main(String[] args) throws IOException {
        ZipFile zipFile = new ZipFile("/home/rajeshp/Documents/OpenNLP/IntelliJIdea_Opennlp_Workspace/ner-call-dail-model.bin");

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            System.out.println(entry.getName());
            InputStream stream = zipFile.getInputStream(entry);
        }
    }

}
