package by.vadarod.smartplan.entity.enums;

public enum TaskStatus {
    NEW("Новая"),
    ASSIGNED("Назначена"),
    IN_PROCESS("В работе"),
    CANCELLED("Отменена"),
    COMPLETED("Выполнена");

    private final String statusName;

    TaskStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
