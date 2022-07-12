package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import com.mycompany.exercicioavaliativografico.model.decorator.GraphDecorator;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.TextAnchor;

import java.text.NumberFormat;

public class AxisValueGraphDecorator extends GraphDecorator {

    public AxisValueGraphDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        CategoryPlot plot=graph.showChart().getCategoryPlot();
        plot.getRangeAxis().setVisible(true);
        BarRenderer r = (BarRenderer) plot.getRenderer();

        CategoryItemLabelGenerator generator = r.getDefaultItemLabelGenerator();
        if(generator==null) {
            generator = new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance());
            r.setDefaultItemLabelGenerator(generator);
        }
        r.setDefaultItemLabelsVisible(true);
        r.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        return super.showChart();
    }

    @Override
    public IGraph reverseDecorator() {
        graph.showChart().getCategoryPlot().getRenderer().setDefaultItemLabelsVisible(false);
        graph.showChart().getCategoryPlot().getRenderer().setDefaultItemLabelGenerator(null);
        return graph;
    }


}
