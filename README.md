# Movie search using clean architecture

[![Build Status](https://travis-ci.com/rezaiyan/Moviz.svg?token=UzctkdBEqv7pYmuaDqu6&branch=master)](https://travis-ci.com/rezaiyan/Moviz)

Libraries used
----

- [Koin](https://github.com/InsertKoinIO/koin)
- [Retrofit](http://square.github.io/retrofit/)
- [Coroutine](https://github.com/Kotlin/kotlinx.coroutines)
- [Kluent](https://github.com/MarkusAmshove/Kluent)
- [Mockito-kotlin](https://github.com/nhaarman/mockito-kotlin)
- [Robolectric](https://github.com/robolectric/robolectric)

## Setting up your OMDB API KEY

_We use the wonderful [OMDB api](http://www.omdbapi.com) to fetch movie information._

There are quotas on this api, so please don't use mine :)

1. Get an [api key for OMDB here](http://www.omdbapi.com/apikey.aspx)
2. Run this in a terminal like application

```
touch $HOME/.gradle/gradle.properties
echo "omdb_apikey=\"<API_KEY_GOES_HERE>\"" >> $HOME/.gradle/gradle.properties
```

You can read [this post for instructions](https://medium.com/code-better/hiding-api-keys-from-your-android-repository-b23f5598b906) on this private api
setting up process.

