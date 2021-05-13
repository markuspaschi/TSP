package main.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import main.helpers.Series;
import main.tsp_utils.City;
import main.tsp_utils.Tour;
import main.tsp_utils.TourManager;


public class Main extends Application {

    private static Main instance;
    public Controller controller;
    private LineChart<Object, Object> linechart;

    private int numberOfCities = 100;


    public Main() {
        instance = this;
    }

    // static method to get instance of view
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        controller = loader.getController();

        linechart = controller.getLinechart();

        primaryStage.setTitle("Traveling Salesman Problem");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    private TourManager tm;

    private void generateCities() {
        tm = TourManager.Companion.getInstance();
        tm.clear();

        for (int i = 0; i < numberOfCities; i++) {
            City city = new City();
            tm.addCity(city);
        }
    }


    public void updateNumberOfCities(Number new_val) {
        numberOfCities = new_val.intValue();
    }

    public void generateAndShowCities() {
        generateCities();

        Tour tour = new Tour(tm);
        Series series = new Series(tour);

        tour.reverseSubList((int) (Math.random() * tour.getSize()), (int) (Math.random() * tour.getSize()));

        main.helpers.LineChart lineChart = new main.helpers.LineChart(linechart);
        lineChart.clearSeriesData();
        lineChart.displaySeries0(series.generateSeries());
    }

    public void showTour(LineChart<Object, Object> customLineChart, Tour tour, boolean animated) {
        Series series = new Series(tour);
        XYChart.Series<Object, Object> series0 = series.generateSeries();

        main.helpers.LineChart lineChart = new main.helpers.LineChart(customLineChart);
        lineChart.clearSeriesData();
        lineChart.displaySeries0(series0);

        if (animated)
            lineChart.displayPathDelayed();
        else
            lineChart.displayPathInstant();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
