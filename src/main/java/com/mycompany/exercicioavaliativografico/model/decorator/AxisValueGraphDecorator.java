package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import com.mycompany.exercicioavaliativografico.model.decorator.GraphDecorator;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.Range;

public class AxisValueGraphDecorator extends GraphDecorator {

    public AxisValueGraphDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        CategoryPlot plot=graph.showChart().getCategoryPlot();
        plot.getRangeAxis().setVisible(true);
        return super.showChart();
    }

    
    
}
