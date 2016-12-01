# Androidratinglibrary

A Test Programe on github.

<p align="center">
  <img src="https://github.com/amitKumartomar/Androidratinglibrary/tree/master-SNAPSHOT/1.jpg" width="350"/>
  <img src="https://github.com/amitKumartomar/Androidratinglibrary/tree/master-SNAPSHOT/2.jpg" width="350"/>
</p>

# Install
```sh
To use the library, first include it your project using Gradle

allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

dependencies {
        compile  'com.github.amitKumartomar:Androidratinglibrary:master-SNAPSHOT'
}
```

# Usages
```sh
//Include below code in actvity onCreate() function 
RatingDialog rating = new RatingDialog(this);
        rating.setratingTitle("Rate Us!!");
        rating.setratingText("If you Like this app,Give Us 5 star Rating!!,Thanks!!");
        //rating.setShowStar(false);//Mark "true",if want to show "stars" in rating. 
        rating.setShowAfter(1);//after how many application launch(onCreate),dialog should pop up
```


