package hospital.queues;

import hospital.patients.Patient;
import hospital.patients.comparators.PatientComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class designed for managing the patients at the examination stage.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class ExaminationQueue {

    private List<Patient> patients;

    /**
     * Constructor for the examination queue.
     */
    public ExaminationQueue() {
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
     * Sorts the queue with patients.
     */
    public void sort() {
        Collections.sort(patients, PatientComparator.getInstance());
    }

    /**
     * Clears the queue by removing all the patients.
     */
    public void clear() {
        patients.clear();
    }
}
