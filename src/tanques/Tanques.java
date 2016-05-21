/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanques;
/* paquete para poder inciar robocode
*/
import java.awt.Color;
import robocode.*;
import robocode.Event;
/**
 *
 * @author JYPARDO
 */
public class Tanques extends AdvancedRobot {
    /* variables globales */
    int objetivo = 0;       //dependiendo de cantidad de objetivos cambia de fase
    int fase = 0;           //segun sus niveles de agresividad       

    public void run(){
        setColors(Color.red,Color.blue,Color.black);        // colores del tanque
        
    }
    
    // en esta funcion se crea estrategia principal del robot 
    public void estrategia(){
        setMaxVelocity(8);                  // asigna velocidad
        turnRight(getHeading() % 90);       // para que gire hacia la derecha
        ahead(500);                         // va hacia adelante lo indicado
                                            //ahead hace referencia al evento onHitWall
        setMaxVelocity(5);                  // disminuye velocidad para disminuir daños de choque           
    }
    
    public void onHitWall(HitWallEvent e){
        tu
    }
}
