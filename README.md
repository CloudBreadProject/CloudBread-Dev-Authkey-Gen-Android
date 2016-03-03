# CloudBread-Dev-Authkey-Gen-Android
This porject is authentication provider authkey development &amp; testing project for CloudBread game server engine 

##Installation guide
Fork this project to your repository and open it in *Android Studio*. 

Change *MainActivity.java* file code to your Authenticated Server
```
mClient = new MobileServiceClient(
     "https://yourservername.azurewebsites.net",
    this
);
```

Change *MainActivity.java* file property of *MobileServiceAuthenticationProvider* to your authentication setting.
```
ListenableFuture<MobileServiceUser> mLogin = mClient.login(MobileServiceAuthenticationProvider.Facebook);
```

Build and execute app. Click Generate auth key and logon to authentication provider. 
Now you should copy *auth key* for deveopment purpose and paste the key at Postman request header *x-zumo-auth* value.

For more information, follow up the official project wiki document 
https://github.com/CloudBreadProject/CloudBread/wiki 

License : MIT
