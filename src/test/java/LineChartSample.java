import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
    import javax.swing.JFrame;  
    import javax.swing.SwingUtilities;  
    import javax.swing.WindowConstants;
      
    import org.jfree.chart.ChartFactory;  
    import org.jfree.chart.ChartPanel;  
    import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;
    import org.jfree.data.category.DefaultCategoryDataset;  
      
    public class LineChartSample extends JFrame {  
      private static int num = 10;
      private static final long serialVersionUID = 1L;  
      private DefaultCategoryDataset chart;
      
      public LineChartSample(String title) {  
        super(title);  
        // Create dataset  
        DefaultCategoryDataset dataset = createDataset();  
        // Create chart  
        JFreeChart chart = ChartFactory.createLineChart( 
            "Site Traffic", // Chart title  
            "Date", // X-Axis Label  
            "Number of Visitor", // Y-Axis Label  
            dataset,
                PlotOrientation.VERTICAL, 
                true, 
                false, 
                false
        );  
        chart.setBackgroundPaint(Color.BLACK);
      
        ChartPanel panel = new ChartPanel(chart);  
        
        JButton button = new JButton("Agregar otra columna");
        button.addActionListener(new ActionListener() 
                {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Agregar");
                    addDataSet();
                }
}
        );
        JButton button2 = new JButton("Limpiar");
        button.addActionListener(new ActionListener() 
                {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //limpiar();
                }
}
        );
        setContentPane(panel);  
        this.add(button);
        this.add(button2);
        this.pack();
      }  
      
      private void limpiar(){
          System.out.println("Limpiando");
          chart.clear();
      }
      
      private void addDataSet(){
        String series3 = "Nuevo";
        DefaultCategoryDataset dataset = chart;
        dataset.addValue(200*num, series3, "2016-12-19");  
        dataset.addValue(150*num, series3, "2016-12-20");  
        dataset.addValue(100*num, series3, "2016-12-21");  
        dataset.addValue(100*num, series3, "2016-12-22");  
        dataset.addValue(150*num, series3, "2016-12-23");  
        dataset.addValue(175*num, series3, "2016-12-24");  
        dataset.addValue(200*num, series3, "2016-12-25");  
        num += 5;
        
      }
      
      private DefaultCategoryDataset createDataset() {  
      
        String series1 = "Visitor";  
        String series2 = "Unique Visitor";  
      
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        /*chart = dataset;
        dataset.addValue(200, series1, "2016-12-19");  
        dataset.addValue(150, series1, "2016-12-20");  
        dataset.addValue(100, series1, "2016-12-21");  
        dataset.addValue(210, series1, "2016-12-22");  
        dataset.addValue(240, series1, "2016-12-23");  
        dataset.addValue(195, series1, "2016-12-24");  
        dataset.addValue(245, series1, "2016-12-25");  
      
        dataset.addValue(150, series2, "2016-12-19");  
        dataset.addValue(130, series2, "2016-12-20");  
        dataset.addValue(95, series2, "2016-12-21");  
        dataset.addValue(195, series2, "2016-12-22");  
        dataset.addValue(200, series2, "2016-12-23");  
        dataset.addValue(180, series2, "2016-12-24");  
        dataset.addValue(230, series2, "2016-12-25");  */
      
        return dataset;  
      }  
      
      public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
          LineChartSample example = new LineChartSample("Line Chart Example");  
          example.setAlwaysOnTop(true);  
          example.pack();  
          example.setSize(600, 400);  
          example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
          example.setVisible(true);  
        });  
      }  
    }  