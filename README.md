# Superhero-Sightings-Spring-Boot-Web-App

Superhero sightings is a web application that manages sightings of superheroes. The project includes a Data Layer and a web application that uses that Data Layer.

The system is capable of :
+ Display a home page with general insights about the application and a newsfeed of the latest 10 sightings in the database and a Google map view on those sightings ;
+ Display, create, edit and delete Superheroes and Supervillains. It is possible to store images related to heroes ;
+ Display, create, edit and delete Superpowers ;
+ Display, create, edit and delete Superheroes and Supervillains organizations ;
+ Display, create, edit and delete Superheroes and Supervillains sightings ;

### Important note

If you plan on trying the project, you will need a google map API if you want the map to show correctly. Create a file name "API_KEY.txt" in the root directory with your Google map API key.

The project will still run without it but will not show the map.

### Data Layer

+ The Data Layer contains a database :
    + [ERD](https://github.com/NacerSebtiMS/Superhero-Sightings-Spring-Boot-Web-App/blob/main/Database%20files/SuperheroSightingsERD.png)
    + [Database init file](https://github.com/NacerSebtiMS/Superhero-Sightings-Spring-Boot-Web-App/blob/main/Database%20files/HeroSightingsDBInit.sql) as well as a [testing database init file](https://github.com/NacerSebtiMS/Superhero-Sightings-Spring-Boot-Web-App/blob/main/Database%20files/HeroSightingsDBTestInit.sql)

+ The models are available [here](https://github.com/NacerSebtiMS/Superhero-Sightings-Spring-Boot-Web-App/tree/main/superherosightings/src/main/java/com/sg/superherosightings/models)

+ The DAO files are available [here](https://github.com/NacerSebtiMS/Superhero-Sightings-Spring-Boot-Web-App/tree/main/superherosightings/src/main/java/com/sg/superherosightings/dao)

+ The DAO test files are available [here](https://github.com/NacerSebtiMS/Superhero-Sightings-Spring-Boot-Web-App/tree/main/superherosightings/src/test/java/com/sg/superherosightings/dao)
