class Official extends Recipient {
    String designation;
    public Official(String[] str) {
        this.name = str[1];
        this.email = str[2];
        this.designation = str[3];
    }
    @Override
    void sendBirthdayWishes(String date) {
    }
}