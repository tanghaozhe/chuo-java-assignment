//kadai02_1 17D8010128  とう　こうてつ 2019/10/1
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;

public class Kadai02_1 extends Application
{   
     private Label lb;
     public static void main(String[] args)
     {
          launch(args);
     }
     public void start(Stage stage)throws Exception
     {
          lb=new Label();
          
          Calendar now = new GregorianCalendar();
          String daytime = now.getTime().toString();
          StringBuffer str = new StringBuffer();
          Formatter fmt = new Formatter(str);
          fmt.format("%02d:%02d:%02d", now.get(Calendar.HOUR_OF_DAY),
                     now.get(Calendar.MINUTE), now.get(Calendar.SECOND)
                     );
          System.out.println(str.toString());
          lb.setText(str.toString());
          
          BorderPane bp = new BorderPane();
          Scene sc = new Scene(bp,200,300);
          bp.setCenter(lb);
          stage.setScene(sc);
          stage.setTitle("現在時刻");
          stage.show();
     }
}