package hospital.patients.comparators;

import hospital.patients.Patient;

import java.util.Comparator;

/**
 * Comparator class used for comparing patients by urgency, by severity, then by alphabetical order.
 * The class is based on the singleton design.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class PatientComparator implements Comparator<Patient> {

    private static PatientComparator instance = null;

    private PatientComparator() {
    }

    /**
     * Getter for the single one instance of the class.
     *
     * @return the single one instance
     */
    public static PatientComparator getInstance() {
        if (instance == null) {
            instance = new PatientComparator();
        }

        return instance;
    }

    /**
     * Compares the two patients.
     * Returns -1, 0 or 1 as the former patient should be treated before the latter patient,
     * they have the same priority, or he should be treated after the latter patient.
     *
     * @param o1 the former patient
     * @param o2 the latter patient
     * @return value corresponding to the comparision
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        UrgencyComparator urgencyComparator = UrgencyComparator.getInstance();

        // First the patients are compared by their urgency.
        if (urgencyComparator.compare(o1.getUrgency(), o2.getUrgency()) == 0) {
            // If they have the same urgency, they are compared by their severity.
            if (o1.getSeverity() == o2.getSeverity()) {
                //If they have the same severity, they are compared by their name.
                return o2.getName().compareTo(o1.getName());
            } else {
                return o2.getSeverity() - o1.getSeverity();
            }
        } else {
            return urgencyComparator.compare(o1.getUrgency(), o2.getUrgency());
        }
    }
}
