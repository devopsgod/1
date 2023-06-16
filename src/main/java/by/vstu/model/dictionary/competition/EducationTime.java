package by.vstu.model.dictionary.competition;

public enum EducationTime {
    FULL_TIME("Полный срок получения образования"), REDUCED_TIME("Сокращенный срок получения образования");

    private String displayName;

    EducationTime(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
