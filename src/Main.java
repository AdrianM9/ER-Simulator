import com.fasterxml.jackson.databind.ObjectMapper;

import services.HospitalManager;
import services.InputReader;

import java.io.File;

/**
 * Class designed for storing the program's entry point.
 */
public final class Main {

    private Main() {
    }

    /**
     * The program's entry point.
     *
     * @param args the program arguments
     * @throws Exception exception thrown (there is only one, when reading from the input file)
     */
    public static void main(String[] args) throws Exception {
        // Store the input information into an Input object.
        File inputFile = new File(args[0]);
        ObjectMapper objectMapper = new ObjectMapper();
        InputReader input = objectMapper.readValue(inputFile, InputReader.class);

        // Create the environment where to simulate the events.
        HospitalManager hospitalManager = new HospitalManager(input);

        // Starting the simulation.
        hospitalManager.simulate();
    }
}
