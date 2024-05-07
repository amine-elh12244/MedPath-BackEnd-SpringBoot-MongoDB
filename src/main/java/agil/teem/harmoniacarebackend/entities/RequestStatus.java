package agil.teem.harmoniacarebackend.entities;

public enum RequestStatus {
    DEMANDE_INITIEE(1, "Demande initiée"),
    DEMANDE_ASSIGNÉE(2, "Demande assignée"),
    ENLÈVEMENT_MÉDECIN(3, "Enlèvement médecin"),
    RÉCEPTION_LABO(4, "Réception labo"),
    EN_COURS_DE_TEST(5, "En cours de test"),
    RÉSULTAT_DISPONIBLE(6, "Résultat disponible"),
    ENLÈVEMENT_LABO(7, "Enlèvement labo"),
    RÉSULTAT_LIVRÉ(8, "Résultat livré"),
    RÉSULTAT_RÉCEPTIONNÉE(9, "Résultat réceptionnée");

    private final int id;
    private final String description;

    RequestStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public static RequestStatus getById(int id) {
        for (RequestStatus status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status ID");
    }

    public String getDescription() {
        return description;
    }
}