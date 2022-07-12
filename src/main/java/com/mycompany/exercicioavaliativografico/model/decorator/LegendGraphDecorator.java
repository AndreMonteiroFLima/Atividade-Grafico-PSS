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

    LegendTitle legendTitle;

    public LegendGraphDecorator(IGraph graph) {
        super(graph);
    }

    @Override
    public JFreeChart showChart() {
        legendTitle = new LegendTitle(graph.showChart().getPlot());
        legendTitle.setPosition(RectangleEdge.BOTTOM);
        legendTitle.setWidth(0.0);
        legendTitle.setFrame(new BlockBorder(1,1,1,1, Color.BLACK));

        TextTitle textTitle = new TextTitle();
        textTitle.setText("Generos");
        textTitle.setTextAlignment(HorizontalAlignment.CENTER);


        BlockContainer legendCont = new BlockContainer(new BorderArrangement());
        legendCont.add(textTitle,RectangleEdge.TOP);
        BlockContainer items = legendTitle.getItemContainer();
        legendCont.add(items);
        legendTitle.setWrapper(legendCont);
        graph.showChart().removeLegend();
        graph.showChart().removeSubtitle(legendTitle);
        graph.showChart().addSubtitle(legendTitle);

        return super.showChart();
    }

    @Override
    public IGraph reverseDecorator() {
        graph.showChart().removeSubtitle(legendTitle);
        return graph;
    }


}
