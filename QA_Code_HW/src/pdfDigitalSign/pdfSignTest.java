package pdfDigitalSign;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class pdfSignTest {

    File path = new File("src/pdfDigitalSign/files");

    @Test
    public void is_pdfFile_notSigned() throws Exception {

        assertFalse(new pdfSign().verify(path + "/report.pdf"));
    }

    @Test
    public void is_newPdfFile_generated(){

        assertTrue(new File(path + "/signedreport.pdf").exists());
    }

    @Test
    public void is_pdfFile_signed() throws Exception {

        assertTrue(new pdfSign().verify(path + "/signedreport.pdf"));
    }
}