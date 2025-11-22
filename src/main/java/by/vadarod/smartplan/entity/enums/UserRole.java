package by.vadarod.smartplan.entity.enums;

public enum UserRole {
    USER("Пользователь"),
    ADMIN("Администратор");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
