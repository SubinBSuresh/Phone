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

    void addContactToDatabase(in List<ContactModel> contactListDatabase);

    List<ContactModel> getContacts();

    boolean checkContactPresentInFavoritesTable(int id);

    void removeContactFromFavorites(int id);
//favourites
     List<FavoritesModel> getFavorites();
    void addContactToFavorites(int id);




//     void addToRecent(in ContactModel contact);
     List<RecentModel> getAllRecents();


    List<String> getList();



}