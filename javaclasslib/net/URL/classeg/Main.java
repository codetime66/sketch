import java.net.*;
import java.io.*;
import java.util.*;

class Main implements Observer {
    Main(String u, int depth) {
        try {
            URL url = new URL(Spider.adjustIfDir(u));
            Spider spider = new Spider(url, depth);

            spider.addObserver(this);
            spider.thread.join(); // Wait for spider to finish.
        } catch (MalformedURLException e) {
        } catch (InterruptedException e) {
        }
    }

    // This method is called immediately whenever the spider
    // discovers a new URL.  It should return as quickly as
    // possible since it is holding up the spider.
    public void update(Observable o, Object arg) {
        SpiderArgs warg = (SpiderArgs)arg;
        for (int i=0; i<warg.depth; i++) {
            System.out.print("   ");
        }
        System.out.println(warg.dst);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <url> <depth>");
        } else {
            new Main(args[0], Integer.parseInt(args[1]));
        }
    }
}

class Spider extends Observable implements Runnable {
    Hashtable walked = new Hashtable();
    int maxDepth;
    URL homeURL;
    String host;
    int port;
    Thread thread;

    Spider(URL url, int depth) {
        homeURL = url;
        maxDepth = depth;
        host = url.getHost();
        port = getPort(url);

        // Start spider thread.
        thread = new Thread(this);
        thread.start();
    }

    void walk(URL url, int curDepth) throws IOException {
        Vector v = findLinks(url);
        
        // Remove duplicates
        for (int i=v.size()-1; i>=0; i--) {
            try {
                URL ur = new URL(url, (String)v.elementAt(i));
                if (walked.get(ur) != null 
                        || !ur.getProtocol().equals("http")
                        || !(getPort(ur) == port)
                        || !ur.getHost().equals(host)) {
                    v.removeElementAt(i);
                } else {
                    walked.put(ur, ur);
                    setChanged();
                    notifyObservers(new SpiderArgs(url, ur, curDepth));
                }
            } catch (MalformedURLException e) {
            }
        }

        // Now walk each of the links in url.
        if (curDepth < maxDepth) {
            for (int i=0; i<v.size(); i++) {
                URL ur = null;
                try {
                    ur = new URL(url, (String)v.elementAt(i));
                    walk(ur, curDepth + 1);
                } catch (MalformedURLException e) {
                } catch (IOException e) {
                    System.out.println("*** " + url + " -> " + ur);
                }
            }
        }
    }

    // Finds all the links in 'url' and returns them in a vector.
    Vector findLinks(URL url) throws IOException {
        Vector v = new Vector();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));
        String line;
        String lineLC;

        while ((line = in.readLine()) != null) {
            while (line != null) {
                int p = line.indexOf("<a ");
                if (p < 0) {
                    p = line.indexOf("<A ");
                    if (p < 0) {
                        break;
                    }
                }

                // Make sure the > is on the same line.
                int q = 0;
                while ((q=line.indexOf(">", p)) < 0) {
                    String l = in.readLine();
                    if (l == null) { // EOF reached.
                        return v;
                    }
                    line += l;
                }
                String u = getLink(in, line, p);

                if (u != null && u.length() > 0) {
                    v.addElement(adjustIfDir(u));
                }
                // Continue looking for links on the line.
                line = line.substring(q+1); 
            }
        }
        in.close();
        return v;
    }

    // Returns the port number of 'url'.  If the port number is 
    // not defined, returns the default HTTP port number.
    int getPort(URL url) {
        int p = url.getPort();
        if (p == -1) {
            p = 80;
        }
        return p;
    }

    // This method implements a heuristic for URLs that are probably
    // directories.  If the last component of the URL does not contain
    // a dot and does not end with a "/", then it is explicitly
    // converted to a directory by appending a "/".
    static String adjustIfDir(String s) {
        int p = s.lastIndexOf("/") + 1;

        if (!s.endsWith("/") && s.indexOf(".", p) < 0) {
            s += "/";
        }
        return s;
    }

    // Extracts the <a> tag from s and then returns the remainder of
    // the line.
    String getLink(BufferedReader in, String s, int p) 
            throws IOException {
        int e;

        // Find the href attribute.
        p = s.indexOf("href=");
        if (p < 0) {
            p = s.indexOf("HREF=");
            if (p < 0) {
                // No href so skip the tag.
                return null;
            }
        }

        // Skip the "href=" 
        p += 5;
        int q = -1;
        if (s.charAt(p) == '"') {
            p++;
            q = s.indexOf('"', p);
        } else {
            q = s.indexOf(' ', p);
            int q2 = s.indexOf('>', p);
            if (Math.min(q, q2) < 0 && Math.max(q, q2) >= 0) {
                // If one is > 0 and the other < 0, use the > 0 one.
                q = Math.max(q, q2);
            } 

            // Use the smaller of the two.
            q = Math.min(q, q2);
        }

        // Could not complete the href tag for some reason
        // so skip the tag.
        if (q < 0) {
            return null;
        }
        s = s.substring(p, q);

        // Remove the reference, if any.
        p = s.indexOf('#');
        if (p == 0) {
            return null;
        } else if (p > 0) {
            s = s.substring(0, p);
        }
        return s;
    }

    public void run() {
        try {
            walk(homeURL, 0);
        } catch (IOException e) {
            System.out.println("*** " + homeURL);
        }
    }
}

class SpiderArgs {
    SpiderArgs(URL src, URL dst, int depth) {
        this.src = src;
        this.dst = dst;
        this.depth = depth;
    }

    URL src;
    URL dst;
    int depth;
}
