// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;
import ServicePackage.RecentsModel;
// Declare any non-default types here with import statements

interface aidlInterface {

    void callNumber(String phoneNumber);
    List<String> getList();
    List<ContactModel> getContacts();
    List<SuggestionModel> getSuggestions(String searchedNumber);
     List<FavoritesModel> getAllFavorites();

    void deleteFavorite(int id);
//    void addToFavorite(ContactModel contacts);

List<RecentsModel> fetchCallLogs();
}