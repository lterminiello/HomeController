package server.home.controller;

import server.home.model.Artifact;
import server.home.model.House;
import server.home.model.Lights;
import server.home.model.Room;
import server.home.service.HouseService;
import server.home.service.LightsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/house")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private HouseService houseService;

    public HomeController(HouseService houseService) {
        super();
        this.houseService = houseService;
    }

    @RequestMapping(value = "/control", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> actionRoom(@RequestParam(value = "roomName", required = true) String roomName,
                                             @RequestParam(value = "arctifactName", required = true) String arctifactName,
                                             @RequestParam(value = "action", required = true) String action,
                                             @RequestParam(value = "power", required = false) Integer power) {
        Artifact artifact = houseService.getHouse().getRoom(roomName).getArtifact(arctifactName);
        String response = artifact.runAction(action,power);
        LOGGER.info(response.toString());
        return new ResponseEntity<String>((response), HttpStatus.ACCEPTED);
    }

/*    @RequestMapping(value = "/kichent", method = RequestMethod.GET)*/
/*    @ResponseBody*/
/*    public ResponseEntity<Lights> lightsKichent() {*/
/*        Lights response = lightsService.kitchenLight();*/
/*        LOGGER.info(response.toString());*/
/*        return new ResponseEntity<Lights>((response), HttpStatus.ACCEPTED);*/
/*    }*/
/*
*/

/*    @RequestMapping(value = "/bedroom", method = RequestMethod.GET)*/
/*    @ResponseBody*/
/*    public ResponseEntity<Lights> genereteBestTrip(@RequestParam(value = "power", required = true) Integer power) {*/
/*
*/

/*        if (power <= 10 && power >= 0) {*/
/*            Lights response = lightsService.bedroomLight(power * 100);*/
/*            LOGGER.info(response.toString());*/
/*            return new ResponseEntity<Lights>((response), HttpStatus.ACCEPTED);*/
/*        } else {*/
/*            return new ResponseEntity<Lights>(HttpStatus.BAD_REQUEST);*/
/*        }*/
/*    }*/
/*
*/

    // @RequestMapping(method = RequestMethod.GET)
    // @ResponseBody
    // public ResponseEntity<String> genereteBestTrip(@RequestParam(value =
    // "state", required = true) Integer state) {
    //
    // if (state < 2 && state >= 0) {
    // Process p;
    // String s = "";
    // try {
    // String[] cmd = { "python", "ledtest.py", state.toString() };
    // p = Runtime.getRuntime().exec(cmd);
    // BufferedReader br = new BufferedReader(new
    // InputStreamReader(p.getInputStream()));
    // s = br.readLine();
    // LOGGER.info(s);
    // p.destroy();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return new ResponseEntity<String>((s), HttpStatus.ACCEPTED);
    // } else {
    // return new ResponseEntity<String>(("Debe ingresar 1 para encender o 0
    // para apagar"),
    // HttpStatus.BAD_REQUEST);
    // }
    // }

/*    @RequestMapping(value = "/status", method = RequestMethod.GET)*/
/*    @ResponseBody*/
/*    public ResponseEntity<Lights> generateBranch() {*/
/*        Lights response = lightsService.statusLights();*/
/*        LOGGER.info(response.toString());*/
/*        return new ResponseEntity<Lights>((response), HttpStatus.ACCEPTED);*/
/*    }*/

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
