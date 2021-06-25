import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.OutSourced;
import model.Part;
import model.Product;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        //primaryStage.setTitle("Howdy World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //sample parts
        InHouse part1 = new InHouse(1, "Knob", 39.99, 5, 3, 8, 13);

        OutSourced part2 = new OutSourced(2, "Speaker", 10.00, 30, 20, 100, "Speaker Guys");

        //sample products
        Product product1 = new Product(1, "Radio", 500.50, 12, 5, 70);

        launch(args);
    }
}
