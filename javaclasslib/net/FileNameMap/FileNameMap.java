
package java.net;

/**
 * A simple interface which provides a mechanism to map between
 * between a file name and a MIME type string.
 *
 * @author  Steven B. Byrne
 * @since   JDK1.1
 */
public interface FileNameMap {
    /**
     * @since JDK1.1
     */
    public String getContentTypeFor(String fileName);
}
