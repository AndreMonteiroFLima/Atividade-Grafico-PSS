package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarPainter;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import java.awt.*;

public class ColoredBarsDecorator extends GraphDecorator {

    public ColoredBarsDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        /*BarRenderer r =(BarRenderer) graph.showChart().getCategoryPlot().getRenderer();
        BarPainter br=r.getBarPainter();
        Dataset bars =  graph.showChart().getCategoryPlot().getDataset();*/

        /*r.setSeriesPaint(0,Color.CYAN);
        r.setSeriesPaint(1,Color.PINK);
        r.setSeriesPaint(2,Color.white);
        r.setSeriesPaint(3,Color.GRAY);*/
        return super.showChart();
    }

    @Override
    public IGraph reverseDecorator() {
        return null;
    }


}
