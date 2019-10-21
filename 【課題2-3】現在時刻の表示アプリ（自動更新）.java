//kadai02_3 17D8010128  とう　こうてつ 2019/10/1
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.event.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.text.*;

public class Kadai02_3 extends Application
{   
     private Canvas cv;
     public static void main(String[] args)
     {
          launch(args);
     }
     public void start(Stage stage)throws Exception
     {
          cv = new Canvas(300,200);
          GraphicsContext gc = cv.getGraphicsContext2D();
          BorderPane bp = new BorderPane();
          bp.setCenter(cv);
          Scene sc = new Scene(bp, 300, 200);
          stage.setScene(sc);
          stage.setTitle("現在時間");
          stage.show();
          
          Thread th = new Thread(() -> {
                  while(true){
                          Calendar cl = new GregorianCalendar();
                          int hour = cl.get(Calendar.HOUR_OF_DAY);
                          int minute = cl.get(Calendar.MINUTE);
                          int second = cl.get(Calendar.SECOND);
                          gc.clearRect(0,0,300,200);
                          gc.fillText(String.format("%02d:%02d:%02d",
                                                    hour, minute, second),100,100);
                          try{
                                  Thread.sleep(1000);
                             }catch(Exception e){
                              }
                  }
          });
          th.setDaemon(true);
          th.start();
     }
}