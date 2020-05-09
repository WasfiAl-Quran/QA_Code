package calcWithLog;

import java.util.logging.*;

public class LogHand extends Handler {

    private final StringBuilder stringBuffer = new StringBuilder();

    @Override
    public void publish(final LogRecord record) {
        stringBuffer.append(record.getMessage());
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }

    public String getLog(){
        return stringBuffer.toString();
    }
}