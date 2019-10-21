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

public class Kadai03_3 extends Application
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
            
            int rad_s=(int)(RADIUS*0.8);
            int cx=WIDTH/2+WIDTH;
            int cy=HEIGHT/2;
            gc.setStroke(Color.BLACK);
        	  for(int t=0;t<60;t++,t%=60){
              gc.clearRect(0, 0, 2*WIDTH, HEIGHT);
              gc.setStroke(Color.BLACK);
              gc.setLineWidth(1.0);
              for(int i=0;i<60;i++){
                
                double theta=i*6/180.0*Math.PI;
                if(i%5==0){
                  double len=(int)(RADIUS*0.9);
                  double x1=RADIUS*Math.sin(theta);
                  double y1=-RADIUS*Math.cos(theta);
                  double x2=len*Math.sin(theta);
                  double y2=-len*Math.cos(theta);
                  gc.strokeLine(x1+cx, y1+cy, x2+cx, y2+cy); 
                }else{
                  double len=(int)(RADIUS*0.95);
                  double x1=RADIUS*Math.sin(theta);
                  double y1=-RADIUS*Math.cos(theta);
                  double x2=len*Math.sin(theta);
                  double y2=-len*Math.cos(theta);
                  gc.strokeLine(x1+cx, y1+cy, x2+cx, y2+cy); 
                }
              }



              gc.setFont(new Font("Arial",30));
        		  
        		  Calendar cl = new GregorianCalendar();
                int hour = cl.get(Calendar.HOUR_OF_DAY);
                int minute = cl.get(Calendar.MINUTE);
                int second = cl.get(Calendar.SECOND);
                gc.fillText(String.format("%02d:%02d:%02d",
                                          hour, minute, second),30,100);

              System.out.println(String.format("time = %02d:%02d:%02d",hour, minute, second));
        		  

        		  
        
        		  double theta_sec=second*6/180.0*Math.PI;
        		  // System.out.println(theta_sec);
        		  double xs=rad_s*Math.sin(theta_sec);
        		  double ys=-rad_s*Math.cos(theta_sec);
              gc.setLineWidth(1.0);
        		  gc.setStroke(Color.BLACK);
        		  gc.strokeOval(cx-RADIUS, cy-RADIUS, RADIUS*2, RADIUS*2);
        		  gc.setStroke(Color.RED);
        		  gc.strokeLine(cx, cy, (int)(cx+xs), (int)(cy+ys));


              // minute+=(double)(second/60.0);
              int rad_m=(int)(RADIUS*0.85);
              double min=minute+second/60.0;
              double theta_min=min*6/180.0*Math.PI;
              // System.out.println("min:"+min+" theta_min"+theta_min);
              double xm=rad_m*Math.sin(theta_min);
              double ym=-rad_m*Math.cos(theta_min);
              gc.setStroke(Color.BLUE);
              gc.strokeLine(cx, cy, (int)(cx+xm), (int)(cy+ym));

              int rad_h=(int)(RADIUS*0.7);
              double h=hour+min/60.0;
              double theta_h=h*30/180.0*Math.PI;
              // System.out.println("h:"+h+" theta_h:"+theta_h);
              double xh=rad_h*Math.sin(theta_h);
              double yh=-rad_h*Math.cos(theta_h);
              gc.setLineWidth(3.0);
              gc.setStroke(Color.BLUE);
              gc.strokeLine(cx, cy, (int)(cx+xh), (int)(cy+yh));

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