package hospital.staff.doctors;

import enums.DoctorType;
import enums.IllnessType;
import enums.State;
import hospital.patients.Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class containing all the information and methods needed to manage doctors inside the hospital.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public class Doctor {

    /**
     * Comparator used for comparing doctors by their id.
     * The class is based on the singleton design.
     *
     * @author Teodor-Adrian Mirea, 323CA
     */
    public static final class IdComparator implements Comparator<Doctor> {

        private static IdComparator instance = null;

        private IdComparator() {
        }

        /**
         * Getter for the single one instance of the class.
         *
         * @return the single one instance
         */
        public static IdComparator getInstance() {
            if (instance == null) {
                instance = new IdComparator();
            }

            return instance;
        }

        /**
         * Compares the two doctors.
         * Returns -1, 0 or 1 as the former doctor's id is less than, equal or greater that the
         * latter doctor's id.
         *
         * @param o1 the former doctor
         * @param o2 the latter doctor
         * @return value corresponding to the comparision
         */
        @Override
        public int compare(Doctor o1, Doctor o2) {
            return o1.id - o2.id;
        }
    }

    private static final int T = 22;
    private static final int STANDARD_TIMELEFT = 3;

    private List<Patient> hospitalizedPatients;
    private int treatmentAmeliorationFactor;

    protected int id;
    protected DoctorType type;
    protected boolean isSurgeon;
    protected int maxForTreatment;
    protected List<IllnessType> treatableIllnesses;
    protected double consultationAmeliorationFactor;
    protected double operationAmeliorationFactor;

    /**
     * Doctor constructor.
     */
    public Doctor() {
        hospitalizedPatients = new ArrayList<Patient>();
        treatableIllnesses = new ArrayList<IllnessType>();
        treatmentAmeliorationFactor = T;
    }

    /**
     * Getter for the doctor's type.
     *
     * @return the doctor's type
     */
    public final DoctorType getType() {
        return type;
    }

    /**
     * Setter for the doctor's type.
     *
     * @param type the doctor's type
     */
    public final void setType(DoctorType type) {
        this.type = type;
    }

    /**
     * Checks if the doctor can operate.
     * Can be seen as a getter for the isSurgeon value.
     *
     * @return the doctor's isSurgeon value
     */
    public final boolean isSurgeon() {
        return isSurgeon;
    }

    /**
     * Setter for the doctor's isSurgeon value.
     *
     * @param isSurgeon the doctor's isSurgeon value
     */
    public final void setIsSurgeon(boolean isSurgeon) {
        this.isSurgeon = isSurgeon;
    }

    /**
     * Getter for the doctor's maximum for treatment value.
     *
     * @return the doctor's maxForTreatment value
     */
    public final int getMaxForTreatment() {
        return maxForTreatment;
    }

    /**
     * Setter for the doctor's maximum for treatment value.
     *
     * @param maxForTreatment the doctor's maximum for treatment value
     */
    public final void setMaxForTreatment(int maxForTreatment) {
        this.maxForTreatment = maxForTreatment;
    }

    /**
     * Getter for the doctor's list of hospitalized patients.
     *
     * @return the doctor's list of hospitalized patients
     */
    public final List<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }

    /**
     * Getter for the doctor's treatment amelioration factor.
     *
     * @return the doctor's treatment amelioration factor
     */
    public final int getTreatmentAmeliorationFactor() {
        return treatmentAmeliorationFactor;
    }

    /**
     * Checks if the doctor can treat the patient's illness.
     *
     * @param patient the patient that the doctor is verified if he can treat his illness
     * @return a boolean value according with the doctor's capability to treat the patient
     */
    public final boolean canTreat(Patient patient) {
        return treatableIllnesses.contains(patient.getIllnessName());
    }

    /**
     * Makes the doctor consult the patient and take a decision.
     *
     * @param patient the patient to be consulted
     */
    public final void consult(Patient patient) {
        if (patient.getSeverity() <= maxForTreatment) {
            sendHome(patient);
        } else {
            patient.setCurrentState(State.INVESTIGATIONS_QUEUE);
        }
    }

    /**
     * Makes the doctor send the patient home.
     *
     * @param patient the patient to be sent home
     */
    public final void sendHome(Patient patient) {
        hospitalizedPatients.remove(patient);
        switch (type) {
            case CARDIOLOGIST:
                patient.setCurrentState(State.HOME_CARDIO);
                break;
            case ER_PHYSICIAN:
                patient.setCurrentState(State.HOME_ERPHYSICIAN);
                break;
            case GASTROENTEROLOGIST:
                patient.setCurrentState(State.HOME_GASTRO);
                break;
            case GENERAL_SURGEON:
                patient.setCurrentState(State.HOME_SURGEON);
                break;
            case INTERNIST:
                patient.setCurrentState(State.HOME_INTERNIST);
                break;
            case NEUROLOGIST:
                patient.setCurrentState(State.HOME_NEURO);
                break;
            default:
        }
    }

    /**
     * Makes the doctor hospitalize the patient.
     *
     * @param patient the patient to be hospitalized
     */
    public final void hospitalize(Patient patient) {
        patient.setTimeLeft((int) Math.max(Math.round(patient.getSeverity()
                * consultationAmeliorationFactor), STANDARD_TIMELEFT));
        hospitalizedPatients.add(patient);
        switch (type) {
            case CARDIOLOGIST:
                patient.setCurrentState(State.HOSPITALIZED_CARDIO);
                break;
            case ER_PHYSICIAN:
                patient.setCurrentState(State.HOSPITALIZED_ERPHYSICIAN);
                break;
            case GASTROENTEROLOGIST:
                patient.setCurrentState(State.HOSPITALIZED_GASTRO);
                break;
            case GENERAL_SURGEON:
                patient.setCurrentState(State.HOSPITALIZED_SURGEON);
                break;
            case INTERNIST:
                patient.setCurrentState(State.HOSPITALIZED_INTERNIST);
                break;
            case NEUROLOGIST:
                patient.setCurrentState(State.HOSPITALIZED_NEURO);
                break;
            default:
        }
    }

    /**
     * Makes the doctor operate the patient.
     *
     * @param patient the patient to be operated
     */
    public final void operate(Patient patient) {
        patient.setSeverity(patient.getSeverity() - (int) Math.round(patient.getSeverity()
                * operationAmeliorationFactor));
        switch (type) {
            case CARDIOLOGIST:
                patient.setCurrentState(State.OPERATED_CARDIO);
                break;
            case ER_PHYSICIAN:
                patient.setCurrentState(State.OPERATED_ERPHYSICIAN);
                break;
            case GENERAL_SURGEON:
                patient.setCurrentState(State.OPERATED_SURGEON);
                break;
            case NEUROLOGIST:
                patient.setCurrentState(State.OPERATED_NEURO);
                break;
            default:
        }
        patient.setTimeLeft((int) Math.max(Math.round(patient.getSeverity()
                * consultationAmeliorationFactor), STANDARD_TIMELEFT));
        hospitalizedPatients.add(patient);
    }

    /**
     * The method checks a doctor's hospitalized patient to decide if he can go home or not.
     *
     * @param patient the patient which is verified
     */
    public final void check(Patient patient) {
        if (patient.getTimeLeft() == 0 || patient.getSeverity() <= 0) {
            patient.setCurrentState(State.HOME_DONE_TREATMENT);
        }
    }
}
