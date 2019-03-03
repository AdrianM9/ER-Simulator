package services.outputWriter;

import hospital.Hospital;
import hospital.patients.Patient;
import services.HospitalManager;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 * Class designed for printing the patients status.
 * The class is based on the observable pattern, it being the one of the observers.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class PatientsActions implements Observer {

    /**
     * The observer's update method which prints for every patient his state.
     *
     * @param o the observable object
     * @param arg the argument for the method (not used)
     */
    @Override
    public void update(Observable o, Object arg) {
        // The update method is called only by the Observable object HospitalManager, so an
        // instanceof verification can be omitted.
        Hospital hospital = ((HospitalManager) o).getHospital();

        System.out.println("~~~~ Patients in round " + (hospital.getTime() + 1) + " ~~~~");

        Collections.sort(hospital.getAllPatients(), Patient.AlphabeticalComparator.getInstance());

        // For every patient, his current state is printed.
        for (Patient patient : hospital.getAllPatients()) {
            System.out.println(patient.getName() + " is " + patient.getCurrentState().getValue());
        }

        System.out.println();
    }
}
