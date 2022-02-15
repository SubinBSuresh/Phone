// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;
import ServicePackage.RecentModel;
// Declare any non-default types here with import statements

interface aidlInterface {

    void callNumber(String phoneNumber, String name);
    List<SuggestionModel> getSuggestions(String searchedNumber);
    List<ContactModel> getContacts();




      List<RecentModel> getAllRecents();

       List<FavoritesModel> getFavorites();
       void addContactToFavorites(int id);
       void removeContactFromFavorites(int id);
       boolean checkContactPresentInFavoritesTable(int id);
       void addToRecent(in ContactModel contact);


}