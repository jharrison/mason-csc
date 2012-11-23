package masoncsc.datawatcher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Records the values observed by a DataWatcher to a CSV file.
 * 
 * @author Eric 'Siggy' Scott
 */
public class FileDataWriter implements DataListener
{
    private final Writer outputWriter;
    
    /**
     * Initialize an output stream to the specified file.  If the file does not
     * exist, create.  If it does, overwrite it.  Prints an exception to stderr
     * if the file could not be created/opened for writing.
     * 
     * @param path Path to the output file.
     */
    public FileDataWriter(String path)
    {
        FileOutputStream stream = null;
        try
        {
            stream = new FileOutputStream(path);
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Could not open file: " + e.getMessage());
        }
        finally
        {
            outputWriter = new OutputStreamWriter(stream);
        }
    }
    
    /** 
     * Close the file.  Prints an exception to stderr if the file is not open.
     */
    public void close()
    {
        try
        {
            outputWriter.close();
        }
        catch(IOException e)
        {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void dataUpdated(DataWatcher source)
    {
        try
        {
            outputWriter.append(source.dataToCSV() + "\n");
        }
        catch(IOException e)
        {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
