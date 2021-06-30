import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

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

        OutSourced part3 = new OutSourced(3, "Knobs", 12.00, 32, 19, 50, "Knob Shop");

        //sample products
        Product product1 = new Product(1, "Radio", 500.50, 12, 5, 70);

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addProduct(product1);

        /*
        //Testing adding
        System.out.println(part1.getPrice());
        System.out.println(product1.getName());


        //Testing getAlls
        System.out.println(Inventory.getAllParts());
        System.out.println(Inventory.getAllProducts());

        //Testing lookups with ints
        System.out.println(Inventory.lookupPart(1));
        System.out.println(Inventory.lookupProduct(1));
        System.out.println(Inventory.lookupProduct(2));

        //Testing lookups with strings
        System.out.println(Inventory.lookupPart("Knob"));
        System.out.println(Inventory.lookupProduct("Radio"));
        */
        launch(args);
    }
}
