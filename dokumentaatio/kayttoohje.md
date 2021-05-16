# Käyttöohje

Lataa tiedosto [Snake-1.0-SNAPSHOT.jar](https://github.com/hctarkia/ot-harjoitustyo/releases/tag/loppupalautus)

## Konfigurointi

Ohjelma tarvitsee käynnistyshakemistoonsa tiedoston "config.properties" toimiakseen oikein.

Lataa tiedosto [config.properties](https://github.com/hctarkia/ot-harjoitustyo/releases/tag/loppupalautus) sovelluksen käynnistyshakemistoon.

Tiedoston sisältö:

```
highscores=highscores.db
test=test.db
```

## Ohjelman käynnistäminen

Ohjelma tarvitsee tiedoston config.properties juurikansioon, että tietokantaan voidaan tallentaa tuloksia.

Ohjelma käynnistetään komennolla:

```
java -jar Snake-1.0-SNAPSHOT.jar
```

## Pelin aloittaminen

Pelin voi aloittaa painamalla hiirellä nappia "Aloita peli".

## Pelin pelaaminen

Matoa ohjataan nuolinäppäimillä tarkoituksena kasvattaa pisteitä syömällä kentältä ruokaa.

## Pelin päätyttyä

Voit kirjata pisteet tietokantaan lisäämällä nimen ja painamalla "Lisää pisteet" tai palata päävalikkoon napista "Valikkoon".

## Ennätyksien tarkastelu

Voit nähdä 10 parasta tulosta, kun painat päävalikosta nappia "Sijoitukset". Näkymästä pääsee takaisin päävalikkoon painamalla nappia "Takaisin valikkoon".
