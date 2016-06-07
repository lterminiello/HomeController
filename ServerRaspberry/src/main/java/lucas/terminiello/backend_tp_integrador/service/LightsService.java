package lucas.terminiello.backend_tp_integrador.service;

import org.python.core.PyInstance;
import org.python.util.PythonInterpreter;

public class LightsService {
	
	PyInstance hello;

	private PyInstance createClass(PythonInterpreter interpreter, final String className, final String opts) {
		return (PyInstance) interpreter.eval(className + "(" + opts + ")");
	}

	public void init(){
		PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.execfile("hello.py");
		hello = this.createClass(interpreter, "Hello", "Hello");
	}
	
	public String kitchenLight() {
		
		System.out.println(hello.invoke("holi"));
		return "";
	}

	public static void main(String[] args) {
		LightsService lightsService= new LightsService();
		lightsService.init();
		for (int i = 0; i < 10; i++) {
			lightsService.kitchenLight();
		}
		
	}
}
