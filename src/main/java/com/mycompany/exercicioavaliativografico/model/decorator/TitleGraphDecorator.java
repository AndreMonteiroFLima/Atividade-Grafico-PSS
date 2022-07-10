package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.JFreeChart;

public class TitleGraphDecorator extends GraphDecorator {

    private String title;

    public TitleGraphDecorator(IGraph graph, String title) {
        super(graph);
        this.title=title;
    }

    @Override
    public JFreeChart showChart() {
        graph.showChart().setTitle(title);
        return super.showChart();
    }

    
    
}
