package services;

import enums.InvestigationResult;
import enums.State;
import enums.Urgency;
import hospital.Hospital;
import hospital.patients.Patient;
import hospital.queues.ExaminationQueue;
import hospital.queues.InvestigationQueue;
import hospital.queues.TriageQueue;
import hospital.staff.ERTechnician;
import hospital.staff.Nurse;
import hospital.staff.doctors.Doctor;
import services.outputWriter.DoctorsActions;
import services.outputWriter.NursesActions;
import services.outputWriter.PatientsActions;

import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * This class manages the flow of the program by simulating all the events.
 * The class is based on the observable pattern, it being the notifier.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class HospitalManager extends Observable {

    private int maxTime;
    private Hospital hospital;
    private List<Patient> incomingPatients;

    /**
     * Hospital Manager constructor based on an InputReader object which contains the information
     * taken from the input file.
     *
     * @param input the object containing the information about hospital
     */
    public HospitalManager(InputReader input) {
        maxTime = input.getSimulationLength();
        hospital = new Hospital(input);
        incomingPatients = input.getIncomingPatients();
        addObserver(new DoctorsActions());
        addObserver(new NursesActions());
        addObserver(new PatientsActions());
    }

    /**
     * Getter for the hospital field.
     *
     * @return the hospital
     */
    public Hospital getHospital() {
        return hospital;
    }

    /**
     * Simulates every round by taking patients, moving them between hospital's queues and other
     * locations, by making doctors, nurses and ER technicians specific actions.
     */
    public void simulate() {
        while (hospital.getTime() < maxTime) {
            movePatientsToTriage();
            calculatePatientsUrgencies();
            movePatientsFromTriageToExamination();
            examinatePatients();
            movePatientsToInvestigation();
            investigatePatients();
            movePatientsFromInvestigationToExamination();

            setChanged();
            notifyObservers();

            hospital.advanceTime();
        }
    }

    /*
     * Takes the patients which must enter the hospital in the current round and moves them from
     * the incoming patients list to the hospital's triage queue.
     */
    private void movePatientsToTriage() {
        List<Patient> patientsToMove = new ArrayList<Patient>();
        List<Patient> allPatients = hospital.getAllPatients();
        TriageQueue triageQueue = hospital.getTriageQueue();

        // Add every patient with the incoming round equal to the current round into a temporary
        // list with the patients that will be moved and change his current state.
        for (Patient patient : incomingPatients) {
            if (patient.getTime() == hospital.getTime()) {
                patientsToMove.add(patient);
                patient.setCurrentState(State.TRIAGE_QUEUE);
            }
        }

        // Remove patients from the incoming patients list and add them into the list with patients
        // which have visited the hospital and into the triage queue.
        incomingPatients.removeAll(patientsToMove);
        allPatients.addAll(patientsToMove);
        triageQueue.addPatients(patientsToMove);
    }

    /*
     * Takes every nurse and calculates the urgency for the first number of nurses patients in the
     * hospital's triage queue.
     */
    private void calculatePatientsUrgencies() {
        TriageQueue triageQueue = hospital.getTriageQueue();

        triageQueue.sort();

        // Every nurse calculates the urgency for only one patient.
        for (Nurse nurse : hospital.getNurses()) {
            if (nurse.getId() == triageQueue.getPatients().size()) {
                break;
            }

            Patient patient = triageQueue.getPatients().get(nurse.getId());

            nurse.calculateUrgency(patient);
        }
    }

    /*
     * Moves the patients which have the urgency calculated from the triage queue into the
     * examination queue.
     */
    private void movePatientsFromTriageToExamination() {
        TriageQueue triageQueue = hospital.getTriageQueue();
        ExaminationQueue examinationQueue = hospital.getExaminationQueue();
        List<Patient> patientsToMove = new ArrayList<Patient>();

        // Add every patient with the urgency determined into a temporary list with the patients
        // that will be moved and change his current state.
        for (Patient patient : triageQueue.getPatients()) {
            if (patient.getUrgency() != Urgency.NOT_DETERMINED) {
                patientsToMove.add(patient);
                patient.setCurrentState(State.EXAMINATIONS_QUEUE);
            }
        }

        // Remove the patients from the triage queue and add them into the examination queue.
        triageQueue.removePatients(patientsToMove);
        examinationQueue.addPatients(patientsToMove);
    }

    /*
     * Takes every patient and finds a doctor which examinates him.
     */
    private void examinatePatients() {
        ExaminationQueue examinationQueue = hospital.getExaminationQueue();
        List<Doctor> doctors = hospital.getDoctors();
        Map<Patient, List<Doctor>> availableDoctors = new LinkedHashMap<Patient, List<Doctor>>();

        examinationQueue.sort();

        // For every patient a list with doctors which are capable to treat his illness is created
        // and associated with him.
        for (Patient patient : examinationQueue.getPatients()) {
            List<Doctor> correspondingDoctors = new ArrayList<Doctor>();

            switch (patient.getInvestigationResult()) {
                // If the patient should be operated, the doctor must be a surgeon which is able to
                // treat the patient.
                case OPERATE:
                    for (Doctor doctor : doctors) {
                        if (doctor.isSurgeon() && doctor.canTreat(patient)) {
                            correspondingDoctors.add(doctor);
                        }
                    }

                    availableDoctors.put(patient, correspondingDoctors);
                    break;

                // Otherwise, the only one condition is that the doctor should be able to treat the
                // patient.
                default:
                    for (Doctor doctor : doctors) {
                        if (doctor.canTreat(patient)) {
                            correspondingDoctors.add(doctor);
                        }
                    }

                    availableDoctors.put(patient, correspondingDoctors);
            }
        }

        // For every patient, the first doctor in the list of available doctors will treat him. The
        // doctor is then moved at the end of the other lists, including the list of all hospital's
        // doctors.
        for (Patient patient : examinationQueue.getPatients()) {
            Doctor allocatedDoctor = null;

            // The first available doctor is set as the doctor to treat the patient and,
            // depending on the investigation result, certain actions are performed by the doctor.
            switch (patient.getInvestigationResult()) {
                case NOT_DIAGNOSED:
                    allocatedDoctor = availableDoctors.get(patient).get(0);
                    allocatedDoctor.consult(patient);
                    break;
                case TREATMENT:
                    allocatedDoctor = availableDoctors.get(patient).get(0);
                    allocatedDoctor.sendHome(patient);
                    break;
                case HOSPITALIZE:
                    allocatedDoctor = availableDoctors.get(patient).get(0);
                    allocatedDoctor.hospitalize(patient);
                    hospital.addHospitalizedPatient(patient);
                    break;
                case OPERATE:
                    // If there is no doctor able to operate the patient, he is moved to another
                    // hospital.
                    if (availableDoctors.get(patient).isEmpty()) {
                        patient.setCurrentState(State.OTHER_HOSPITAL);
                    } else {
                        allocatedDoctor = availableDoctors.get(patient).get(0);
                        allocatedDoctor.operate(patient);
                        hospital.addHospitalizedPatient(patient);
                    }
                    break;
                default:
            }

            // For every patient P, if the current patient's doctor is found in the P's list with
            // available doctors, he is moved at the end of it.
            for (Patient otherPatient : examinationQueue.getPatients()) {
                List<Doctor> otherPatientAvailableDoctors = availableDoctors.get(otherPatient);
                if (otherPatientAvailableDoctors.contains(allocatedDoctor)) {
                    otherPatientAvailableDoctors.remove(allocatedDoctor);
                    otherPatientAvailableDoctors.add(allocatedDoctor);
                }
            }

            // If the doctor exists, he is moved at the end of the hospital's list with all the
            // doctors. There is a possibility that the allocatedDoctor value to be null when there
            // is no surgeon available to operate a patient and the allocatedDoctor value remains
            // the one set at the beginning, which is null.
            if (allocatedDoctor != null) {
                doctors.remove(allocatedDoctor);
                doctors.add(allocatedDoctor);
            }

            // Set the found doctor as the patient's doctor.
            patient.setAllocatedDoctor(allocatedDoctor);
        }

        // Clear the examination queue as all the patients have been examinated.
        examinationQueue.clear();
    }

    /*
     * Moves the patients which should be investigated from the examination queue into the
     * investigation queue.
     */
    private void movePatientsToInvestigation() {
        InvestigationQueue investigationQueue = hospital.getInvestigationQueue();
        List<Patient> patientsToMove = new ArrayList<Patient>();

        // Add every patient which should be investigated and is not already at investigations into
        // into a temporary list with the patients that will be moved.
        for (Patient patient : hospital.getAllPatients()) {
            if (patient.getCurrentState() == State.INVESTIGATIONS_QUEUE
                    && !investigationQueue.contains(patient)) {
                patientsToMove.add(patient);
            }
        }

        // Moving patients into the investigation queue.
        investigationQueue.addPatients(patientsToMove);
    }

    /*
     * Takes every ER technician to investigate only one patients in the hospital's
     * investigation queue.
     */
    private void investigatePatients() {
        InvestigationQueue investigationQueue = hospital.getInvestigationQueue();

        investigationQueue.sort();

        // Every ER technician analyzes only one patient.
        for (ERTechnician erTechnician : hospital.getErTechnicians()) {
            if (erTechnician.getId() == investigationQueue.getPatients().size()) {
                break;
            }

            Patient patient = investigationQueue.getPatients().get(erTechnician.getId());

            erTechnician.analyze(patient);
        }
    }

    /*
     * Moves the patients which have been investigated from the investigation queue into the
     * examination queue.
     */
    private void movePatientsFromInvestigationToExamination() {
        InvestigationQueue investigationQueue = hospital.getInvestigationQueue();
        ExaminationQueue examinationQueue = hospital.getExaminationQueue();
        List<Patient> patientsToMove = new ArrayList<Patient>();

        // Add every patient which has been investigated into a temporary list with the patients
        // that will be moved and change his current state.
        for (Patient patient : investigationQueue.getPatients()) {
            if (patient.getInvestigationResult() != InvestigationResult.NOT_DIAGNOSED) {
                patientsToMove.add(patient);
                patient.setCurrentState(State.EXAMINATIONS_QUEUE);
            }
        }

        // Remove the patients from the investigation queue and add them into the examination queue.
        investigationQueue.removePatients(patientsToMove);
        examinationQueue.addPatients(patientsToMove);
    }
}
