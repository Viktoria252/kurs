class User {
    private String username;
    private String email;
    protected String password;
    private boolean isActive;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = false;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        this.isActive = true;
    }
}