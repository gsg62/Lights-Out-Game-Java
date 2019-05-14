
package exceptions;

 /**
 * Is thrown if a file is not in the format required for the Light's Out game.
 * @author Greg Geary
 * @version 1
 */
public class UnsupportedLightsOutFileException extends Exception
{
    public UnsupportedLightsOutFileException(String message)
    {
        super(message);
    }
}

