package by.vadarod.smartplan.entity.enums;

public enum TaskPriority {
    LOW("Низкий"),
    MEDIUM("Средний"),
    HIGH("Высокий");

    private final String priorityName;

    TaskPriority(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getPriorityName() {
        return priorityName;
    }

    @Override
    public String toString() {
        return priorityName;
    }
}
