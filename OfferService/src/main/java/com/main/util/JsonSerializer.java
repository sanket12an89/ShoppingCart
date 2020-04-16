package com.main.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.google.common.base.Preconditions;

@SuppressWarnings("deprecation")
public final class JsonSerializer {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
        AnnotationIntrospector pair = AnnotationIntrospector.pair(primary,
                secondary);
        MAPPER.setAnnotationIntrospector(pair);
    }

    private JsonSerializer() {

    }

    /**
     * Converts an object to JSON String.
     * 
     * @param object
     * @return JSON format as String
     * @throws IOException
     */
    public static final String serialize(Object object) throws IOException {

        Preconditions.checkNotNull(object);

        String json = null;
        try {
            MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            json = MAPPER.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            throw e;
        } catch (JsonMappingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return json;
    }

    /**
     * Converts an object to JSON String.
     * 
     * @param object
     * @return JSON format as String
     * @throws IOException
     */
    public static final void serialize(Object object, OutputStream ostream)
            throws IOException {

        Preconditions.checkNotNull(ostream);
        Preconditions.checkNotNull(object);

        try {
            MAPPER.writeValue(ostream, object);
        } catch (JsonGenerationException e) {
            throw e;
        } catch (JsonMappingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Converts an object to JSON String.
     * 
     * @param <T>
     * 
     * @param object
     * @return JSON format as String
     * @throws IOException
     */
    public static final <T> T deserialize(String json, Class<T> clzz)
            throws IOException {

        Preconditions.checkNotNull(json);
        Preconditions.checkNotNull(clzz);

        try {
            return MAPPER.readValue(json, clzz);
        } catch (JsonGenerationException e) {
            throw e;
        } catch (JsonMappingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 
     * @param jsonReader
     * @param clzz
     * @return
     * @throws IOException
     */
    public static final <T> T deserialize(Reader jsonReader, Class<T> clzz)
            throws IOException {

        Preconditions.checkNotNull(jsonReader);
        Preconditions.checkNotNull(clzz);

        try {
            return MAPPER.readValue(jsonReader, clzz);
        } catch (JsonGenerationException e) {
            throw e;
        } catch (JsonMappingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 
     * @param jsonReader
     * @param clzz
     * @return
     * @throws IOException
     */
    public static final <T> T deserialize(File jsonFile, Class<T> clzz)
            throws IOException {

        Preconditions.checkNotNull(jsonFile);
        Preconditions.checkNotNull(clzz);

        try {
            return MAPPER.readValue(jsonFile, clzz);
        } catch (JsonGenerationException e) {
            throw e;
        } catch (JsonMappingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
}
