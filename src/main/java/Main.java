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
 * Time: 16:35
 */
public class Main {
    public static void main(String[] args) throws Exception {
        XsdValidator.reportValidation(
                "xsd/KDTout_CU.xsd", "xsd/sample/payctl133/buggedKDT-2.0.xml",
                "xsd/KDTout_CU.xsd", "xsd/sample/payctl133/buggedKDT-2.1.xml",
                "xsd/KDTout_CU.xsd", "xsd/sample/payctl133/buggedKDT-29.xml",
                "xsd/KDTout_CU.xsd", "xsd/sample/payctl133/KDT_20131107151223984.xml"
                );
    }
}
