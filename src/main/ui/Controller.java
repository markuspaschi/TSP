package main.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import main.ui.tabs.GreedyUI;
import main.ui.tabs.TwoOptUI;

public class Controller {

    @FXML
    public LineChart<Object, Object> lineChart;

    @FXML
    public JFXSlider citySlider;
    public JFXButton generateCitiesButton;

    @FXML
    public JFXButton greedyButton;
    public LineChart greedyLinechart;
    public Label greedyIteration;
    public Label greedyDistance;

    @FXML
    public JFXButton twoOptButton;
    public LineChart twoOptLineChart;
    public Label twoOptDistance;
    public Label twoOptIteration;
    public Label twoOptCurrentOptimumIteration;


    @FXML
    public void initialize() {

        setLineChartProperties(lineChart);
        setLineChartProperties(greedyLinechart);
        setLineChartProperties(twoOptLineChart);

        citySlider.valueProperty().addListener((ov, old_val, new_val) -> Main.getInstance().updateNumberOfCities(new_val));

        generateCitiesButton.setOnAction(event -> Main.getInstance().generateAndShowCities());

        greedyButton.setOnAction(event -> GreedyUI.Companion.startAlgorithm());
        twoOptButton.setOnAction(event -> TwoOptUI.Companion.startAlgorithm());


    }

    @FXML
    public LineChart<Object, Object> getLinechart() {
        return lineChart;
    }

    private void setLineChartProperties(LineChart lineChart) {
        lineChart.getStyleClass().add("tsp-chart");
        lineChart.getXAxis().setLabel("X Koordinate");
        lineChart.getYAxis().setLabel("Y Koordinate");
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.getXAxis().setAutoRanging(false);
        lineChart.getYAxis().setAutoRanging(false);
        lineChart.setAnimated(false);

    }

    public void setStartButton() {
        if (greedyButton != null)
            greedyButton.setText("START");
        if (twoOptButton != null)
            twoOptButton.setText("START");
    }

    public void setStopButton() {
        if (greedyButton != null)
            greedyButton.setText("STOP");
        if (twoOptButton != null)
            twoOptButton.setText("STOP");
    }

}
