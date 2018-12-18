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

        File fullname = new File(path, filename);
        System.out.println("class file name: " + fullname);
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

    protected synchronized Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        // Try to find it from the cache
        Class c = findLoadedClass(name);
        System.out.println(name + ((c == null) ? " not " : " ") + "in cache");

        // Not in cache
        if (c == null) {
            // See if it can be loaded by system class loader
            try {
                // No need to call resolveClass() on the result
                // because findSystemClass() loads and links the class.
                return findSystemClass(name);
            } catch (ClassNotFoundException e) {
            }
        
            // Try to get it from file
            c = loadIt(name);
        }

        // Link class if asked to do so
        if (c != null && resolve) {
	    System.out.println("Resolving class: " + name);
            resolveClass(c);
        }
        return c;
    }

    // Returns a URL containing the location of the named resource.
    public URL getResource(String name) {
        try {
            File file = new File(path, name);
            String absPath = 
                file.getAbsolutePath().replace(file.separatorChar, '/');

            // If leading character is not '/', add for URL.
            if (absPath.charAt(0) != '/') {
                absPath = '/' + absPath;
            }

            System.out.println("resource name: " + absPath);
            return new URL("file:" + absPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns an input stream to the named resource.
    public InputStream getResourceAsStream(String name) {
        try {
            return new FileInputStream(new File(path, name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
