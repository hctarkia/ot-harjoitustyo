# Matopeli

Sovellus on matopeli, jossa pelaaja liikuttaa matoa nuolinäppäimillä tavoitteenaan syödä ruokaa kentältä saadakseen pisteitä.

## Dokumentaatio

[Käyttöohje](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md) (tekemättä)

[Tuntikirjanpito](https://github.com/hctarkia/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/hctarkia/ot-harjoitustyo/releases/tag/viikko5)

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
