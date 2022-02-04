package ServicePackage;

public class SuggestionModel {
    String contactName;
            String contactNumber;

    public SuggestionModel(String contactName, String contactNumber){
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public SuggestionModel() {

    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {

        return contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
