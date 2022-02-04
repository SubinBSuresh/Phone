package ServicePackage;


public class RecentsModel {
    String phnNumber, callDate;

    public RecentsModel(String phnNumber, String callDate) {
        this.phnNumber = phnNumber;
        this.callDate = callDate;
    }

    public String getPhnNumber() {
        return phnNumber;
    }

    public String getCallDate() {

        return callDate;
    }
}