// aidlInterface.aidl
package ServicePackage;
import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;
// Declare any non-default types here with import statements

interface aidlInterface {

  String getText();
       void placeCall(String number);
       List<String> getList();

       //parcelables
       //List<RecentModel> getAllRecents();
       List<ContactModel> getAllContacts();
       List<FavoritesModel> getAllFavorites();

       void deleteFavorite(int id);
             void addToFavorite(in ContactModel contact);
             //void addToRecent(in ContactModel contact);


  }