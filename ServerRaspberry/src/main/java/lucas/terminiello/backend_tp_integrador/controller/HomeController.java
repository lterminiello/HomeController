package lucas.terminiello.backend_tp_integrador.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	public HomeController() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> genereteBestTrip(@RequestParam(value = "state", required = true) Integer state) {

		if (state < 2 && state >= 0) {
			Process p;
			String s = "";
			try {
				String[] cmd = { "python", "ledtest.py", state.toString() };
				p = Runtime.getRuntime().exec(cmd);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				s = br.readLine();
				LOGGER.info(s);
				p.destroy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<String>((s), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(("Debe ingresar 1 para encender o 0 para apagar"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> generateBranch() {

		Process p;
		String s = "";
		try {
			String[] cmd = { "python", "HomeStatus.py"};
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			s = br.readLine();
			LOGGER.info(s);
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>((s), HttpStatus.ACCEPTED);

	}

	// @RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	// public ResponseEntity<String> genereteBestTrip(@RequestParam(value =
	// "from", required = true) String from,
	// @RequestParam(value = "money", required = true) Double money,
	// @RequestParam(value = "strategy", required = false) String strategy) {
	//
	// Process p;
	// String s = "";
	// try {
	// String[] cmd = { "python", "helloword.py" };
	// p = Runtime.getRuntime().exec(cmd);
	// BufferedReader br = new BufferedReader(new
	// InputStreamReader(p.getInputStream()));
	// s = br.readLine();
	// System.out.println(s);
	// p.destroy();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return new ResponseEntity<String>((s), HttpStatus.ACCEPTED);
	//
	// }

	// @RequestMapping(value = "/generate-branchs", method = RequestMethod.GET)
	// @ResponseBody
	// public ResponseEntity<SortedSet<Client>>
	// generateBranch(@RequestParam(value = "from", required = true) String
	// from,
	// @RequestParam(value = "money", required = true) Double money,
	// @RequestParam(value = "strategy", required = true) String strategy) {
	//
	// return new ResponseEntity<SortedSet<Client>>(
	// generateBranchsTrips.generateListTripsClient(from, money, strategy),
	// HttpStatus.ACCEPTED);
	//
	// }

	// @ExceptionHandler(BadParameterException.class)
	// public ResponseEntity<ErrorApi>
	// handleCityNotFoundException(BadParameterException ex) {
	// LOGGER.error(ex.getMessage(), ex);
	// return new ResponseEntity<ErrorApi>(new ErrorApi("400", ex.getMessage()),
	// HttpStatus.BAD_REQUEST);
	// }
	//
	// @ExceptionHandler(Exception.class)
	// public ResponseEntity<ErrorApi> handleCityNotFoundException(Exception ex)
	// {
	// LOGGER.error(ex.getMessage(), ex);
	// return new ResponseEntity<ErrorApi>(new ErrorApi("500", ex.getMessage()),
	// HttpStatus.BAD_REQUEST);
	// }
	//
	// @ExceptionHandler(TypeMismatchException.class)
	// public ResponseEntity<ErrorApi>
	// handleCityNotFoundException(TypeMismatchException ex) {
	// LOGGER.error(ex.getMessage(), ex);
	// return new ResponseEntity<ErrorApi>(new ErrorApi("400", ex.getMessage()),
	// HttpStatus.BAD_REQUEST);
	// }

}
