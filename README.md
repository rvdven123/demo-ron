# Bekende beperkingen

* Retry voor RestRepo implementaties.
* Veel unit tests toevoegen
* Unit tests netjes opdelen per te testen implementatie
* Veel te weinig verschillende users getest.
* yaml gebruiken als spring config en validatie op de configuratie
* api definition 
* property validations/constraints. 
* CreditCards is niet ge-implementeerd. 
* Zie verder alle TODO's

# Gebruiken 
userinfo opvragen : http://localhost:8081/userinfo

Er is maar 1 gebruiker geconfigureerd en getest: Fellowship of the ring (voor inloggen zie application.properties)


# Design principles
* KISS: Geen overbodige layers.
* Ontkoppeling van externe (userinfo package) en intern (repos package) modellen
* Geen cyclische dependencies -> Acyclic directed graph 
* Modularisation: high cohesion, low coupling. 
* Immutability
* Builder pattern
* Command based.
* SOLID principles
* DDD -> GetUserInfo heeft geen infra dependencies.

Gracefull afvangen van errors is geimplementeerd via spring boot ErrorController.
Is dat gracefull genoeg ?  

Het kan allemaal nog een stuk meer rock solid, maar het idee is denk ik wel duidelijk.




