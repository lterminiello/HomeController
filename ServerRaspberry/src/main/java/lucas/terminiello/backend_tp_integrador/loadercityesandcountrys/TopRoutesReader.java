package lucas.terminiello.backend_tp_integrador.loadercityesandcountrys;

import java.io.InputStream;
import java.io.InputStreamReader;


public class TopRoutesReader {

	public InputStreamReader getTopRoutes() {
		InputStream inputStream = TopRoutesReader.class.getResourceAsStream("top_routes.json");
		return new InputStreamReader(inputStream);
	}
}
