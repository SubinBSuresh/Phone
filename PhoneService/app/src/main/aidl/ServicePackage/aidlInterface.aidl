// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;
import ServicePackage.RecentsModel;
// Declare any non-default types here with import statements

interface aidlInterface {

    void callNumber(String phoneNumber);
    List<ContactModel> getContacts();
    List<SuggestionModel> getSuggestions();


    List<FavoritesModel> getAllFavorites();

    void deleteFavorite(int id);

List<RecentsModel> fetchCallLogs();
}