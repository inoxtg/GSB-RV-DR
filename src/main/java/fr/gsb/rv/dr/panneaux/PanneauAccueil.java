package fr.gsb.rv.dr.panneaux;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PanneauAccueil extends Panel {

    public static void show(BorderPane panel) throws FileNotFoundException{

        Image img = null;
        img = new Image("https://s1.qwant.com/thumbr/0x380/b/a/eadef4c63fa7d149052117058467068759727eba7221c8a2a57a68cb91f6a4/7e2cce8d8c9150db5018ae40464d2d11--troy-designer.jpg?u=https%3A%2F%2Fi.pinimg.com%2F736x%2F7e%2F2c%2Fce%2F7e2cce8d8c9150db5018ae40464d2d11--troy-designer.jpg&q=0&b=1&p=0&a=0");

        ImageView imageView = new ImageView(img);

        imageView.setFitHeight(800);
        imageView.setFitWidth(800);
        imageView.setPreserveRatio(true);

        panel.setCenter(imageView);
        panel.getCenter().toBack();
    }

}