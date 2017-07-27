package com.brat.trainer.namefinder;

import opennlp.tools.ml.model.MaxentModel;
import opennlp.tools.ml.model.SequenceClassificationModel;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.SequenceCodec;
import opennlp.tools.util.model.SerializableArtifact;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by root on 27/7/17.
 */
public class CustomTokenNameFinderModel extends TokenNameFinderModel {

    private static String SERIALIZER_CLASS_NAME_PREFIX = "serializer-class-";

    public CustomTokenNameFinderModel(String languageCode, SequenceClassificationModel<String> nameFinderModel, byte[] generatorDescriptor, Map<String, Object> resources, Map<String, String> manifestInfoEntries, SequenceCodec<String> seqCodec, TokenNameFinderFactory factory) {
        super(languageCode, nameFinderModel, generatorDescriptor, resources, manifestInfoEntries, seqCodec, factory);
    }

    public CustomTokenNameFinderModel(String languageCode, MaxentModel nameFinderModel, int beamSize, byte[] generatorDescriptor, Map<String, Object> resources, Map<String, String> manifestInfoEntries, SequenceCodec<String> seqCodec, TokenNameFinderFactory factory) {
        super(languageCode, nameFinderModel, beamSize, generatorDescriptor, resources, manifestInfoEntries, seqCodec, factory);
    }

    public CustomTokenNameFinderModel(String languageCode, MaxentModel nameFinderModel, byte[] generatorDescriptor, Map<String, Object> resources, Map<String, String> manifestInfoEntries) {
        super(languageCode, nameFinderModel, generatorDescriptor, resources, manifestInfoEntries);
    }

    public CustomTokenNameFinderModel(String languageCode, MaxentModel nameFinderModel, Map<String, Object> resources, Map<String, String> manifestInfoEntries) {
        super(languageCode, nameFinderModel, resources, manifestInfoEntries);
    }

    public CustomTokenNameFinderModel(InputStream in) throws IOException, InvalidFormatException {
        super(in);
    }

    public CustomTokenNameFinderModel(File modelFile) throws IOException, InvalidFormatException {
        super(modelFile);
    }

    public CustomTokenNameFinderModel(URL modelURL) throws IOException, InvalidFormatException {
        super(modelURL);
    }

    public void customSerializeAnalysis(OutputStream stream) {

        Iterator zip = this.artifactMap.keySet().iterator();

        while(zip.hasNext()) {
            String i$ = (String)zip.next();
            Object name = this.artifactMap.get(i$);
            System.out.println(i$+"-"+name);
            if(name instanceof SerializableArtifact) {
                SerializableArtifact artifact = (SerializableArtifact)name;
                String serializer = artifact.getArtifactSerializerClass().getName();
                this.setManifestProperty(SERIALIZER_CLASS_NAME_PREFIX + i$, serializer);
            }
        }
    }
}
