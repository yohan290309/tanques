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
        estrategia();        //pendiente
        
        while(true){
            if (getOthers() != 1 || fase == 0) {
                cauto();        //cuando hay poca energia
            }
            else {
                    ataque();       //cuando esta de frente
            }
        }
    }
    
    // en esta funcion se crea estrategia principal del robot 
        public void estrategia(){
            setMaxVelocity(8);                  // asigna velocidad
            turnRight(getHeading() % 90);      // para que gire hacia la derecha
            ahead(500);                         // va hacia adelante lo indicado
                                                //ahead hace referencia al evento onHitWall
            setMaxVelocity(5);                  // disminuye velocidad para disminuir daños de choque           
        }
        
        public void onHitWall(HitWallEvent e) {		//gira 90º en caso de impacto con el muro
			turnLeft(90);
        }                
  
        public void cauto() {
            turnGunRight(360);			// avanza sobre el muro
            setAhead(800);				// gira cañon
            setTurnGunLeft(360);                    // busca oponentes
            waitFor( new GunTurnCompleteCondition(this)); 	// solo busca en el campo de batalla
        }
    
        public void ataque() {
                    if (getEnergy() > 50) {		// Exelente energia
                            setMaxVelocity(8);      // maxima velocidad
                            turnGunRight(360);
                    }
                    else {
                            fase = 0;       //si hay poca energia
                            estrategia();      // usa la estrategia
                        }
            }
        
        public void onHitByBullet(HitByBulletEvent e) {
           out.println(e.getName() + " me dio");        //quien me disparo
        }   
        
        public void onHitRobot(HitRobotEvent e) {       //CUANDO CHOCA CON OTRO ROBOT
		if (e.getBearing() > -10 && e.getBearing() < 10) {      // Dispara si lo tiene al frente
			fire(3);
		}
	    if (e.getBearing() > -90 && e.getBearing() <= 90) {     // retrocede si esta por los lados o detras
			setBack(200);
		}
     	else {
       		setAhead(100);
		}
		scan();
        }
        
        public void onScannedRobot(ScannedRobotEvent e) {
		++objetivo;					//suma objetivos
		setAhead(100);					// avanza
			if (getOthers() == 1 && getEnergy() > 25 && e.getDistance() < 200) {	// unico objetivo cerca
				fase = 1;							// cambio a modo atque
				turnRight(0 - e.getBearing());					// si hay buena energía
		  		fire(4);							// perseguimos intentando arrollarlo
		  		setTurnRight(0 - e.getBearing());				// y disparando...
		  		setAhead(100); 
				fire(3);
				scan(); 
			}
			if (e.getDistance() < 500) {        //objetivo cerca o medio
				fire(e.getEnergy()/4);      // mucha menos energia gasto
				}                           
			else { 
				fire(getEnergy()/20);       // menos energia si esta lejos
				}
			scan();
		if (objetivo > 5) {     //se modifica velocidad cada cinco detecciones
			objetivo=0;     
			setMaxVelocity((e.getDistance() % 3)+4);    //para dificultar a los adversarios
		}                                                   //puede ver posicion futura
	}
	public void onWin(WinEvent e) {
		setTurnRight(90);
		ahead(100);
		setTurnRight(3600);
		turnGunLeft(3600);
        }
    }

