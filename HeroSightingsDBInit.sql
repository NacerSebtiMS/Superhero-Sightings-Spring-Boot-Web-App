drop database if exists HeroSightingsDB;

create database HeroSightingsDB;

use HeroSightingsDB;

-- Hero
create table Hero(
	HeroId int primary key auto_increment,
    IsHero boolean not null,
    Description varchar(255) not null
);

-- Superpower
create table Superpower(
	SuperpowerId int primary key auto_increment,
    Name varchar(50) not null,
    Description varchar(255)
);

-- HeroSuperpower
create table HeroSuperpower(
	HeroId int not null,
    SuperpowerId int not null,
    PRIMARY KEY pk_HeroSuperpower (HeroId, SuperpowerId),
    foreign key fk_HeroSuperpower_HeroId (HeroId)
		references Hero(HeroId),
	foreign key fk_HeroSuperpower_SuperpowerId (SuperpowerId)
		references Superpower(SuperpowerId)
);

-- Organization
create table Organization(
	OrganizationId int primary key auto_increment,
    Name varchar(50) not null,
    IsHero boolean not null,
    Description varchar(255),
    Address varchar(255),
    Contact varchar(255)
);


-- HeroOrganization
create table HeroOrganization(
	HeroId int not null,
    OrganizationId int not null,
    PRIMARY KEY pk_HeroOrganization (HeroId, OrganizationId),
    foreign key fk_HeroOrganization_HeroId (HeroId)
		references Hero(HeroId),
	foreign key fk_HeroOrganization_OrganizationId (OrganizationId)
		references Organization(OrganizationId)
);

-- Location
create table Location(
	LocationId int primary key auto_increment,
    Name varchar(50) not null,
    Latitude DECIMAL(10,8) not null,
    Longitude DECIMAL(11,8) not null,
    Description varchar(255),
    AddressInformation varchar(255)
);

-- Sighting
create table Sighting(
	SightingId int primary key auto_increment,
	HeroId int not null,
    LocationId int not null,
    Date datetime not null,
    foreign key fk_Sighting_HeroId (HeroId)
		references Hero(HeroId),
	foreign key fk_Sighting_LocationId (LocationId)
		references Location(LocationId)
);
