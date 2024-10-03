# Springboot application to create web services.

### To list the users
#### GET
```
http://localhost:8080/ws/v1/user/list
```

### To create user
#### POST
```
http://localhost:8080/ws/v1/user/list
```
#### Request Body
```
{
"userName": "dwilmes",
"followerModels":[]
}
```
### Docker build and run (Run from the root of the project)

#### Build
```
docker build -t my-app .
```
#### Run
```
sudo docker run -it --rm -p 8080:8080 my-app
```