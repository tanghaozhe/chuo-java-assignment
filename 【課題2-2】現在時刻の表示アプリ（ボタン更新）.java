//kadai02_2 17D8010128  とう　こうてつ 2019/10/1
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.event.*;

public class Kadai02_2 extends Application
{   
     private Label lb;
     private Button bt;
     
     public static void main(String[] args)
     {
          launch(args);
     }
     public void start(Stage stage)throws Exception
     {
          lb=new Label();
          bt = new Button("更新");
          //get time
          Calendar now = new GregorianCalendar();
          String daytime = now.getTime().toString();
          StringBuffer str = new StringBuffer();
          Formatter fmt = new Formatter(str);
          fmt.format("%02d:%02d:%02d", now.get(Calendar.HOUR_OF_DAY),
                     now.get(Calendar.MINUTE), now.get(Calendar.SECOND)
                     );
          System.out.println(str.toString());
          lb.setText(str.toString());
          
          //border
          BorderPane bp = new BorderPane();
          bp.setTop(lb);
          bp.setCenter(bt);
         
          //handler
          bt.setOnAction(new SampleEventHandler());
          
          //stage
          Scene sc = new Scene(bp,200,300);
          stage.setScene(sc);
          stage.setTitle("現在時間");
          stage.show();
     }
     class SampleEventHandler implements EventHandler<ActionEvent>
     {
          public void handle(ActionEvent e)
          {
                  Calendar now = new GregorianCalendar();
                  String daytime = now.getTime().toString();
                  StringBuffer str = new StringBuffer();
                  Formatter fmt = new Formatter(str);
                  fmt.format("%02d:%02d:%02d", now.get(Calendar.HOUR_OF_DAY),
                             now.get(Calendar.MINUTE), now.get(Calendar.SECOND)
                             );
                  System.out.println(str.toString());
                  lb.setText(str.toString());
          }
     }
}