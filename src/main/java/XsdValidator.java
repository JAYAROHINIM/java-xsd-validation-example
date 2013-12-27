import javax.xml.XMLConstants;
import javax.xml.bind.ValidationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: magzhan.karasayev
 * Date: 27.12.13
 * Time: 17:54
 */
public class XsdValidator {
    public static void reportValidation(String... xsdXmlPairs) {
        if (xsdXmlPairs.length%2 != 0) { throw new IllegalArgumentException("num args must be %2 = 0"); }
        for (int i = 0; i < xsdXmlPairs.length; i += 2) {
            String xsd = xsdXmlPairs[i];
            String xml = xsdXmlPairs[i+1];
            if (isValid(xsd, xml)) {
                System.out.println(String.format("PASSED: xml '%s' passed validation against xsd '%s'", xml, xsd));
            } else {
                System.out.println(String.format("FAILED: xml '%s' failed validation against xsd '%s'", xml, xsd));
            }
        }
    }

    private static boolean isValid(String schemaFileName, String xmlFileName) {
        try                 { validate(schemaFileName, xmlFileName); return true;}
        catch (Throwable e) { return false; }
    }

    private static void validate(String schemaFileName, String xmlFileName) throws Exception {
        // xsd
        URL schemaURL = Thread.currentThread().getContextClassLoader().getResource(schemaFileName);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaURL);
        Validator validator = schema.newValidator();

        // xml
        URL xml = Thread.currentThread().getContextClassLoader().getResource(xmlFileName);
        StreamSource asSource = new StreamSource(new File(xml.toURI()));

        // validating
        try {
            validator.validate(asSource);
        }catch (Throwable e) {
            throw new ValidationException(e);
        }
    }
}
