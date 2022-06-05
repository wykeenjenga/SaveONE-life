# SaveOne-Life

## Running the project
> ### App testing
> - We tested the app using;
> - compileSdkVersion 31,
> -  buildToolsVersion "30.0.3",
> - minSdk 23,
> - targetSdk 31

## Installation by Downloading the APK file.
- To download the .apk click the link [SaveOne-Life](https://drive.google.com/file/d/1g1FYZzDRIGC-IxRjcsMFp3qduoH6_eYo/view?usp=sharing)
- Install the .apk on your android mobile phone
## Installation by Cloning to Android Studio.
- Download Android Studio
- Clonning the repo using the command ``` git clone https://github.com/wykeenjenga/SaveONE-life.git```
- Run the android app from Android Studio after cloning.

## Introduction
> - Did you know, there are about 153 million orphans worldwide.
> - According to Light Up Hope Organization, the population of orphaned children in Kenya is estimated to be at three million in about 800 orphanages. Every day, 700 children are orphaned (one every two minutes), with HIV/AIDS contributing to 1/3 of these orphans. As a result, the number of orphans is expected to increase. With the death of their parents who were the breadwinners, most children find themselves without sufficient financial, social, and emotional support and these children's futures become quite uncertain.
> - They end up straining to access basic needs such as food, clothing, course books, writing materials, and even shelter that are essential for normal living. In addition to that, orphans who have been traumatized by the death of their parents can become antisocial. The fact that society appears to have become oblivious to their predicament hasn't helped matters.
> - Those who have found themselves in children’s homes have been a little bit lucky to have some access to shelter, food, and clothing but in limited quantities compared to other children 
> - Most of the registered 800 children’s homes in Kenya depend on donors to provide these basic needs of these children and the finances are mostly very limited to providing food for all those 
children they are hosting


## Problem
> - The major problem gotten from the user requirement gathering phase, has been the donors having a hard time trying to locate the orphanages in the country, especially those that are really in interior areas. We got respondents, who were students, from our own campus who sometimes have food, clothing, and education materials to donate and they shared their difficulty of accessing the locations of the orphanages in the areas around. 
> - Also, the orphanages shared how it was difficult for them to find donors to support them and share the needs of various basic needs items of the children.

## Solution
> - That’s where the Save ONE-life android app comes in. -it’s an app built with java and google technology that helps donors to locate home orphanages all across the country and easen making of their donations from the comfort of their homes.Also, it makes easier for orphanages to share their needs on the platform in order to get support from donors.
> - Knowing which child requires help, when they require this help, and where they actually are in order to be accorded this help is a major step in alleviating the conditions of these orphaned children.
> - Our biggest desire is that orphaned children will experience a normal life just as children who have found themselves in their families.


## SDGs and their Targets
> SaveOne-Life is an android mobile application that helps solve three United Nations  Sustainable Development Goals(SDG). These goals are;
> #### SDG 1: No poverty
> - Target 1.2: By 2030, reduce at least by half the proportion of men, women, and children of all ages living in poverty in all its dimensions according to national definitions.

> #### SDG 2: Zero Hunger
> - Target 2.1: By 2030, end hunger and ensure access by all people, in particular the poor and people in vulnerable situations, including infants, to safe, nutritious, and sufficient food all year round. 

> #### SDG 4: Quality Education
> - Target 4.1: By 2030, ensure that all girls and boys complete free, equitable, and quality primary and secondary education leading to relevant and effective learning outcomes.


## Scalability of the project.
> SaveONE Life project is scalable,
> We are planning to implement an additional feature regarding the health of the children, whereby Orphanage managers can add and indicate the number of children who are not in good health, thus also being able to integrate the goodwill health organizations to offer free medical services and even hold medical camps.
>  This goodwill health organizations will also be able to log into the app and locate those orphaned children that are in need.

> M-Pesa is a virtual banking system that provides transaction services through a SIM card. Once the SIM has been inserted into the card slot of the mobile device, users can make payments and transfer money to vendors and family members with SMS messages. 
> We are planning to integrate M-Pesa APIs with our app so that it can be easy for donors to donate with M-Pesa with a single touch from the app.
> We are also planning to add more methods/mediums of donations like stripe, PayPal, and many others. 

> We are also planning to extend the app from being used inside the country to the whole of Africa and the whole world as well.

## App Testing.
> - We tested SaveONE-life App with two children's homes as shown on the screenshots below.
> - Tumaini Children's home - is an orphanage located along Nairobi Mombasa road Voi, the orphanage has a capacity of 55 orphaned children. Contact +254724723901.
> - GRACE MWATATE CHILDREN'S HOME - Is an orphanage located along Voi - Mwatate road that was initiated way back in 2008, the orphanage holds 41 orphaned children, Contact +254710153812.
> - The orphanage managers were very excited because they will be able to share their needs via SaveOne-life with the community. Their wish is for the project to succeed for the benefit of the young generation, for a real experience you can download SaveONE life from [Set Up](#SaveOne-Life)

<img src="https://user-images.githubusercontent.com/46722362/160270804-d985e630-880e-47dd-9f8b-11243acf9797.png" data-canonical-src="https://user-images.githubusercontent.com/46722362/160270804-d985e630-880e-47dd-9f8b-11243acf9797.png" width="200" height="400" />


## Technology
SaveOne-Life is an android mobile application developed using Android Studio, the Programming language used is JAVA and at the backend, we use Google Technology.

## Google Technology Overview
> ### Firebase Auth.
> - We used *firebase auth* to register and login user to the app. Where by donors can opt to register with or continue with google as shown in the screenshot below.
> - <img src="https://user-images.githubusercontent.com/46722362/159160999-a2f9614c-be49-4ee3-ba54-da694318dfdf.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/159160999-a2f9614c-be49-4ee3-ba54-da694318dfdf.png"
       width="240" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/159161005-e108a376-1e2e-4fda-b45f-d068dffff38e.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/159161005-e108a376-1e2e-4fda-b45f-d068dffff38e.png"
       width="240" height="450" />

> ### Firebase Firestore.
> - We used firebase Firestore to store orphanages, users/donors, location, and chats message from the forum data so as we can access data from the backend with ease. 

> ### Firebase functions and Google cloud.
> - We used firebase functions to listen to any changes on Chats collection from Firestore that is when a new message is added or sent the function is supposed to trigger a push notification to the users subscribed to the topic on the SaveONE life forum.
> - We used Google cloud to write and deploy the function.

> ### Firebase cloud messaging.
> - We used firebase cloud messaging dependency to send push notifications when there is a new message in the SaveONE life forum.

> ### Google cloud.
> - Google cloud was used to create Google Maps API which helped us to implement Google maps on our project.

> ### Firebase Storage.
> - This dependency helped us to store user profile images when the orphanage uploads their group photo.



# App functionality and Google technology application
> ### Landing Page
> <img src="https://user-images.githubusercontent.com/46722362/159177572-b96ff294-b3c4-4495-9884-ffdf931cffbb.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/159177572-b96ff294-b3c4-4495-9884-ffdf931cffbb.png"
       width="240" height="460" />

- On the landing page we have 4 buttons which include Donate Now, Register an Orphanage, Register as a donor, and Login button.

## Authentication
> ## Donor Authentication 
> -  Donor - To authenticate donor we used firebase auth dependecy (firebase-auth). 
```
implementation 'com.google.firebase:firebase-auth:21.0.1'

```
- Donors can opt to Donate without an account, but this has limitations for example he/she(Donor) will not be able to participate on [SaveONE -life forum](#Home Page).
> - If donors wish to participate on the SaveONE life forum, he/she must have an account.

> ## Orphanage / Children's Home Authentication 
> - To authenticate an orphanage, the Orphanage manager is supposed to register the orphanage by clicking the Register button, he will pass through 4 different sections as shown below.
> - An orphanage is required to be verified to start receiving donations.
> - Verification process requires an orphanage to channel $5 (annually) and the exact location of the orphanage if it was not provided.
> - The verification process will help donors to be more convinced that the orphanage exists.
> - Channeled ($5) money is used by TTU GDSC for the maintenance of SaveONE's life App by improving the service of the app. Money can be channeled using M-Pesa at the moment.
> - Below are the profile pages for the verified and not verified orphanage.
> - <img src="https://user-images.githubusercontent.com/46722362/160561081-1329e1c0-faf2-4ec1-887e-c34ae77e823b.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561081-1329e1c0-faf2-4ec1-887e-c34ae77e823b.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/159165000-a3131ab2-6075-47a6-955c-452b38654155.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/159165000-a3131ab2-6075-47a6-955c-452b38654155.png"
       width="220" height="450" />

### 1. Basic Information 
- On this page Orphanage manager is supposed to fill in the name, email, and password of the orphanage as shown.
- The following dependencies were used.

```
implementation 'com.google.firebase:firebase-storage:20.0.0'
implementation 'com.google.firebase:firebase-database:20.0.3'
implementation 'com.google.firebase:firebase-firestore:24.0.1'
implementation 'com.google.firebase:firebase-auth:21.0.1'
```
- We used the Firestore database with a collection name Orphanage to store orphanage data.
- Orphanage data is stored as a HashMap on the Firestore database.

       
### 2. Additional Information
-  On this page the Orphanage manager is supposed to upload an Orphanage Group Photo, fill in the Phone number, Till number, Bank account number, Bank Name, and country where the orphanage is located.
- On this page, we used firebase storage dependency and storage reference class so as to enable users to upload group photos to the firebase storage database.
- On this fragment, we will create a sharable link of the image uploaded.

### 3. Other Information
- On this page Orphanage manager is supposed to fill in a brief description of the orphanage, the number of children in the orphanage, what they need most (include food, water, clothes, money for school fees etc.), and the location address of the orphanage. 

<img src="https://user-images.githubusercontent.com/46722362/158599594-b612623f-3de1-447e-b65a-1d48805fcc24.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158599594-b612623f-3de1-447e-b65a-1d48805fcc24.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/158608189-5a1560c6-0f69-4b70-9d99-5fb20b8a9731.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158608189-5a1560c6-0f69-4b70-9d99-5fb20b8a9731.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/158609052-5ab9ab48-04a3-4dc2-baa0-e05c88a33f39.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158609052-5ab9ab48-04a3-4dc2-baa0-e05c88a33f39.png"
       width="220" height="450" />

       
- ### 4. Orphanage location
- Orphanage location fragment shows a google map showing the current location of the user.
- Orphanage manager is supposed to drag and drop the pin to the actual position of the orphanage and get the current coordinates, on button click, coordinates are recorded to the Firestore database.
- We used google map API, the following dependencies were used  
- We created Google Map Api from Google clouds
 ```
 build.grandle(:app)
 dependencies{
 implementation 'com.google.android.gms:play-services-maps:18.0.2'
 implementation 'com.google.android.gms:play-services-location:19.0.1'
 }
 
 
 //on the manifest file  we added a meta data with a string of API key value 
 <meta-data
      android:name="com.google.android.geo.API_KEY"
      android:value="@string/map" />
      
 //This legacy name (com.google.android.geo.API_KEY)  allows authentication to the Android Maps API
 ```
 
<img src="https://user-images.githubusercontent.com/46722362/158611030-d17d2023-3971-4d5c-9331-f1b152c09775.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158611030-d17d2023-3971-4d5c-9331-f1b152c09775.png"
       width="210" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/158611060-44edd280-e8ad-45b8-8aa5-6116edbcb019.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158611060-44edd280-e8ad-45b8-8aa5-6116edbcb019.png"
       width="210" height="450" />
       
```
NB: When an orphanage is registered it should be verified and in the future we are planning to create an admin app for the orphanage verification, for the moment we are verifying orphanages from the backend. The process of verification may take 2 to 3 days. 
```

## Home Page
- On the home page, we have 3 fragments.
- We used tab layout to show the 3 pages, which are;

> ### 1. Home
> - On the home page, we are fetching orphanages data from the Firestore database, and presenting them at the frontend as card view in the recycler view.
> - Each card view is showing some important details about the orphanage which include name, location address, number of children in the orphanage, and a donate button at the bottom.
> - When donating here button is clicked it shows the detailed fragment about the orphanage and a donate floating action button at the bottom.
> -  On this detailed fragment page it shows a map view with a marker showing the exact location of the orphanage home whereby if you click on marker it shows the direction to the orphanage location on Google map (but in future, we are planning to show it on the map).
> - Detailed fragment shows more about the orphanage, contacts, and donations medium which are a bank, phone number (can be used to call the orphanage when clicked) and donate na M-Pesa.
> - The above methods are used for the liquid donation (eg money etc), while the floating action button at the bottom opens a dialog for the solid donations which include *(clothing, beddings, foodstuffs, health services, and educational materials )* as shown in the screenshot (3).
> - When the donor donates the above items, Data is recorded to the Firestore database with the name **Donations** and a document name of the orphanage email.
> - Below screenshots shows the whole process.

<img src="https://user-images.githubusercontent.com/46722362/158621972-77ce0bd3-b684-4fcb-84e8-fa190adf9c8c.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158621972-77ce0bd3-b684-4fcb-84e8-fa190adf9c8c.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/159177583-4c26811e-eb00-4ac3-b23c-f3c2cc3de287.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/159177583-4c26811e-eb00-4ac3-b23c-f3c2cc3de287.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/159177585-059e2723-b8d6-4fae-a8ff-50b026d4391e.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/159177585-059e2723-b8d6-4fae-a8ff-50b026d4391e.png"
       width="220" height="460" />


> ### 2. MapView
> - SaveONE life map view shows the different locations of the orphanages across east Africa.
> - Orphanages are being represented by a custom marker as shown in the screenshots
> - On this fragment, we used a Google Map SDK whereby data (Location Coordinates) are fetched from the Firestore database which includes coordinates and the name of the orphanage.
> - On this Fragment user or donor is able to search for the orphanage by name and navigate to the location using Google Maps.
> - We created a map API from Google cloud.
> - We used a model view to store fetched data as a List 
> 
```
List<LocationModel> listdata;//model

...get data from firestore...
for (int i = 0; i < listdata.size(); i++) {
            //add makers
            googleMap.addMarker(new MarkerOptions()
                    .position(listdata.get(i).getLatLng())
                    .title(listdata.get(i).getTitle())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                            new getBitmap().getBitmap(String.valueOf(R.drawable.custom_maker),
                                            120,120, getContext()))));
                                            }
```
> - Here is a screenshot showing orphanages from different locations.

<img src="https://user-images.githubusercontent.com/46722362/160268874-3bf1a123-0be8-4eb3-ab16-6bc73d0f60f3.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160268874-3bf1a123-0be8-4eb3-ab16-6bc73d0f60f3.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160268952-582a6c76-4716-4776-b98f-99fdd4938e53.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160268952-582a6c76-4716-4776-b98f-99fdd4938e53.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160269034-b670c35c-5509-4b4f-b3a9-cf487f644caf.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160269034-b670c35c-5509-4b4f-b3a9-cf487f644caf.png"
       width="220" height="450" />


> ### 3. Public Forum (SaveONE life)
> Is a platform whereby the public and all communities can chat and give reviews or comments about orphanage homes.
> On this fragment we used;
> #### Firestore database for storing messages
> - With a collection name (Chats), time+date as the document path.
> - Messages are saved as a document, which includes (message, email, name, time, and data).
> - To send a message you can either type or use Google Speech Recognizer.
> - Also users can opt to share images and documents to the community forum. 
> - Images are stored in firebase storage.
 ```
 ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
 message.setText(result.get(0).toString().toLowerCase());
```
> - Only logged users are allowed to send a message.
> - At first onCreateView, we retrieve messages from Firestore, we used an adapter and recycler view to hold data.

> #### Google Cloud Function
> - We used a google cloud function to listen to any changes on Chats collection when a message is written or deleted.
> - This function will trigger a firebase push notifications to those users that are subscribed to the topic.
> - We used the code below for push notifications
```
const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

exports.androidPushNotification = functions.firestore.document('Chats/{uid}').onWrite( async (event) => {
  
  let t = "You have a new message";
  let c = "Hello there you have a new message from SaveONE life Forum";

  var message = {
    notification: {
      title: t,
      body: c,
    },

    topic: 'chats',
    
    };

});
```

> #### Firebase Cloud Messaging
> - To send push notifications to the user's we used ``` implementation 'com.google.firebase:firebase-messaging' ``` dependecy and a Chat notification class that extend FirebaseMessagingService and overrided onMessageReceived to handle everything.
> ##Screenshots
>
> <img src="https://user-images.githubusercontent.com/46722362/160269397-dabe47e5-e454-4050-95e4-630b44ccbe77.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160269397-dabe47e5-e454-4050-95e4-630b44ccbe77.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160269807-b26b0409-21c9-42f6-b8f2-98100aecd1aa.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160269807-b26b0409-21c9-42f6-b8f2-98100aecd1aa.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160269648-7b518f29-5f4e-48c0-9ab9-781d89e0fae4.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160269648-7b518f29-5f4e-48c0-9ab9-781d89e0fae4.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160269675-f64b4e01-c77a-450e-bb37-78e285602b01.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160269675-f64b4e01-c77a-450e-bb37-78e285602b01.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/158769919-a05a1bcc-79cd-44c3-8676-0b4ac8175cc7.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158769919-a05a1bcc-79cd-44c3-8676-0b4ac8175cc7.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/158769975-8b7b6753-261b-4645-8b0f-9822546bc6ab.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/158769975-8b7b6753-261b-4645-8b0f-9822546bc6ab.png"
       width="220" height="450" />


## Orphanage Profile Page
> - *On the orphanage profile page,* Orphanage manager/ Admin will be able to update the profile and other information which include ( group photo, number of children, phone number, and other account information)
> - On this page Orphanage manager will also update location of the orphanage.
> - Also orphanages will be able to query through and see the donations they have received, this data is restored from the firebase Firestore.
> - He/She will be able to check and call donors on this fragment (Screenshot no. 5). And if orphanage have not received donations orphanage manager will be able to check in the donations they have received.
> - User / Orphanage manager can log out on this page.
> - 

<img src="https://user-images.githubusercontent.com/46722362/160561055-52bedf4e-3764-4b0f-a829-3edeb1a44c20.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561055-52bedf4e-3764-4b0f-a829-3edeb1a44c20.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160561081-1329e1c0-faf2-4ec1-887e-c34ae77e823b.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561081-1329e1c0-faf2-4ec1-887e-c34ae77e823b.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160561085-4d51de8b-c7ec-49b2-b809-6bad4a658cff.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561085-4d51de8b-c7ec-49b2-b809-6bad4a658cff.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160561089-eb2921b5-121f-4017-933a-51b0dc17e39d.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561089-eb2921b5-121f-4017-933a-51b0dc17e39d.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160561092-8d6a21f0-1eab-4e14-85b0-757faa30890e.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561092-8d6a21f0-1eab-4e14-85b0-757faa30890e.png"
       width="220" height="450" />
       <img src="https://user-images.githubusercontent.com/46722362/160561095-9b7de3f4-9d46-4553-ba00-37f06c81bb7b.png"
      data-canonical-src="https://user-images.githubusercontent.com/46722362/160561095-9b7de3f4-9d46-4553-ba00-37f06c81bb7b.png"
       width="220" height="450" />
## Orphanage Images
<p float="left">
<img src="https://user-images.githubusercontent.com/49823575/172049694-21391f1a-95fd-4e7b-adaf-c8ffc00fc6a8.jpg" data-canonical-src="https://user-images.githubusercontent.com/49823575/172049694-21391f1a-95fd-4e7b-adaf-c8ffc00fc6a8.jpg" width="330" height="450">
<img src="https://user-images.githubusercontent.com/49823575/172049851-742fcd93-42ff-4143-835d-f63409fe8977.jpg" data-canonical-src="https://user-images.githubusercontent.com/49823575/172049851-742fcd93-42ff-4143-835d-f63409fe8977.jpg" width="330" height="450">
<img src="https://user-images.githubusercontent.com/49823575/172050107-45fbc438-28f9-4931-a724-a1b5c63cf777.jpg" data-canonical-src="https://user-images.githubusercontent.com/49823575/172050107-45fbc438-28f9-4931-a724-a1b5c63cf777.jpg" width="330" height="450">
      </p>
       
## Thanks <span style="color: #e25555;">&hearts;</span>
Thanks to Google Developer Students Club for contributing towards the growth of [@DSCTTU](https://twitter.com/DscTtu?t=nLFp2oGleW6Tpu3XpzbugQ&s=09).



## License
```
MIT License

Copyright (c) 2022 GDSC Taita Taveta University.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```
