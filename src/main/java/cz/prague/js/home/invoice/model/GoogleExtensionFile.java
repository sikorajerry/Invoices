package cz.prague.js.home.invoice.model;

import com.google.api.services.drive.model.File;
import lombok.Data;

import java.io.ByteArrayOutputStream;

/**
 * Class kterou potrebuji pro stazeni dat z google drive
 * file jako data o soubory , mime type , real name , id a kind
 * byteArrayOutputStream samotny byte[] souboru
 */

@Data
public class GoogleExtensionFile {
    private File extensionData;
    private ByteArrayOutputStream byteArrayOutputStream;
}
