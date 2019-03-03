package services;

import hospital.patients.Patient;
import hospital.staff.doctors.Doctor;

import java.util.List;

/**
 * Class used for storing the information from the input.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class InputReader {

    private int simulationLength;
    private int nurses;
    private int investigators;
    private List<Doctor> doctors;
    private List<Patient> incomingPatients;

    /**
     * InputReader constructor.
     */
    public InputReader() {
    }

    /**
     * Getter for the simulation length, which represents the number of rounds to simulate.
     *
     * @return the simulation length
     */
    public int getSimulationLength() {
        return simulationLength;
    }

    /**
     * Setter for the simulation length, which represents the number of rounds to simulate.
     *
     * @param simulationLength the simulation length
     */
    public void setSimulationLength(int simulationLength) {
        this.simulationLength = simulationLength;
    }

    /**
     * Getter for the number of nurses.
     *
     * @return the number of nurses
     */
    public int getNurses() {
        return nurses;
    }

    /**
     * Setter for the number of nurses.
     *
     * @param nurses the number of nurses
     */
    public void setNurses(int nurses) {
        this.nurses = nurses;
    }

    /**
     * Getter for the number of investigators (ER Technicians).
     *
     * @return the number of investigators
     */
    public int getInvestigators() {
        return investigators;
    }

    /**
     * Setter for the number of investigators (ER Technicians).
     *
     * @param investigators the number of investigators
     */
    public void setInvestigators(int investigators) {
        this.investigators = investigators;
    }

    /**
     * Getter for the list with doctors.
     *
     * @return the list with doctors.
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Setter for the list with doctors.
     *
     * @param doctors the list with doctors
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * Getter for the list with incoming patients.
     *
     * @return the list with incoming patients
     */
    public List<Patient> getIncomingPatients() {
        return incomingPatients;
    }

    /**
     * Setter for the list with incoming patients.
     *
     * @param incomingPatients the list with incoming patients
     */
    public void setIncomingPatients(List<Patient> incomingPatients) {
        this.incomingPatients = incomingPatients;
    }
}
