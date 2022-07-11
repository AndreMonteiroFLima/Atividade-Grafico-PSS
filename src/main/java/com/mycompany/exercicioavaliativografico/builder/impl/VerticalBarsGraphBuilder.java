package com.mycompany.exercicioavaliativografico.builder.impl;

import com.mycompany.exercicioavaliativografico.builder.GraphBuilder;
import com.mycompany.exercicioavaliativografico.service.DataProcessingService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.text.NumberFormat;

public class VerticalBarsGraphBuilder extends GraphBuilder {

    DataProcessingService dps= new DataProcessingService();
    
    @Override
    public void doFactoryChart() {
        DefaultCategoryDataset bars = new DefaultCategoryDataset();

        bars.addValue(chart.getData().get(dps.getMASCULINO()+ dps.getSOLTEIRO()), dps.getMASCULINO(),dps.getSOLTEIRO());
        bars.addValue(chart.getData().get(dps.getMASCULINO()+ dps.getCASADO()), dps.getMASCULINO(),dps.getCASADO());
        bars.addValue(chart.getData().get(dps.getFEMININO()+ dps.getSOLTEIRO()), dps.getFEMININO(),dps.getSOLTEIRO());
        bars.addValue(chart.getData().get(dps.getFEMININO()+ dps.getCASADO()), dps.getFEMININO(),dps.getCASADO());

        chart.setChart(ChartFactory.createBarChart(null,null,null,bars, PlotOrientation.VERTICAL,false,false,false));
        CategoryPlot plot = chart.showChart().getCategoryPlot();
        BarRenderer r =(BarRenderer) chart.showChart().getCategoryPlot().getRenderer();
        r.setBarPainter(new StandardBarPainter());
        r.setDefaultItemLabelsVisible(true);
        r.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER,TextAnchor.TOP_CENTER));
        r.setSeriesPaint(0, Color.CYAN);
        r.setSeriesPaint(1, Color.CYAN);
        plot.getDomainAxis().setVisible(true);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.setRangeGridlinesVisible(false);
        plot.getRangeAxis().setVisible(true);
    }

    @Override
    public String toString() {
        return "Barras Verticais";
    }
}
