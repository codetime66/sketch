import static java.util.Arrays.asList;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.util.Scanner;

public class Sample
{
    public static void main( String[] args )
    {
        for ( String protocol : asList( "http", "https" ) )
        {
            System.setProperty( protocol + ".proxyHost", "localhost" );
            System.setProperty( protocol + ".proxyPort", "3128" );
        }

        Authenticator.setDefault( new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                System.out.println( ">>> requesting password" );
                return new PasswordAuthentication( "foo", "bar".toCharArray() );
            }
        } );

        for ( String protocol : asList( "https", "http", "https" ) )
        {
            System.out.println( "=== BEGIN " + protocol + " ===" );

            try ( InputStream in = new URI( protocol, "httpbin.org", "/headers", null ).toURL().openStream() )
            {
                System.out.println( asString( in ) );
            }
            catch ( Exception e )
            {
                System.out.println( "ERROR: " + e.getMessage() );
            }

            System.out.println( "=== END " + protocol + " ===" );
        }
    }

    private static String asString( InputStream in )
    {
        try ( Scanner scanner = new Scanner( in ) )
        {
            scanner.useDelimiter( "\0" );
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
} 
