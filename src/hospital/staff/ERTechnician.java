package hospital.staff;

import enums.InvestigationResult;
import hospital.patients.Patient;

/**
 * Class containing all the information and methods needed to manage ER Technicians inside the
 * hospital.
 *
 * @author Teodor-Adrian Mirea, 323CA
 */
public final class ERTechnician {

    private static final int C1 = 75;
    private static final int C2 = 40;

    private int id;

    /**
     * ER Technician constructor.
     *
     * @param id the ER Technician's id
     */
    public ERTechnician(int id) {
        this.id = id;
    }

    /**
     * Getter for the ER Technician's id.
     *
     * @return the ER Technician's id
     */
    public int getId() {
        return id;
    }

    /**
     * Analyzes a patient for setting an investigation result to help the doctor to decide.
     *
     * @param patient the patient which is analyzed
     */
    public void analyze(Patient patient) {
        if (patient.getSeverity() > C1) {
            patient.setInvestigationResult(InvestigationResult.OPERATE);
        } else if (patient.getSeverity() <= C2) {
            patient.setInvestigationResult(InvestigationResult.TREATMENT);
        } else {
            patient.setInvestigationResult(InvestigationResult.HOSPITALIZE);
        }
    }
}
