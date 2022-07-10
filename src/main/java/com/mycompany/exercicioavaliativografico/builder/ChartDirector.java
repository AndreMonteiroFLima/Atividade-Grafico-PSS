package com.mycompany.exercicioavaliativografico.builder;

import com.mycompany.exercicioavaliativografico.model.ChartModel;
import com.mycompany.exercicioavaliativografico.model.IGraph;
import org.jfree.chart.JFreeChart;

public class ChartDirector {

    public IGraph build(GraphBuilder builder, IGraph model){
        builder.setChart(model);
        builder.doFactoryChart();
        return builder.build();
    }
}
