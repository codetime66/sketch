import java.io.*;
import java.net.*;

// Class loader for loading bytes codes from a file.
public class FileClassLoader extends ClassLoader {
    String path;

    public FileClassLoader(String path) {
        this.path = path;
    }

    // Loads the bytes from file 
    Class loadIt(String classname) throws ClassNotFoundException {
        // To get file name, remove the package name, if any.
        String filename;
        if (classname.indexOf(".") >= 0) {
            filename = classname.substring(classname.lastIndexOf(".")+1);
        } else {
            filename = classname;
        }
        // Make sure the filename ends with .class
        filename += ".class";

        File fullname = new File(path+filename);
        try {
            // Read in the byte codes.
            InputStream is = new FileInputStream(fullname);
            int bufsize = (int)fullname.length();
            byte buf[] = new byte[bufsize];
            is.read(buf, 0, bufsize);
            is.close();

            // Define the class
            return defineClass(classname, buf, 0, buf.length);
        } catch (Exception e) {
            throw new ClassNotFoundException(classname);
        }
    }

    protected Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        // Try to find it from the cache
        Class c = findLoadedClass(name);

        // Not in cache
        if (c == null) {
            // See if it can be loaded by system class loader
            try {
                return findSystemClass(name);
            } catch (ClassNotFoundException e) {
            }
        
            // Try to get it from file
            c = loadIt(name);
        }

        // Link class if asked to do so
        if (c != null && resolve) {
            resolveClass(c);
        }
        return c;
    }

    // Returns a URL containing the location of the named resource.
    public URL getResource(String name) {
        try {
            File file = new File(path);
            String absPath = 
                file.getAbsolutePath().replace(file.separatorChar, '/');
    
            // There is no need to insert a '/' between the absPath
            // and name because absPath already ends with a '/'.
            return new URL("file:///"+absPath+name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns an input stream to the named resource.
    public InputStream getResourceAsStream(String name) {
        try {
            return new FileInputStream(path + name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
