package documentation;

public enum Developer {
    ROBIN("RobinvanKaathoven"), PAUL("pebeling"), MERON("meronbrouwer"), MARCEL("mgoris"), DAAN("dvberkel");

    private final String githubUsername;

    Developer(String githubUsername) {
        this.githubUsername = githubUsername;
    }
}
