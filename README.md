# roles-service
A service that add the concept of team roles to a existent Users and Teams API.

## Approach
Started with a service that calls the existents users and teams API and checks if a user is a member of a given team. Since a user can be part of zero or more teams, have to make sure that we would only assign a role to a pair of user and a team they are part of.

To assign a role, a role must exist, so, there is a service to manage roles, where we can create, update, list all roles, find a role by id and delete a role.

So, when receiving a combination of userId and teamId, we should be able to assign them a role. For this purpose, there is a membership service that receives a userId, a teamId and a roleId (to be assigned), search if the user is in the team, and if it is, creates a membership, what is composed by a unique combination of user and team, and a role.

The user and team ids composes a key that is the membership id, so the user and team combination are unique.

When passing a role to a membership, if that membership already exists, it will be updated with the given role, if not, it will be created.
Since that are memberhips indexed in the db, we can search for them. We can search for a role by a membership(userId and teamId) or we can search by all memberships with a given role.

## Running with docker
#### Clone the project
```
git clone git@github.com:ThalitaC/roles-service.git
```
#### Go to the directory where you have cloned it

#### Run with docker
```
docker-compose up --build
```
### It will be available at http://localhost:8080/

### And the documentation is available at http://localhost:8080/docs/ui


## What I would like to have on the Team and User services
- A way to find all teams that a user is on.
or
- That the get user by id endpoint contained also a list of the teams they are on.
- A way to check if a user is in a team, or if a team has a user, without having to get the complete json.
