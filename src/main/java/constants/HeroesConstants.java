package constants;

public enum HeroesConstants {
    HEROES_ENDPOINT_LOCAL("/heroes"),
    ENDPOINT_DYNAMODB("https://localhost:8000"),
    REGION_DYNAMODB("us-east-1");

    private final String value;

    HeroesConstants(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }
}
