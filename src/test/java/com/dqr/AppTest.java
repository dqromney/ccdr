package com.dqr;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple PoloniexDataReceiver.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException {

        assertTrue(true);
        givenNotAllFieldsHaveValuesInJson_whenDeserializingAJsonToAClass_thenCorrect();
    }

    public void givenNotAllFieldsHaveValuesInJson_whenDeserializingAJsonToAClass_thenCorrect()
            throws JsonParseException, JsonMappingException, IOException {

        String jsonAsString = "{\"stringValue\":\"a\",\"booleanValue\":true}";
        ObjectMapper mapper = new ObjectMapper();

        MyDto readValue = mapper.readValue(jsonAsString, MyDto.class);

        assertNotNull(readValue);
        assertThat(readValue.getStringValue(), equalTo("a"));
        assertThat(readValue.isBooleanValue(), equalTo(true));
    }

}
