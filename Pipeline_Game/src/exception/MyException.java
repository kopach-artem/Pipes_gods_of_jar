package exception;

import java.io.Serializable;

/**
 * Exception osztály
 */
public class MyException extends Exception implements Serializable {
    public MyException(String message) {
        super(message);
    }
}
