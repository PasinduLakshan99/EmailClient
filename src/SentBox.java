import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class SentBox {
    static HashMap<String, ArrayList<Mail>> sentMails = new HashMap<String, ArrayList<Mail>>();
    public static void serialize(Mail mail) {
        mail.sentTime = DateTime.getTime();
        ArrayList<Mail> maillist = sentMails.get(DateTime.getDate());
        if (maillist == null)
            sentMails.put(DateTime.getDate(), new ArrayList<Mail>());
        sentMails.get(DateTime.getDate()).add(mail);
        try {
            //Creating FileOutputStream object.
            FileOutputStream fos = new FileOutputStream("SentBox.ser");
            //Creating ObjectOutputStream object.
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //write object.
            oos.writeObject(sentMails);
            //close streams.
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void deserialize() {
        try {
            //Creating FileInputStream object.
            FileInputStream fis = new FileInputStream("SentBox.ser");
            //read ObjectOutputStream object.
            ObjectInputStream ois = new ObjectInputStream(fis);
            //read object.
            sentMails = (HashMap<String, ArrayList<Mail>>) ois.readObject();
            //close streams.
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("!!!!!Cannot find file 'SentBox.ser'!!!!!");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void sentOnDate(String date) {
        //if no mails were sent on the input date,print a message and return
        if (!sentMails.containsKey(date)){
            System.out.println("No mail history available for the given date");
            return;
        }
        //if mails sent on the input date print details of mails
        System.out.println("\nMails sent on " + date + " :\n");
        for (Mail mail : sentMails.get(date)) {
            System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+"
                    + "\nRecipient name = " + mail.recipientName
                    + "\nSubject = " + mail.subject
                    + "\nContent = " + mail.body
                    + "\nSent At = " + mail.sentTime
                    + "\n-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
        }
    }
    public static String getLastWishDate(){
        String date=null;
        try {
            FileReader reader = new FileReader("LastWishDate");
            BufferedReader buffReader = new BufferedReader(reader);
            date=buffReader.readLine();
            reader.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("!!!!!Cannot find file 'LastWishDate.txt'!!!!!");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return date;
    }
    public static void updateLastWishDate(){
        try {
            //updates the last day that the client sent birthday wishes
            FileWriter writer = new FileWriter("LastWishDate");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(DateTime.getDate());
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}