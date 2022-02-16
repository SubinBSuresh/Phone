// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;
import ServicePackage.RecentModel;
// Declare any non-default types here with import statements

interface aidlInterface {
    void addContactToDatabase(in List<ContactModel> contactListDatabase);

    void callNumber(String phoneNumber, String name);
    void removeContactFromFavorites(int id);

    void addContactToFavorites(int id);
    boolean checkContactPresentInFavoritesTable(int id);


    List<SuggestionModel> getSuggestions(String searchedNumber);
    List<ContactModel> getContacts();
     List<FavoritesModel> getFavorites();
     List<RecentModel> getAllRecents();
    List<String> getList();




//favourites




//     void addToRecent(in ContactModel contact);





}