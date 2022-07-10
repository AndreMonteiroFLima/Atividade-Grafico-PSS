package com.mycompany.exercicioavaliativografico.presenter;

import com.mycompany.exercicioavaliativografico.IO.CSVReader;
import com.mycompany.exercicioavaliativografico.builder.ChartDirector;
import com.mycompany.exercicioavaliativografico.builder.GraphBuilder;
import com.mycompany.exercicioavaliativografico.builder.impl.HorizontalBarsGraphBuilder;
import com.mycompany.exercicioavaliativografico.builder.impl.VerticalBarsGraphBuilder;
import com.mycompany.exercicioavaliativografico.model.ChartModel;
import com.mycompany.exercicioavaliativografico.model.IGraph;
import com.mycompany.exercicioavaliativografico.model.SubjectModel;
import com.mycompany.exercicioavaliativografico.model.decorator.*;
import com.mycompany.exercicioavaliativografico.service.DataProcessingService;
import com.mycompany.exercicioavaliativografico.view.GraphView;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
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
    ChartDirector chartDirector = new ChartDirector();

    JButton closeBtn;
    JButton restoreBtn;
    JButton reverseBtn;
    JCheckBox titleCheckBox;
    JComboBox chartComboBox;
    IGraph defaultChartModel;
    JCheckBox legend;
    JCheckBox axisTitles;
    JCheckBox dataInformationPercentage;
    JCheckBox dataInformationValue;
    JCheckBox dataInformationValuePercentage;
    JCheckBox barColors;
    JCheckBox barColorsByGroups;
    JCheckBox grid;
    ChartPanel panel;
    IGraph chartModel;

    IGraph lastChartModel;
    public GraphPresenter(){

        defaultChartModel = new ChartModel(dps.processData(CSVReader.readCsv()));
        lastChartModel=chartModel=defaultChartModel;

        closeBtn = graphView.getExitBtn();
        restoreBtn = graphView.getRestoreBtn();
        titleCheckBox = graphView.getTituloCheckBox();
        chartComboBox = graphView.getGraphComboBox();
        legend = graphView.getLegendaCheckBox();
        axisTitles=graphView.getTituloEixosCheckBox();
        dataInformationPercentage = graphView.getRotuloPorcentagemCheckBox();
        dataInformationValue = graphView.getRotuloValorCheckBox();
        dataInformationValuePercentage = graphView.getRotuloValorPorcentagemCheckBox();
        barColors = graphView.getCorBarrasCheckBox();
        barColorsByGroups = graphView.getCorBarrasGrupoCheckBox();
        grid = graphView.getGradeCheckBox();
        reverseBtn = graphView.getUnDoBtn();

        initComboBox();


        graphView.getGraphPanel().setBorder(BorderFactory.createTitledBorder("Graph"));
        graphView.getGraphPanel().add(buildChart(defaultChartModel));
        graphView.setTitle("Tela Principal");
        graphView.pack();
        graphView.setVisible(true);


        chartComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphView.getGraphPanel().remove(0);
                graphView.getGraphPanel().add(buildChart(defaultChartModel));
                graphView.repaint();
                SwingUtilities.updateComponentTreeUI(graphView);
                //titleCheckBox.setSelected(false);
            }
        });

        reverseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!chartModel.equals(defaultChartModel)) {
                    chartModel = lastChartModel;
                    updateChart(chartModel);
                }
            }
        });


        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphView.dispose();
            }
        });

        restoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCheckBoxes();
                graphView.getGraphPanel().remove(0);
                graphView.getGraphPanel().add(buildChart(defaultChartModel));
                graphView.repaint();
                SwingUtilities.updateComponentTreeUI(graphView);
                chartModel=defaultChartModel;
            }
        });


        titleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = null;
                if(titleCheckBox.isSelected()) {
                    title = JOptionPane.showInputDialog("Digite o titulo do grafico");
                }
                lastChartModel=chartModel;
                chartModel = new TitleGraphDecorator(chartModel,title);
                updateChart(chartModel);

            }
        });

        legend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(legend.isSelected()) {
                    lastChartModel=chartModel;
                    chartModel = new LegendGraphDecorator(chartModel);
                    updateChart(chartModel);
                }
            }
        });



        dataInformationValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataInformationValue.isSelected()) {
                    lastChartModel=chartModel;
                    chartModel = new AxisValueGraphDecorator(chartModel);
                    updateChart(chartModel);
                }else{
                    graphView.getGraphPanel().remove(0);
                    graphView.getGraphPanel().add(buildChart(defaultChartModel));
                    graphView.repaint();
                    SwingUtilities.updateComponentTreeUI(graphView);
                }
            }
        });

        axisTitles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    chartModel = new AxisTitleGraphDecorator(chartModel);
                    updateChart(chartModel);

            }
        });

        barColors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new ColoredBarsDecorator(chartModel);
                updateChart(chartModel);
            }
        });

        barColorsByGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new ColoredBarsByGroupDecorator(chartModel);
                updateChart(chartModel);
            }
        });

        grid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new AxisGridDecorator(chartModel);
                updateChart(chartModel);
            }
        });

    }

    public JPanel buildChart(IGraph chartModel){
        chartModel = chartDirector.build((GraphBuilder) chartComboBox.getSelectedItem(), chartModel);
        panel = new ChartPanel(chartModel.showChart());
        panel.setPreferredSize(new Dimension(300,200));
        panel.setDomainZoomable(true);
        panel.setEnabled(true);
        return panel;
/*
        DefaultCategoryDataset bars = new DefaultCategoryDataset();

        Collection<SubjectModel> subjetcs= CSVReader.readCsv();
        Map<String,Double> data = dps.processData(subjetcs);

        bars.addValue(data.get(dps.getMASCULINO()+ dps.getSOLTEIRO()), dps.getMASCULINO()+ dps.getSOLTEIRO(),"");
        bars.addValue(data.get(dps.getMASCULINO()+ dps.getCASADO()), dps.getMASCULINO()+ dps.getCASADO(),"");
        bars.addValue(data.get(dps.getFEMININO()+ dps.getSOLTEIRO()), dps.getFEMININO()+ dps.getSOLTEIRO(),"");
        bars.addValue(data.get(dps.getFEMININO()+ dps.getCASADO()), dps.getFEMININO()+ dps.getCASADO(),"");


        JFreeChart graph = ChartFactory.createBarChart(null,null,null,bars,PlotOrientation.HORIZONTAL,false,false,false);

        CategoryPlot plot = graph.getCategoryPlot();
        LegendItem legendItem = new LegendItem("Teste","Teste", null,null,null,Color.BLACK);
        LegendItemCollection colletion = new LegendItemCollection();
        colletion.add(legendItem);
        plot.getLegendItems().add(legendItem);
        LegendTitle legendTitle = new LegendTitle(plot);
        legendTitle.setPosition(RectangleEdge.BOTTOM);
        graph.addLegend(legendTitle);


        ChartPanel panel = new ChartPanel(graph);
        panel.setPreferredSize(new Dimension(600,400));
        panel.setDomainZoomable(true);
        panel.setEnabled(true);

        return panel;*/
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


        ChartPanel panel = new ChartPanel(graph);
        panel.setPreferredSize(new Dimension(600,400));
        panel.setDomainZoomable(true);
        panel.setEnabled(true);

        return panel;
    }

    public void initComboBox(){
        this.chartComboBox.removeAllItems();
        this.chartComboBox.addItem(new HorizontalBarsGraphBuilder());
        this.chartComboBox.addItem(new VerticalBarsGraphBuilder());
    }

    private void resetCheckBoxes(){
        titleCheckBox.setSelected(false);
        legend.setSelected(false);
        axisTitles.setSelected(false);
        dataInformationPercentage.setSelected(false);
        dataInformationValue.setSelected(false);
        dataInformationValuePercentage.setSelected(false);
        barColors.setSelected(false);
        barColorsByGroups.setSelected(false);
        grid.setSelected(false);
    }

    private void updateChart(IGraph chartModel){
        panel.setChart(chartModel.showChart());
        panel.getChart().fireChartChanged();
        SwingUtilities.updateComponentTreeUI(graphView);
    }

}
