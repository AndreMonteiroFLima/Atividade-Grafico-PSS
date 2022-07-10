package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.entity.TitleEntity;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.Title;

import java.util.Iterator;
import java.util.List;

public class AxisTitleGraphDecorator extends GraphDecorator {


    public AxisTitleGraphDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        CategoryPlot plot=graph.showChart().getCategoryPlot();
        plot.getDomainAxis().setLabel("Estado Civil");
        plot.getRangeAxis().setLabel("Quantidade");
        return super.showChart();
    }

    
    
}
