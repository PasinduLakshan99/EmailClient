import java.io.*;
import java.util.HashMap;
import java.util.Map;

class RecipientList {
    static HashMap<String, Recipient> recipients;
    static Integer numOfRecipients = 0;
    public static void getRecipientList() {
        //reads the recipient text file and stores Recipients objects in a Hash Map
        numOfRecipients = 0;
        recipients = new HashMap<String, Recipient>();
        try {
            FileReader reader = new FileReader("recipient_list.txt");
            BufferedReader buffReader = new BufferedReader(reader);
            String line;
            while ((line = buffReader.readLine()) != null) {
                String[] str = line.split(",");
                if (str[0].equals("Official")) {
                    Official res = new Official(str);
                    recipients.put(res.email, res);
                    numOfRecipients += 1;
                }
                if (str[0].equals("OfficeFriend")) {
                    OfficeFriend res = new OfficeFriend(str);
                    recipients.put(res.email, res);
                    numOfRecipients += 1;
                }
                if (str[0].equals("Personal")) {
                    Personal res = new Personal(str);
                    recipients.put(res.email, res);
                    numOfRecipients += 1;
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\n!!!!!Cannot find file 'recipient_list.txt'!!!!!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    static void addRecipient(String newRecipient) {
        //take details of a new recipient as user input and stores it in the "recipient_list.txt"
        try {
            FileWriter writer = new FileWriter("recipient_list.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.newLine();
            bufferedWriter.append(newRecipient);
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    static HashMap<String, String> checkBirthdays(String birthday) {
        //takes a date as input and prints all recipients with birthday on given date
        HashMap<String, String> birthdayPeople = new HashMap<String, String>();
        for (Map.Entry<String, Recipient> mapElement : recipients.entrySet()) {
            if (mapElement.getValue() instanceof OfficeFriend) {
                OfficeFriend recipient = (OfficeFriend) mapElement.getValue();
                if (recipient.birthday.substring(6).equals(birthday.substring(6)))
                    birthdayPeople.put(recipient.name, recipient.email);
            }
            if (mapElement.getValue() instanceof Personal) {
                Personal recipient = (Personal) mapElement.getValue();
                if (recipient.birthday.substring(6).equals(birthday.substring(6)))
                    birthdayPeople.put(recipient.name, recipient.email);
            }
        }
        return birthdayPeople;
    }
}