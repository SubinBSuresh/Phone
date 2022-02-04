// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
// Declare any non-default types here with import statements

interface aidlInterface {

    void callNumber(String phoneNumber);
    List<ContactModel> getContacts();


    List<FavoritesModel> getAllFavorites();

    void deleteFavorite(int id);

//    Cursor fetchCallLogs();

}