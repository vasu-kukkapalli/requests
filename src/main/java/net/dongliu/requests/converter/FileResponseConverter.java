package net.dongliu.requests.converter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import net.dongliu.requests.utils.Void;

import java.io.*;

/**
 * save http response to file
 *
 * @author Dong Liu
 */
final public class FileResponseConverter implements ResponseConverter<Void> {
    private final File file;

    /**
     * save http response to file
     *
     * @param filePath the file path to write to
     */
    public FileResponseConverter(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * save http response to file
     *
     * @param file the file to write to
     */
    public FileResponseConverter(File file) {
        this.file = file;
    }

    /**
     * copy data into file output stream
     *
     * @param httpEntity the http response entity
     * @return true if success
     */
    @Override
    public Void convert(HttpEntity httpEntity) throws IOException {
        try (InputStream in = httpEntity.getContent()) {
            try (OutputStream out = new FileOutputStream(this.file)) {
                IOUtils.copy(in, out);
            }
        }
        return new Void();
    }
}
