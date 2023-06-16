package by.vstu.model.dictionary.competition;

public enum EducationForm {
    FULL_TIME_FORM("Дневная форма получения образования"), PART_TIME_FORM("Заочная форма получения образования");

    private String displayName;

    EducationForm(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
