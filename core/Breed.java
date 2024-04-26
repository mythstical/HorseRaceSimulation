public enum Breed {
    ARABIAN_HORSE("Arabian Horse"),
    MUSTANG("Mustang"),
    SHIRE_HORSE("Shire Horse"),
    BRETON_HORSE("Breton Horse");

    public final String value;

    private Breed(String value) {
        this.value = value;
    }
}
