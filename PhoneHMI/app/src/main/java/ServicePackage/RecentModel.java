package ServicePackage;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecentModel implements Parcelable {
    private int id;
    private String name, number, date;

    protected RecentModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        number = in.readString();
        date = in.readString();
    }

    public static final Creator<RecentModel> CREATOR = new Creator<RecentModel>() {
        @Override
        public RecentModel createFromParcel(Parcel in) {
            return new RecentModel(in);
        }

        @Override
        public RecentModel[] newArray(int size) {
            return new RecentModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public RecentModel() {

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
