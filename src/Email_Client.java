import java.util.*;
class Email_Client {
    public static void main(String[] args) throws Exception {
        //getting all recipients to the program from the text file.
        RecipientList.getRecipientList();
        //deserializing all the sent mails.
        SentBox.deserialize();
        //code to send birthday wishes at the start of the program(only executes once a day)
        if (!DateTime.getDate().equals(SentBox.getLastWishDate())){
            for (Map.Entry<String, Recipient> mapElement : RecipientList.recipients.entrySet())
                mapElement.getValue().sendBirthdayWishes(DateTime.getDate());
            SentBox.updateLastWishDate();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "0 - Exit Email Client\n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");
        int option;
        while (true) {
            option = scanner.nextInt();
            while (option<0 || 5<option){
                System.out.println("Invalid Input!..Please enter a value between 0 and 5");
                option=scanner.nextInt();
            }
            switch (option) {
                case 0:
                    System.out.println("Email Client Stopped");
                    return;
                case 1:
                    //code to add a new recipient
// input format - Official,nimal,nimal@gmail.com,ceo
                    System.out.println("Insert Recipients in one of the following formats : \n\t" +
                            "1.Official,nimal,nimal@gmail.com,ceo\n\t" +
                            "2.OfficeFriend,kamal,kamal@gmail.com,clerk,2000/12/12\n\t" +
                            "3.Personal,sunil,<nick-name>,sunil@gmail.com,2000/10/10\n");
                    Scanner recipientScanner = new Scanner(System.in);
                    String newRecipient = recipientScanner.next();
                    RecipientList.addRecipient(newRecipient);
                    //updates the recipient list in the running program with the newly added recipient
                    RecipientList.getRecipientList();
                    break;
                case 2:
                    // input format - email, subject, content
                    Scanner mailScanner = new Scanner(System.in);
                    System.out.println("Insert Recipient email,subject,content :");
                    String line = mailScanner.nextLine();
                    String[] input = line.split(",");
                    Mail mail = new Mail();
                    mail.recipientAddress = input[0];
                    if (RecipientList.recipients.get(input[0]) != null) {
                        mail.recipientName = RecipientList.recipients.get(mail.recipientAddress).name;
                    } else {
                        mail.recipientName = null;
                    }
                    mail.subject = input[1];
                    mail.body = input[2];
                    mail.sentTime = DateTime.getTime();
                    Sender.sendMail(mail, false);
                    break;
                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    Scanner dateScanner = new Scanner(System.in);
                    System.out.println("Insert the date to check Birthdays : ");
                    String inputDate = dateScanner.next();
                    System.out.println("People With their Birthday on " + inputDate + " :");
                    for (Map.Entry<String, String> mapElement : RecipientList.checkBirthdays(inputDate).entrySet())
                    {
                        System.out.println(mapElement.getKey());
                    }
                    break;
                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    Scanner dateScanner2 = new Scanner(System.in);
                    System.out.println("Insert the date to check sent mail : ");
                    String date = scanner.next();
                    SentBox.sentOnDate(date);
                    break;
                case 5:
                    //code to print the number of recipient objects in the application
                    System.out.println("Number of recipients : " + RecipientList.numOfRecipients);
                    break;
            }
            System.out.println("==================================================\nEnter next Option : ");
        }
        //System.out.println("Email Client Stopped");
    }


}