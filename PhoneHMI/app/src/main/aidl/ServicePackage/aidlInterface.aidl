// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
// Declare any non-default types here with import statements

interface aidlInterface {

    void callNumber(String phoneNumber);
    List<ContactModel> getContacts();
    Cursor FetchCallLogs();
}