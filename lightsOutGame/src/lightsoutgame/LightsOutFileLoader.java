
package lightsoutgame;
import exceptions.UnsupportedLightsOutFileException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Loads a file and checks if it is in the correct format for the Light's Out game
 * @author Greg Geary
 * @version 1
 */
class LightsOutFileLoader
{
    /**
     * Checks of a file is compatible and converts its contents to a 2-d boolean array that is saved in lightsOut.
     * @param lightsOut the class where the 2-d boolean is written to.
     * @param file the file to be read.
     * @throws IOException
     * @throws FileNotFoundException if the file does not exist.
     * @throws UnsupportedLightsOutFileException if the file is not compatible.
     */
    public void load(LightsOut lightsOut, File file) throws IOException, FileNotFoundException, UnsupportedLightsOutFileException {
        try
        {
            Scanner scan = new Scanner(file);
            int k = 0;
            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                for (int i = 0; i < line.length(); i++)
                {
                    if (line.charAt(i) != 'X' && line.charAt(i) != '_' && line.charAt(i) != '\n')
                    {
                        scan.close();
                        throw new UnsupportedLightsOutFileException("Invalid file selected!");
                    }
                    line.replaceFirst("\n", "");
                    lightsOut.forceLit(k, i, '_' == line.charAt(i));
                }
                k++;
            }
            scan.close();
        }
        catch (FileNotFoundException e) {}
    }
}