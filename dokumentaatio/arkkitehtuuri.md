# Arkkitehtuurikuvaus

## Rakenne

Sovellus on toteutettu kolmitasoisena arkkitehtuurina.

<img src="https://github.com/hctarkia/ot-harjoitustyo/edit/master/dokumentaatio/kuvat/pakkausrakenne.png">

Pakkauksessa snake.ui on käyttöliittymän toteutus luokassa SnakeUi ja se käynnistetään luokasta Main.
Pakkaus snake.domain sisältää sovelluslogiikan toteutuksen ja pakkaus snake.dao vastaa tietokantaan tallennettavasta tiedosta.

## Käyttöliittymä

Käyttöliittymässä on neljä erilaista näkymää.

- Päävalikko, josta pääsee aloittamaan pelin tai katselemaan pelissä tehtyjä ennätyksiä.
- Pelin aikainen näkymä, missä pelaaminen tapahtuu.
- Pelin päätyttyä tulee näkymä, missä omat pisteet voi tallentaa tietokantaan ja palata päävalikkoon.
- Sijoitusten näkymässä näkee kymmenen parhaan pelaajan pisteet ja näkymästä voi palata takaisin päävalikkoon.

