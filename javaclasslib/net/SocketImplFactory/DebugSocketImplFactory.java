import java.net.DebugSocketImpl;
import java.net.SocketImplFactory;
import java.net.SocketImpl;

public class 
DebugSocketImplFactory implements SocketImplFactory {
    public SocketImpl createSocketImpl() {
        return new DebugSocketImpl();
    }
}
