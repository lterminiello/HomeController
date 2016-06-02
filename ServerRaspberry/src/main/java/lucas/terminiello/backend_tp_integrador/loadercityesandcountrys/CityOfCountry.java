package lucas.terminiello.backend_tp_integrador.loadercityesandcountrys;


import java.io.InputStream;
import java.io.InputStreamReader;

public class CityOfCountry {

	public InputStreamReader getCityCountry() {
		InputStream inputStream = CityOfCountry.class.getResourceAsStream("/cityCountry.json");
		return new InputStreamReader(inputStream);
	}
}
