import java.io.Serializable;

class Mail implements Serializable {
    //Mail Class stores all the data of a mail when its being sent
    public String recipientName;
    public String recipientAddress;
    public String subject;
    public String body;
    public String sentTime;
}