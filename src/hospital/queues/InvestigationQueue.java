package hospital.queues;

import hospital.patients.Patient;
import hospital.patients.comparators.PatientComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class designed for managing the patients at the investigation stage.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class InvestigationQueue {

    private List<Patient> patients;

    /**
     * Constructor for the investigation queue.
     */
    public InvestigationQueue() {
        patients = new ArrayList<Patient>();
    }

    /**
     * Getter for the queue with patients.
     *
     * @return the list with patients.
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Adds patients to the queue.
     *
     * @param patientsToAdd the patients to be added
     */
    public void addPatients(List<Patient> patientsToAdd) {
        this.patients.addAll(patientsToAdd);
    }

    /**
     * Removes patients from the queue.
     *
     * @param patientsToRemove the patients to be removed
     */
    public void removePatients(List<Patient> patientsToRemove) {
        this.patients.removeAll(patientsToRemove);
    }

    /**
     * Sorts the queue with patients.
     */
    public void sort() {
        Collections.sort(patients, PatientComparator.getInstance());
    }

    /**
     * Checks if the queue contains a patient.
     *
     * @param patient the searched patient
     * @return a boolean according with the found of the patient in the queue
     */
    public boolean contains(Patient patient) {
        for (Patient p : patients) {
            if (p == patient) {
                return true;
            }
        }

        return false;
    }
}
