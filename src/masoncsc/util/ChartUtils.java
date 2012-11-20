package masoncsc.util;

import javax.swing.JFrame;

import org.jfree.data.xy.XYSeries;
import sim.display.Controller;
import sim.util.media.chart.HistogramGenerator;
import sim.util.media.chart.TimeSeriesChartGenerator;

public class ChartUtils
{
    /**
     * Create a chart from a single time series and register it with the
     * controller.  The title and Y axis labels for the chart will be taken from
     * TimeSeriesDataStore.getDescription().
     * 
     * @param series The time series data.
     * @param xLabel Label for the X axis.
     * @param c Controller to register the chart with (so it can be opened by
     * the user).
     * @param updateInterval How frequently the chart needs refreshed.
     */
    static public void attachTimeSeriesToChart(XYSeries series, String xLabel, Controller c, int updateInterval)
    {
        attachTimeSeriesToChart(new XYSeries[] { series },
            series.getDescription(),
            xLabel, 
            series.getDescription(),
            c,
            updateInterval);
    }
    
    /**
     * Create a chart from several time series and register it with the
     * controller.
     * 
     * @param seriesArray The time series data.
     * @param title Label to display at the top of the chart.
     * @param xLabel Label for the X axis.
     * @param yLabel Label for the Y axis.
     * @param c Controller to register the chart with (so it can be opened by
     * the user).
     * @param updateInterval How frequently the chart needs refreshed.
     */
    static public void attachTimeSeriesToChart(XYSeries[] seriesArray, String title, String xLabel, String yLabel, Controller c,int updateInterval)
    {
        TimeSeriesChartGenerator chartGen = new TimeSeriesChartGenerator();
        chartGen.setTitle(title);
        chartGen.setXAxisLabel(xLabel);
        chartGen.setYAxisLabel(yLabel);
        for (XYSeries dw : seriesArray)
            chartGen.addSeries(dw, null);
        
        JFrame frame = chartGen.createFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frame.setTitle(title);
        frame.pack();
        c.registerFrame(frame);
    }
    
    /**
     * Create a histogram and register it with the controller.
     * @param data
     * @param numberOfBins Number of bins in the histogram.
     * @param title Label that goes at the top of the chart.
     * @param xLabel Label for the X axis.
     * @param yLabel Label for the Y axis.
     * @param c Controller to register the chart.
     * @return HistogramGenerator which can be used to update data later.
     */
    static public HistogramGenerator attachHistogramToChart(final double[] data, final int numberOfBins, String title, String xLabel, String yLabel, Controller c)
    {
        HistogramGenerator gen = new HistogramGenerator();
        gen.setTitle(title);
        gen.setXAxisLabel(xLabel);
        gen.setYAxisLabel(yLabel);
        gen.addSeries(data, numberOfBins, title, null);
        
        JFrame frame = gen.createFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        frame.setTitle(title);
        frame.setVisible(true);
        c.registerFrame(frame);
        
        return gen;
    }

}