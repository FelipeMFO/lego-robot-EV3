package color;
//7 -> noire
//6 -> blanche
//1 -> verd
//0 -> rouge
//2 -> bleu
import lejos.hardware.Button; //Esse import eh usado pro butao do exemplo do cara do youtube, o butao que faz parar
//LEMBRAR DE DAR CLOSE NOS SENSORES E MOTORES
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorTest {
	
	EV3ColorSensor colorSensor;		// declarando o sensor
	SampleProvider colorProvider;	// Abstracao de classes que pegam os dados medidos pelo sensor
									// para processar esse dado. Detector de cor eh o provider
	float[] colorSamples;			// Array de floats das amostras pq o fetchSample precisa
									// de um array de floats
	
	
	public static void main(String[] args) {
	
		new ColorTest();
		
	}
	
	public ColorTest() {
		Port s2 = LocalEV3.get().getPort("S3");				// a porta que tem que botar no sensor
		colorSensor = new EV3ColorSensor(s2);				// comando para associar a porta ao sensor declarado
		colorProvider = colorSensor.getColorIDMode();		// sensor detectou e ta passando para o processador de dado
		colorSamples = new float[colorProvider.sampleSize()];  // O provider fornece os numeros de elementos tratados em sua amostra
		
		for(int i = 0; i < 20; i++ ) {
			colorProvider.fetchSample(colorSamples, 0);
			System.out.println(colorSamples[0]);
			Delay.msDelay(200);
			
		}


		
	}
}
