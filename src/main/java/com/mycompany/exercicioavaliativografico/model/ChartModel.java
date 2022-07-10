package com.mycompany.exercicioavaliativografico.model;

import org.jfree.chart.JFreeChart;

import java.util.Map;

public class ChartModel extends IGraph{

    public ChartModel(Map<String, Double> data) {
        setData(data);
    }

    @Override
    public JFreeChart showChart() {
        return chart;
    }

    public Map<String, Double> getData() {
        return this.data;
    }


}
