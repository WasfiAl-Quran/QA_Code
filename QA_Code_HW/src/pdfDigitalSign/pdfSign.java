package pdfDigitalSign;

import com.java4less.pdf.signature.PDFSignatureFacade;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.bouncycastle.cms.*;
import java.io.File;
import java.io.FileInputStream;
import java.security.cert.CertStore;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class pdfSign {

    File path = new File("src/pdfDigitalSign/files");

    public void sign() {

        PDFSignatureFacade Facade = new PDFSignatureFacade();

        Facade.sign(path.getAbsolutePath() + "/certs/j4l_test.p12", "test", path.getAbsolutePath() + "/report.pdf", path.getAbsolutePath() + "/signedreport.pdf");
    }

    public Boolean verify(String fileName) throws Exception {

        Boolean status = false;

        File signedFile = new File(fileName);

        PDDocument document = PDDocument.load(signedFile);

        List<PDSignature> signatureDictionaries = document.getSignatureDictionaries();

        for (PDSignature signatureDictionary : signatureDictionaries) {

            byte[] signatureContent = signatureDictionary.getContents(new FileInputStream(signedFile));

            byte[] signedContent = signatureDictionary.getSignedContent(new FileInputStream(signedFile));

            CMSProcessable cmsProcessableInputStream = new CMSProcessableByteArray(signedContent);

            CMSSignedData cmsSignedData = new CMSSignedData(cmsProcessableInputStream, signatureContent);

            SignerInformationStore signerInformationStore = cmsSignedData.getSignerInfos();

            Collection signers = signerInformationStore.getSigners();

            CertStore certs = cmsSignedData.getCertificatesAndCRLs("Collection", (String) null);

            Iterator signersIterator = signers.iterator();

            while (signersIterator.hasNext()) {

                SignerInformation signerInformation = (SignerInformation) signersIterator.next();

                Collection certificates = certs.getCertificates(signerInformation.getSID());

                Iterator certIt = certificates.iterator();

                X509Certificate signerCertificate = (X509Certificate) certIt.next();

                if (signerInformation.verify(signerCertificate.getPublicKey(), (String) null)) {

                    status = true;
                } else {

                    status = false;
                }
            }
        }

        return status;
    }
}