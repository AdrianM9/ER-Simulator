package hospital.patients;

import enums.IllnessType;
import enums.InvestigationResult;
import enums.Urgency;
import hospital.staff.doctors.Doctor;

import java.util.Comparator;

/**
 * Class containing all the information and methods needed to manage patients inside the hospital.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Patient {

    /*
     * Inner static class used to manage patient's state.
     *
     * @author Teodor-Adrian Mirea, 323CA
     */
    private static final class State {

        private IllnessType illnessName;
        private int severity;
        private Urgency urgency;
        private InvestigationResult investigationResult;
        private enums.State currentState;

        State() {
            urgency = Urgency.NOT_DETERMINED;
            investigationResult = InvestigationResult.NOT_DIAGNOSED;
        }

        /*
         * Setter for the patient's illness name.
         *
         * @param illnessName the patient's illness name
         */
        public void setIllnessName(IllnessType illnessName) {
            this.illnessName = illnessName;
        }

        /*
         * Setter for the patient's severity.
         *
         * @param severity the patient's severity
         */
        public void setSeverity(int severity) {
            this.severity = severity;
        }
    }

    /**
     * Comparator used for comparing patients by alphabetical order.
     * The class is based on the singleton design.
     *
     * @author Teodor-Adrian Mirea, 323CA
     */
    public static final class AlphabeticalComparator implements Comparator<Patient> {

        private static AlphabeticalComparator instance = null;

        private AlphabeticalComparator() {
        }

        /**
         * Getter for the single one instance of the class.
         *
         * @return the single one instance
         */
        public static AlphabeticalComparator getInstance() {
            if (instance == null) {
                instance = new AlphabeticalComparator();
            }

            return instance;
        }

        /**
         * Compares the two patients.
         * Returns -1, 0 or 1 as the former patient's name is lexicographical lower than the latter
         * patient's name, they are equal, or the first one is greater.
         *
         * @param o1 the former patient
         * @param o2 the latter patient
         * @return value corresponding to the comparision
         */
        @Override
        public int compare(Patient o1, Patient o2) {
            return (o1.getName().compareTo(o2.getName()));
        }
    }

    private int id;
    private String name;
    private int age;
    private int time;
    private State state;
    private Doctor allocatedDoctor;
    private int timeLeft;

    /**
     * Patient constructor.
     */
    Patient() {
        state = new State();
    }

    /**
     * Setter for the patient's id.
     *
     * @param id the patient's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the patient's name.
     *
     * @return the patient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the patient's name.
     *
     * @param name the patient's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the patient's age.
     *
     * @param age the patient's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for the patient's incoming round time.
     *
     * @return the round the patient comes at the hospital
     */
    public int getTime() {
        return time;
    }

    /**
     * Setter for the patient's incoming round time.
     *
     * @param time the round the patient comes at the hospital
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Getter for the patient's general state.
     *
     * @return the patient's general state
     */
    public State getState() {
        return state;
    }

    /**
     * Getter for the patient's illness name.
     *
     * @return the name of the illness the patient has
     */
    public IllnessType getIllnessName() {
        return state.illnessName;
    }

    /**
     * Getter for the patient's severity.
     *
     * @return the patient's severity
     */
    public int getSeverity() {
        return state.severity;
    }

    /**
     * Setter for the patient's severity.
     *
     * @param severity the patient's severity
     */
    public void setSeverity(int severity) {
        state.severity = severity;
    }

    /**
     * Getter for the patient's urgency.
     *
     * @return the patient's urgency
     */
    public Urgency getUrgency() {
        return state.urgency;
    }

    /**
     * Setter for the patient's urgency.
     *
     * @param urgency the patient's urgency
     */
    public void setUrgency(Urgency urgency) {
        state.urgency = urgency;
    }

    /**
     * Getter for the patient's investigation result.
     *
     * @return the patinent's investigation result
     */
    public InvestigationResult getInvestigationResult() {
        return state.investigationResult;
    }

    /**
     * Setter for the patient's investigation result.
     *
     * @param investigationResult the patient's investigation result
     */
    public void setInvestigationResult(InvestigationResult investigationResult) {
        state.investigationResult = investigationResult;
    }

    /**
     * Getter for the patient's current state.
     *
     * @return the patient's current state
     */
    public enums.State getCurrentState() {
        return state.currentState;
    }

    /**
     * Setter for the patient's current state.
     *
     * @param currentState the patient's current state
     */
    public void setCurrentState(enums.State currentState) {
        state.currentState = currentState;
    }

    /**
     * Getter for the patient's allocated doctor.
     *
     * @return the patient's allocated doctor
     */
    public Doctor getAllocatedDoctor() {
        return allocatedDoctor;
    }

    /**
     * Setter for the patient's allocated doctor.
     *
     * @param allocatedDoctor the patient's allocated doctor
     */
    public void setAllocatedDoctor(Doctor allocatedDoctor) {
        this.allocatedDoctor = allocatedDoctor;
    }

    /**
     * Getter for the patient's number of rounds left to stay in the hospital.
     *
     * @return the patient's number of rounds left
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Setter for the patient's number of rounds left to stay in the hospital.
     *
     * @param timeLeft the patient's number of rounds left
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
