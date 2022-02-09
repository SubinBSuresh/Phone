// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;
import ServicePackage.RecentModel;
// Declare any non-default types here with import statements

interface aidlInterface {

    void callNumber(String phoneNumber);
    List<ContactModel> getContacts();
List<SuggestionModel> getSuggestions(String searchedNumber);


    List<FavoritesModel> getAllFavorites();
      List<RecentModel> getAllRecents();

    void deleteFavorite(int id);


}