package com.mycompany.exercicioavaliativografico.presenter;

import com.mycompany.exercicioavaliativografico.IO.CSVReader;
import com.mycompany.exercicioavaliativografico.model.SubjectModel;
import com.mycompany.exercicioavaliativografico.service.DataProcessingService;
import com.mycompany.exercicioavaliativografico.view.GraphView;
import org.jfree.chart.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

public class GraphPresenter {

    GraphView graphView = new GraphView();
    DataProcessingService dps = new DataProcessingService();

    JButton closeBtn;
    JButton restoreBtn;
    JCheckBox titleCheckBox;

    public GraphPresenter(){

        graphView.getGraphPanel().setBorder(BorderFactory.createTitledBorder("Graph"));
        graphView.getGraphPanel().add(buildChart());
        graphView.setTitle("Tela Principal");
        graphView.pack();
        graphView.setVisible(true);

        closeBtn = graphView.getExitBtn();
        restoreBtn = graphView.getRestoreBtn();
        titleCheckBox = graphView.getTituloCheckBox();

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphView.dispose();
            }
        });

        restoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphView.getGraphPanel().remove(0);
                graphView.getGraphPanel().add(buildChart());
                graphView.repaint();
                SwingUtilities.updateComponentTreeUI(graphView);
                titleCheckBox.setSelected(false);
            }
        });


        titleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = null;
                if(titleCheckBox.isSelected()) {
                     titulo = JOptionPane.showInputDialog("Digite o titulo do grafico");
                }
                    graphView.getGraphPanel().remove(0);
                    graphView.getGraphPanel().add(buildChartWithTitle(titulo));
                    graphView.repaint();
                    SwingUtilities.updateComponentTreeUI(graphView);

            }
        });

    }

    public JPanel buildChart(){
        DefaultCategoryDataset bars = new DefaultCategoryDataset();

        Collection<SubjectModel> subjetcs= CSVReader.readCsv();
        Map<String,Double> data = dps.processData(subjetcs);

        bars.addValue(data.get(dps.getMASCULINO()+ dps.getSOLTEIRO()), dps.getMASCULINO()+ dps.getSOLTEIRO(),"");
        bars.addValue(data.get(dps.getMASCULINO()+ dps.getCASADO()), dps.getMASCULINO()+ dps.getCASADO(),"");
        bars.addValue(data.get(dps.getFEMININO()+ dps.getSOLTEIRO()), dps.getFEMININO()+ dps.getSOLTEIRO(),"");
        bars.addValue(data.get(dps.getFEMININO()+ dps.getCASADO()), dps.getFEMININO()+ dps.getCASADO(),"");


        JFreeChart graph = ChartFactory.createBarChart(null,null,null,bars,PlotOrientation.HORIZONTAL,false,false,false);
        CategoryPlot plot=graph.getCategoryPlot();


        ChartPanel panel = new ChartPanel(graph);
        panel.setPreferredSize(new Dimension(600,400));
        panel.setDomainZoomable(true);
        panel.setEnabled(true);

        return panel;
    }

    public JPanel buildChartWithTitle(String title){
        DefaultCategoryDataset bars = new DefaultCategoryDataset();

        Collection<SubjectModel> subjetcs= CSVReader.readCsv();
        Map<String,Double> data = dps.processData(subjetcs);

        bars.addValue(data.get(dps.getMASCULINO()+ dps.getSOLTEIRO()), dps.getMASCULINO()+ dps.getSOLTEIRO(),"");
        bars.addValue(data.get(dps.getMASCULINO()+ dps.getCASADO()), dps.getMASCULINO()+ dps.getCASADO(),"");
        bars.addValue(data.get(dps.getFEMININO()+ dps.getSOLTEIRO()), dps.getFEMININO()+ dps.getSOLTEIRO(),"");
        bars.addValue(data.get(dps.getFEMININO()+ dps.getCASADO()), dps.getFEMININO()+ dps.getCASADO(),"");


        JFreeChart graph = ChartFactory.createBarChart(title,null,null,bars,PlotOrientation.HORIZONTAL,false,false,true);
        CategoryPlot plot = graph.getCategoryPlot();
        LegendItem legendItem = new LegendItem("Teste","Teste", null,null,null,Color.BLACK);
        LegendItemCollection colletion = new LegendItemCollection();
        colletion.add(legendItem);
        plot.getLegendItems().add(legendItem);

        ChartPanel panel = new ChartPanel(graph);
        panel.setPreferredSize(new Dimension(600,400));
        panel.setDomainZoomable(true);
        panel.setEnabled(true);

        return panel;
    }

}
