//kadai03_1 17D8010128  とう　こうてつ 2019/10/8
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

public class Kadai03_1 extends Application
{   
     private Canvas cv;
     final int WIDTH=200;
     final int HEIGHT=200;
     final int RADIUS=80;
     public static void main(String[] args)
     {
          launch(args);
     }
     public void start(Stage stage)throws Exception
     {
          cv = new Canvas(WIDTH*2,HEIGHT);
          
          GraphicsContext gc = cv.getGraphicsContext2D();
          BorderPane bp = new BorderPane();
          
          bp.setLeft(cv);
        
          
          Scene sc = new Scene(bp, 2*WIDTH, HEIGHT);
          stage.setScene(sc);
          stage.setTitle("現在時間");
          stage.show();
          
          
          
          Thread th = new Thread(() -> {
        	  for(int t=0;t<60;t++,t%=60){
        		  
        		  gc.clearRect(0, 0, 2*WIDTH, HEIGHT);
        		  
        		  Calendar cl = new GregorianCalendar();
                int hour = cl.get(Calendar.HOUR_OF_DAY);
                int minute = cl.get(Calendar.MINUTE);
                int second = cl.get(Calendar.SECOND);
//                gc.clearRect(0,0,WIDTH,HEIGHT);
                gc.fillText(String.format("%02d:%02d:%02d",
                                          hour, minute, second),100,100);
                
        		  
        		  
        		  int rad_s=(int)(RADIUS*0.8);
        		  int cx=WIDTH/2+WIDTH;
        		  int cy=HEIGHT/2;
        		  double theta_sec=second*6/180.0*Math.PI;
        		  System.out.println(theta_sec);
        		  double xs=rad_s*Math.sin(theta_sec);
        		  double ys=-rad_s*Math.cos(theta_sec);
        		  gc.setStroke(Color.BLACK);
        		  gc.strokeOval(cx-RADIUS, cy-RADIUS, RADIUS*2, RADIUS*2);
        		  gc.setStroke(Color.RED);
        		  gc.strokeLine(cx, cy, (int)(cx+xs), (int)(cy+ys));
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