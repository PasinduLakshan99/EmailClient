class OfficeFriend extends Recipient {
    String designation;
    String birthday;
    public OfficeFriend(String[] str) {
        this.name = str[1];
        this.email = str[2];
        this.designation = str[3];
        this.birthday = str[4];
    }
    @Override
    void sendBirthdayWishes(String date) {
        if (date.substring(6).equals(birthday.substring(6))) {
            Mail mail = new Mail();
            mail.recipientAddress = this.email;
            mail.recipientName = this.name;
            mail.subject = "Happy Birthday";
            mail.body = "Wish you a Happy Birthday!\n-Pasindu_Lakshan-";
            try {
                Sender.sendMail(mail, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}