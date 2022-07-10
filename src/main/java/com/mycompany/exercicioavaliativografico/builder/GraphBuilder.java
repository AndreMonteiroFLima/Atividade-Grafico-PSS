package com.mycompany.exercicioavaliativografico.builder;

import com.mycompany.exercicioavaliativografico.model.ChartModel;
import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.JFreeChart;

public abstract class GraphBuilder {

    protected IGraph chart;

    public abstract void doFactoryChart();

    final IGraph build(){
        return chart;
    }

    protected void setChart(IGraph chart) {
        this.chart = chart;
    }
}
