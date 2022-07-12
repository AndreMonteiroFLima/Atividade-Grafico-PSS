package com.mycompany.exercicioavaliativografico.presenter;

import com.mycompany.exercicioavaliativografico.IO.CSVReader;
import com.mycompany.exercicioavaliativografico.builder.ChartDirector;
import com.mycompany.exercicioavaliativografico.builder.GraphBuilder;
import com.mycompany.exercicioavaliativografico.builder.impl.HorizontalBarsGraphBuilder;
import com.mycompany.exercicioavaliativografico.builder.impl.VerticalBarsGraphBuilder;
import com.mycompany.exercicioavaliativografico.model.ChartModel;
import com.mycompany.exercicioavaliativografico.model.IGraph;
import com.mycompany.exercicioavaliativografico.model.decorator.*;
import com.mycompany.exercicioavaliativografico.service.DataProcessingService;
import com.mycompany.exercicioavaliativografico.view.GraphView;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                if(chartModel == chartModel.reverseDecorator()){
                    JOptionPane.showMessageDialog(null,"Não da para voltar");
                }else {
                    chartModel = chartModel.reverseDecorator();
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
                boolean checked = false;
                String title = null;
                if(titleCheckBox.isSelected()) {
                    title = JOptionPane.showInputDialog("Digite o titulo do grafico");
                    if (title!=null) {
                        setCheckBoxesFalse(titleCheckBox);
                        checked=true;
                    }
                }
                titleCheckBox.setSelected(checked);
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
                    setCheckBoxesFalse(legend);
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
                    setCheckBoxesFalse(dataInformationValue);
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
                setCheckBoxesFalse(axisTitles);

            }
        });

        barColors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new ColoredBarsDecorator(chartModel);
                updateChart(chartModel);
                setCheckBoxesFalse(barColors);
            }
        });

        barColorsByGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new ColoredBarsByGroupDecorator(chartModel);
                updateChart(chartModel);
                titleCheckBox.setEnabled(false);
                setCheckBoxesFalse(barColorsByGroups);
            }
        });

        grid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new AxisGridDecorator(chartModel);
                updateChart(chartModel);
                setCheckBoxesFalse(grid);
            }
        });

        dataInformationPercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new AxisPercentageGraphDecorator(chartModel);
                updateChart(chartModel);
                setCheckBoxesFalse(dataInformationPercentage);
            }
        });

        dataInformationValuePercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartModel = new AxisValuePercentageGraphDecorator(chartModel);
                updateChart(chartModel);
                setCheckBoxesFalse(dataInformationValuePercentage);
            }
        });

    }

    private void setCheckBoxesFalse(JCheckBox checkBox) {
        checkBox.setEnabled(false);
    }

    public JPanel buildChart(IGraph chartModel){
        chartModel = chartDirector.build((GraphBuilder) chartComboBox.getSelectedItem(), chartModel);
        panel = new ChartPanel(chartModel.showChart());
        panel.setPreferredSize(new Dimension(300,200));
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

        titleCheckBox.setEnabled(true);
        legend.setEnabled(true);
        axisTitles.setEnabled(true);
        dataInformationPercentage.setEnabled(true);
        dataInformationValue.setEnabled(true);
        dataInformationValuePercentage.setEnabled(true);
        barColors.setEnabled(true);
        barColorsByGroups.setEnabled(true);
        grid.setEnabled(true);


    }

    private void updateChart(IGraph chartModel){
        panel.setChart(chartModel.showChart());
        panel.getChart().fireChartChanged();
        SwingUtilities.updateComponentTreeUI(graphView);
    }

}
