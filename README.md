# Androidratinglibrary

A Test Programe on github.


![11](https://cloud.githubusercontent.com/assets/20965301/20809809/3887ecda-b82d-11e6-9bc3-af3cf4b8ea32.png)

![22](https://cloud.githubusercontent.com/assets/20965301/20809883/6ef8d2ca-b82d-11e6-80fc-30b1a6f15be2.png)


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


