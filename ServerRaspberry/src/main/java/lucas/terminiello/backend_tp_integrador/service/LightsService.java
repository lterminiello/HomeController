package lucas.terminiello.backend_tp_integrador.service;



import org.python.antlr.PythonParser.return_stmt_return;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import lucas.terminiello.backend_tp_integrador.model.Lights;

public class LightsService {

	final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pinKichent = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Kichent", PinState.LOW);
    final GpioPinPwmOutput pinBedroom = gpio.provisionPwmOutputPin(RaspiPin.GPIO_26, "Bedroom", 0);

	public LightsService() {
		super();
	}


	public Lights kitchenLight() {
		if(pinKichent.isLow()){
			pinKichent.high();
		}else{
			pinKichent.low();
		}
		return this.statusLights();
	}

	public Lights bedroomLight(int power) {
		pinBedroom.setPwm(power);
		return this.statusLights();
		
	}
	
	public Lights statusLights(){
		return new Lights(pinKichent.getState().toString(), pinBedroom.getPwm())  ;
	}


}
