package com.mycompany.exercicioavaliativografico.model;

import org.jfree.chart.JFreeChart;

import java.util.Map;
import java.util.Objects;

public abstract class IGraph {

    protected JFreeChart chart;

    protected Map<String,Double> data;
    public abstract JFreeChart showChart();

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }
    public void setData(Map<String, Double> data) {
        this.data = data;
    }

    public Map<String, Double> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGraph iGraph = (IGraph) o;
        return Objects.equals(chart, iGraph.chart) && Objects.equals(data, iGraph.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chart, data);
    }
}
