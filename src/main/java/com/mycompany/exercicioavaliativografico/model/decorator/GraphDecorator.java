package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.JFreeChart;

import java.util.Map;

public abstract class GraphDecorator extends IGraph{

    IGraph graph;

    public GraphDecorator(IGraph graph){
        this.graph=graph;
    }

    @Override
    public JFreeChart showChart() {
        return graph.showChart();
    }

    @Override
    public Map<String, Double> getData() {
        return graph.getData();
    }

    @Override
    public IGraph reverseDecorator() {
        return null;
    }


}
