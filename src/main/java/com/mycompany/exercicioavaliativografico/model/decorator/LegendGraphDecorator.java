package com.mycompany.exercicioavaliativografico.model.decorator;

import com.mycompany.exercicioavaliativografico.model.IGraph;
import com.mycompany.exercicioavaliativografico.model.decorator.GraphDecorator;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;

import java.awt.*;
import java.util.IllegalFormatCodePointException;

public class LegendGraphDecorator extends GraphDecorator {

    public LegendGraphDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        LegendTitle legendTitle = new LegendTitle(graph.showChart().getPlot());
        legendTitle.setPosition(RectangleEdge.BOTTOM);
        legendTitle.setWidth(0.0);
        legendTitle.setFrame(new BlockBorder(1,1,1,1, Color.BLACK));

        TextTitle textTitle = new TextTitle();
        textTitle.setText("Generos");
        //textTitle.setPosition(RectangleEdge.TOP);
        textTitle.setTextAlignment(HorizontalAlignment.CENTER);
        //textTitle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //textTitle.setVerticalAlignment(VerticalAlignment.CENTER);

        BlockContainer legendCont = new BlockContainer(new BorderArrangement());
        legendCont.add(textTitle,RectangleEdge.TOP);
        BlockContainer items = legendTitle.getItemContainer();
        legendCont.add(items);
        legendTitle.setWrapper(legendCont);
        graph.showChart().removeLegend();
        graph.showChart().addSubtitle(legendTitle);

        return super.showChart();
    }
    
    
}
