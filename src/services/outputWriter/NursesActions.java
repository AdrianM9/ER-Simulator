package services.outputWriter;

import hospital.Hospital;
import hospital.patients.Patient;
import hospital.staff.Nurse;
import services.HospitalManager;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Class designed for printing the applying of the treatment by the nurses.
 * The class is based on the observable pattern, it being the one of the observers.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class NursesActions implements Observer {

    /**
     * The observer's update method which applies treatment and prints for every nurse the patient
     * he/she treated.
     *
     * @param o the observable object
     * @param arg the argument for the method (not used)
     */
    @Override
    public void update(Observable o, Object arg) {
        // The update method is called only by the Observable object HospitalManager, so an
        // instanceof verification can be omitted.
        Hospital hospital = ((HospitalManager) o).getHospital();

        System.out.println("~~~~ Nurses treat patients ~~~~");

        List<Nurse> nurses = hospital.getNurses();
        List<Patient> hospitalizedPatients = hospital.getHospitalizedPatients();
        int idNurse = 0;

        Collections.sort(hospitalizedPatients, Patient.AlphabeticalComparator.getInstance());

        // For every patient, the following nurse treats him and a corresponding message is printed.
        for (Patient patient : hospitalizedPatients) {
            Nurse nurse = nurses.get(idNurse % nurses.size());
            nurse.applyTreatment(patient);

            if (patient.getTimeLeft() == 1) {
                System.out.println(nurse.getName() + " treated " + patient.getName()
                        + " and patient has " + patient.getTimeLeft() + " more round");
            } else {
                System.out.println(nurse.getName() + " treated " + patient.getName()
                        + " and patient has " + patient.getTimeLeft() + " more rounds");
            }

            idNurse++;
        }

        System.out.println();
    }
}
