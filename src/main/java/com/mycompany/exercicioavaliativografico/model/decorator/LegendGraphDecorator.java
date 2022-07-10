package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import com.mycompany.exercicioavaliativografico.model.decorator.GraphDecorator;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;

public class LegendGraphDecorator extends GraphDecorator {

    public LegendGraphDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        LegendTitle legendTitle = new LegendTitle(graph.showChart().getPlot());
        legendTitle.setPosition(RectangleEdge.BOTTOM);
        graph.showChart().removeLegend();
        graph.showChart().addLegend(legendTitle);
        return super.showChart();
    }
    
    
}
