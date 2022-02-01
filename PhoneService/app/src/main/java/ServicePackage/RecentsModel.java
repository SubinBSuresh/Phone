package ServicePackage;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecentsModel implements Parcelable {
    private int id;
    private String name, number, date;

    protected RecentsModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        number = in.readString();
        date = in.readString();
    }

    public static final Creator<RecentsModel> CREATOR = new Creator<RecentsModel>() {
        @Override
        public RecentsModel createFromParcel(Parcel in) {
            return new RecentsModel(in);
        }

        @Override
        public RecentsModel[] newArray(int size) {
            return new RecentsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecentsModel(String name, String number, String date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }

    public RecentsModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy MMMM dd hh:mm:ss");
        this.date = dateFormat.format(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(date);
    }
}
