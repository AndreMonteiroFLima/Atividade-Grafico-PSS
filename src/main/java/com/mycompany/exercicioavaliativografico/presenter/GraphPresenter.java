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
import java.util.LinkedList;

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
/*    JCheckBox barColors;*/
    JCheckBox barColorsByGroups;
    JCheckBox grid;
    ChartPanel panel;
    IGraph chartModel;

    LinkedList<JCheckBox> checkBoxes = new LinkedList<JCheckBox>();

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
/*        barColors = graphView.getCorBarrasCheckBox();*/
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
                chartModel=defaultChartModel;
                resetCheckBoxes();
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
                    JCheckBox to=checkBoxes.pop();
                    if(to.equals(dataInformationValue) || to.equals(dataInformationPercentage) || to.equals(dataInformationValuePercentage)){
                        dataInformationValue.setEnabled(true);
                        dataInformationPercentage.setEnabled(true);
                        dataInformationValuePercentage.setEnabled(true);
                    }else
                        to.setEnabled(true);

                    to.setSelected(false);
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
                chartModel = new TitleGraphDecorator(chartModel,title);
                checkBoxes.add(titleCheckBox);
                updateChart(chartModel);
            }
        });

        legend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(legend.isSelected()) {
                    selectionRotine(new LegendGraphDecorator(chartModel),legend);
                }
            }
        });




        axisTitles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(axisTitles.isSelected()) {
                    selectionRotine(new AxisTitleGraphDecorator(chartModel),axisTitles);
                }

            }
        });

/*
        barColors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barColors.isSelected()) {
                    chartModel = new ColoredBarsDecorator(chartModel);
                    updateChart(chartModel);
                    checkBoxes.add(barColors);
                    setCheckBoxesFalse(barColors);
                }
            }
        });
*/

        barColorsByGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barColorsByGroups.isSelected()) {
                    selectionRotine(new ColoredBarsByGroupDecorator(chartModel),barColorsByGroups);
                }
            }
        });

        grid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(grid.isSelected()) {
                    selectionRotine(new AxisGridDecorator(chartModel),grid);
                }
            }
        });

        dataInformationPercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataInformationPercentage.isSelected()) {
                    selectionRotine(new AxisPercentageGraphDecorator(chartModel),dataInformationPercentage);
                    dataInformationEnableFalse();
                }
            }
        });

        dataInformationValuePercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataInformationValuePercentage.isSelected()) {
                    selectionRotine(new AxisValuePercentageGraphDecorator(chartModel),dataInformationValuePercentage);
                    dataInformationEnableFalse();
                }
            }
        });

        dataInformationValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataInformationValue.isSelected()) {
                    selectionRotine(new AxisValueGraphDecorator(chartModel),dataInformationValue);
                    dataInformationEnableFalse();
                }
            }
        });

    }

    private void selectionRotine(GraphDecorator decorator,JCheckBox checkBox) {
        chartModel = decorator;
        updateChart(chartModel);
        checkBoxes.add(checkBox);
        setCheckBoxesFalse(checkBox);
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
/*        barColors.setSelected(false);*/
        barColorsByGroups.setSelected(false);
        grid.setSelected(false);

        titleCheckBox.setEnabled(true);
        legend.setEnabled(true);
        axisTitles.setEnabled(true);
        dataInformationPercentage.setEnabled(true);
        dataInformationValue.setEnabled(true);
        dataInformationValuePercentage.setEnabled(true);
/*        barColors.setEnabled(true);*/
        barColorsByGroups.setEnabled(true);
        grid.setEnabled(true);

    }

    private void dataInformationEnableFalse(){
        setCheckBoxesFalse(dataInformationPercentage);
        setCheckBoxesFalse(dataInformationValue);
        setCheckBoxesFalse(dataInformationValuePercentage);
    }

    private void updateChart(IGraph chartModel){
        panel.setChart(chartModel.showChart());
        panel.getChart().fireChartChanged();
        SwingUtilities.updateComponentTreeUI(graphView);
    }

}
