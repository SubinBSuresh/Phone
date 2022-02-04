package ServicePackage;

import android.os.Parcel;
import android.os.Parcelable;

public class SuggestionModel implements Parcelable {
    String contactName;
            String contactNumber;

    public SuggestionModel(String contactName, String contactNumber){
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public SuggestionModel() {

    }

    protected SuggestionModel(Parcel in) {
        contactName = in.readString();
        contactNumber = in.readString();
    }

    public static final Creator<SuggestionModel> CREATOR = new Creator<SuggestionModel>() {
        @Override
        public SuggestionModel createFromParcel(Parcel in) {
            return new SuggestionModel(in);
        }

        @Override
        public SuggestionModel[] newArray(int size) {
            return new SuggestionModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(contactName);
        parcel.writeString(contactNumber);
    }
}
