package masoncsc.datawatcher;



/**
 *
 * @author Eric 'Siggy' Scott
 * @author Joey Harrison
 */
public class ScreenDataWriter implements DataListener
{
    public ScreenDataWriter(DataWatcher source)
    {
        // XXX Move this out of constructor?
        System.out.println(source.getCSVHeader());
        source.addListener(this);
    }
    
    @Override
    public void dataUpdated(DataWatcher source)
    {
        System.out.println(source.dataToCSV());
    }
}
