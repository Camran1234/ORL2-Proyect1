package valiente.orl2.UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JPanel;
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;
      
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;  
      
    public class LineChart {  
      
      private static final long serialVersionUID = 1L;  
      private DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
      
      public LineChart(JPanel newPanel) {  
        // Create chart  
        JFreeChart chart = ChartFactory.createLineChart( 
            "Frecuencia de la Cancion", // Chart title  
            "Segundos", // X-Axis Label  
            "Frecuencia", // Y-Axis Label  
            dataSet,
                PlotOrientation.VERTICAL, 
                true, 
                false, 
                false
        );  
        chart.setBackgroundPaint(Color.BLACK);
      
        ChartPanel panel = new ChartPanel(chart);  
        panel.setSize(580, 720);
        panel.setVisible(true);
        newPanel.add(panel);
        newPanel.setVisible(true);
      }  
      
      public void limpiar(){
          dataSet.clear();
      }
      
      public void addDataSet(String canal, float frecuencia, int segundo){
        dataSet.addValue((int)frecuencia, canal, Integer.toString(segundo));  
      }  
        
    }  