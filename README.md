# Phone
Simple Phone app implemented using AIDL with dialer, recents, favorites and contacts using SQLite Database and Content Provider.
This repository contains two applications namely PhoneHMI and PhoneService.
PhoneHMI refers to the client side application and PhoneService refers to the server side application.
Clone this reopository and open the both aplications seperately.
Run the PhoneService application first, which shows a blank activity.
Then run the PhoneHMI application, which is where the User interacts with the application.
Phone application will launch in landscape mode by default
Contacts data is taken from the AVD using content provider and stored in SQLite Database.
The Contact List is showing using data from SQLite.
Application contains three databases for contacts, favorites and recents.
Contact Suggestion is taken from Contacts Database.
By default, call screen is a dummy screen,actual code for making call is commented out which is available in the files.
