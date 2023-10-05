class Personal extends Recipient {
    String nickname;
    String birthday;
    public Personal(String[] str) {
        this.name = str[1];
        this.nickname = str[2];
        this.email = str[3];
        this.birthday = str[4];
    }
    @Override
    void sendBirthdayWishes(String date) {
        if (date.substring(6).equals(birthday.substring(6))) {
            Mail mail = new Mail();
            mail.recipientAddress = this.email;
            mail.subject = "Happy Birthday";
            mail.body = "Hugs and Love on your Birthday !";
            mail.recipientName = this.name;
            try {
                Sender.sendMail(mail, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}