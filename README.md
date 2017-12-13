# GoalMaker

Podstawowe poruszanie się po heroku.

Jeśli mamy nową bazę i nikt jeszcze z nas jej nie wrzucił na heroku, a potrzebujemy zrobić to pilnie należy wziąć od Igora linka do dropboxa (ma tam wrzucać backupy) i wydać komendę z terminala:
```
heroku pg:backups:restore 'linkOdIgora' DATABASE_URL
```
Jeśli wszystko poszło dobrze, powinno w konsoli wyświetlić się prośba o potwierdzenie. Należy wpisać nazwę aplikacji (w naszym przypadku goalmaker).

Deploy na heroku:
Robimy to z folderu gdzie jest pom.xml. Wpisujemy komendę:
```
mvn clean heroku:deploy-war.
```
Jeśli nie chce nam się deploynąć pierwsze co należy sprawdzić to czy na pewno są dobre zdalne repozytoria.
Komenda: 
```
git remote -v.
```
Po tej komendzie powinniśmy mieć: https://git.heroku.com/goalmaker.git(fetch) (push) i https://github/KamilBest/GoalMaker.git (fetch) (push).
W przypadku jak nie ma heroku należy dodać je poprzez komendę:
```
heroku git:remote -a goalmaker
```
No i jak już mamy deploy i wszystko śmiga, ale apka się nie odpala czy są jakieś błędy warto wejść na stronę heroku i sprawdzać logi na stronie (view logs w dashboardzie). Dzięki temu można sporo ciekawych i przydatnych rzeczy wyciągnąć.