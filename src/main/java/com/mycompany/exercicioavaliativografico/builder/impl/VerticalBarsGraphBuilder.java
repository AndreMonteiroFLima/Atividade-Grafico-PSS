package com.mycompany.exercicioavaliativografico.builder.impl;

import com.mycompany.exercicioavaliativografico.builder.GraphBuilder;
import com.mycompany.exercicioavaliativografico.service.DataProcessingService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

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
        r.setSeriesPaint(0, Color.CYAN);
        r.setSeriesPaint(1, Color.CYAN);
        plot.getDomainAxis().setVisible(false);
        plot.getRangeAxis().setVisible(false);
    }

    @Override
    public String toString() {
        return "Barras Verticais";
    }
}
