# vmo-note
Note application

*. Build BE steps:
1. Create mysql database name: vmo-note
2. Import BE code in folder "note"
3. Import sql file link: "/vmo-note/note/src/main/resources/Dump20220908.sql"
4. Modify username and password datasource
5. Run server on port 8080: 
6. Api login:
POST: http://localhost:8080/api/v1/auth/login
Body linhpv account:
{
    "username":"linhpv",
    "password":"123456"
}

Body daitt account:
{
    "username":"daitt",
    "password":"123456"
}



*. Build FE steps:
1. Open folder "note-fe"
2. command: yarn install
3. command: yarn serve
4. Server FE run on port 5555
4. Links:
- Host: http://localhost:5555/
- List note: http://localhost:5555/list-note
- Add basic note: http://localhost:5555/add-basic-note
- Add image note: http://localhost:5555/add-image-note
- Checkbox note: http://localhost:5555/add-checkbox-note

*. Note
Curretly, login page isn't implemented on FE. so I logged in by postman and get temporary token, 
then set it in Bearer authentication header. You can change token in file "note-fe/src/http-common.js if you want to loggin with other account