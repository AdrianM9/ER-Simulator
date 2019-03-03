package services.outputWriter;

import enums.State;
import hospital.Hospital;
import hospital.patients.Patient;
import hospital.staff.doctors.Doctor;
import services.HospitalManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Collections;

/**
 * Class designed for printing the checking of every doctor's hospitalized patient and the doctor's
 * verdict.
 * The class is based on the observable pattern, it being the one of the observers.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class DoctorsActions implements Observer {

    /**
     * The observer's update method which prints for every doctor its verdicts about every their
     * hospitalized patient.
     *
     * @param o the observable object
     * @param arg the argument for the method (not used)
     */
    @Override
    public void update(Observable o, Object arg) {
        // The update method is called only by the Observable object HospitalManager, so an
        // instanceof verification can be omitted.
        Hospital hospital = ((HospitalManager) o).getHospital();
        List<Doctor> doctors = new ArrayList<Doctor>(hospital.getDoctors());

        System.out.println("~~~~ Doctors check their hospitalized patients and give verdicts ~~~~");

        Collections.sort(doctors, Doctor.IdComparator.getInstance());

        // Every doctor visits his hospitalized patients and checks if they can go home or not.
        for (Doctor doctor : doctors) {
            List<Patient> hospitalizedPatients = hospital.getHospitalizedPatients();
            List<Patient> doctorsHospitalizedPatients = doctor.getHospitalizedPatients();
            List<Patient> patientsToSendHome = new ArrayList<Patient>();

            // The patients should be ordered alphabetical.
            Collections.sort(doctorsHospitalizedPatients,
                    Patient.AlphabeticalComparator.getInstance());

            // For every hospitalized patient, the doctor gives the verdict.
            for (Patient patient : doctorsHospitalizedPatients) {
                doctor.check(patient);

                // If a patient is sent home, he is added into a temporary list with the patients
                // that must be removed from the lists with hospitalized patients.
                if (patient.getCurrentState() == State.HOME_DONE_TREATMENT) {
                    patientsToSendHome.add(patient);
                    System.out.println(doctor.getType().getValue() + " sent " + patient.getName()
                            + " home");
                } else {
                    System.out.println(doctor.getType().getValue() + " says that "
                            + patient.getName() + " should remain in hospital");
                }
            }

            // The patients that were sent home are removed from the hospitalization lists.
            doctorsHospitalizedPatients.removeAll(patientsToSendHome);
            hospitalizedPatients.removeAll(patientsToSendHome);
        }

        System.out.println();
    }
}
