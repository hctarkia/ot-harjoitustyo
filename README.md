# Matopeli

Sovellus on matopeli, jossa pelaaja liikuttaa matoa nuolinäppäimillä tavoitteenaan syödä ruokaa kentältä saadakseen pisteitä.

## Dokumentaatio

[Käyttöohje](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testaus](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Tuntikirjanpito](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/hctarkia/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/hctarkia/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/hctarkia/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Testaus

Testien suoritus komennolla:

```
mvn test
```

Testikattavuusraportti komennolla:

```
mvn test jacoco:report
```

### Jarin generointi

Suoritettavan jarin generointi komennolla:

```
mvn package
```

Komento generoi hakemistoon target suoritettavan jar-tiedoston Snake-1.0-SNAPSHOT.jar

### Checkstyle

Checkstyle-raportin generointi komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Komennon suoritettuasi löydät raportin polusta /target/site/checkstyle.html

### JavaDoc

JavaDocin luominen onnistuu komennolla:

```
mvn javadoc:javadoc
```

Komennon suoritettuasi löydät JavaDocin polusta /target/site/apidocs/index.html

