package hospital.staff;

import hospital.patients.Patient;
import hospital.patients.UrgencyEstimator;

/**
 * Class containing all the information and methods needed to manage nurses inside the hospital.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Nurse {

    private int id;

    /**
     * Nurse constructor.
     *
     * @param id the nurse's id
     */
    public Nurse(int id) {
        this.id = id;
    }

    /**
     * Getter for the nurse's id.
     *
     * @return the nurse's id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the nurse's name.
     * It is composed by the string "Nurse" and the nurse's id.
     *
     * @return the nurse's name
     */
    public String getName() {
        return ("Nurse " + id);
    }

    /**
     * Calculates the urgency for a patient.
     *
     * @param patient the patient for which the nurse determines the urgency
     */
    public void calculateUrgency(Patient patient) {
        patient.setUrgency(UrgencyEstimator.getInstance().estimateUrgency(patient.getIllnessName(),
                patient.getSeverity()));
    }

    /**
     * Applies treatment for a patient.
     *
     * @param patient the patient for which the nurse applies treatment
     */
    public void applyTreatment(Patient patient) {
        // Change the patient's severity.
        patient.setSeverity(patient.getSeverity()
                - patient.getAllocatedDoctor().getTreatmentAmeliorationFactor());

        // Decrease the patient's number of rounds left to stay in hospital.
        patient.setTimeLeft(patient.getTimeLeft() - 1);
    }
}
