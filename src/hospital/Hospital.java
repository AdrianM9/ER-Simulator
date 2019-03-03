package hospital;

import hospital.patients.Patient;
import hospital.queues.ExaminationQueue;
import hospital.queues.InvestigationQueue;
import hospital.queues.TriageQueue;
import hospital.staff.ERTechnician;
import hospital.staff.Nurse;
import hospital.staff.doctors.Doctor;
import hospital.staff.doctors.Cardiologist;
import hospital.staff.doctors.ERPhysician;
import hospital.staff.doctors.Gastroenterologist;
import hospital.staff.doctors.GeneralSurgeon;
import hospital.staff.doctors.Internist;
import hospital.staff.doctors.Neurologist;
import services.InputReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all the information and methods needed to manage information about the hospital.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class Hospital {

    private int time;
    private List<Doctor> doctors;
    private List<Nurse> nurses;
    private List<ERTechnician> erTechnicians;
    private List<Patient> allPatients;
    private TriageQueue triageQueue;
    private ExaminationQueue examinationQueue;
    private InvestigationQueue investigationQueue;
    private List<Patient> hospitalizedPatients;

    /**
     * Hospital constructor.
     *
     * @param input the information the hospital is based at the beginning
     */
    public Hospital(InputReader input) {
        time = 0;

        // Setting the list with nurses.
        nurses = new ArrayList<Nurse>();
        for (int id = 0; id < input.getNurses(); id++) {
            nurses.add(new Nurse(id));
        }

        // Setting the list with ER Technicians.
        erTechnicians = new ArrayList<ERTechnician>();
        for (int id = 0; id < input.getInvestigators(); id++) {
            erTechnicians.add(new ERTechnician(id));
        }

        // Setting the list with doctors.
        doctors = new ArrayList<Doctor>();
        int id = 0;
        for (Doctor doctor : input.getDoctors()) {
            switch (doctor.getType()) {
                case CARDIOLOGIST:
                    doctors.add(new Cardiologist(doctor, id));
                    break;
                case ER_PHYSICIAN:
                    doctors.add(new ERPhysician(doctor, id));
                    break;
                case GASTROENTEROLOGIST:
                    doctors.add(new Gastroenterologist(doctor, id));
                    break;
                case GENERAL_SURGEON:
                    doctors.add(new GeneralSurgeon(doctor, id));
                    break;
                case INTERNIST:
                    doctors.add(new Internist(doctor, id));
                    break;
                case NEUROLOGIST:
                    doctors.add(new Neurologist(doctor, id));
                    break;
                default:
            }

            id++;
        }

        //Initializing the other fields.
        allPatients = new ArrayList<Patient>();
        triageQueue = new TriageQueue();
        examinationQueue = new ExaminationQueue();
        investigationQueue = new InvestigationQueue();
        hospitalizedPatients = new ArrayList<Patient>();
    }

    /**
     * Getter for the time which is the current round.
     *
     * @return the current round
     */
    public int getTime() {
        return time;
    }

    /**
     * Advances the time. Increments the round number.
     */
    public void advanceTime() {
        time++;
    }

    /**
     * Getter for the list of hospital's doctors.
     *
     * @return the list with doctors
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Getter for the list of hospital's nurses.
     *
     * @return the list with nurses
     */
    public List<Nurse> getNurses() {
        return nurses;
    }

    /**
     * Getter for the list of hospital's ER Technicians.
     *
     * @return the list with ER Technicians
     */
    public List<ERTechnician> getErTechnicians() {
        return erTechnicians;
    }

    /**
     * Getter for the list of hospital's all patients which visited it.
     *
     * @return the list with all the patients which visited the hospital
     */
    public List<Patient> getAllPatients() {
        return allPatients;
    }

    /**
     * Getter for the hospital's triage queue.
     *
     * @return the triage queue
     */
    public TriageQueue getTriageQueue() {
        return triageQueue;
    }

    /**
     * Getter for the hospital's examination queue.
     *
     * @return the examination queue
     */
    public ExaminationQueue getExaminationQueue() {
        return examinationQueue;
    }

    /**
     * Getter for the hospital's investigation queue.
     *
     * @return the investigation queue
     */
    public InvestigationQueue getInvestigationQueue() {
        return investigationQueue;
    }

    /**
     * Getter for the list of hospitalized patients.
     *
     * @return the list with hospitalized patients
     */
    public List<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }

    /**
     * Adds a patient in the list with hospitalized patients.
     *
     * @param patient the patient to be added
     */
    public void addHospitalizedPatient(Patient patient) {
        hospitalizedPatients.add(patient);
    }
}
