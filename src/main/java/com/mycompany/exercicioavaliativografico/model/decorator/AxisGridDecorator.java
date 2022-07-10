package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.JFreeChart;

public class AxisGridDecorator extends GraphDecorator {

    public AxisGridDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        graph.showChart().getCategoryPlot().setDomainGridlinesVisible(true);
        graph.showChart().getCategoryPlot().setRangeGridlinesVisible(true);
        return super.showChart();
    }

    
    
}
