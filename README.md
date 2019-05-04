Assignment

Create a search application where you expose an endpoint for a client to search based on a certain radius for tree related data.

- You can find Street tree data from the TreesCount 2015 here => https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh
- The direct call to api is `https://data.cityofnewyork.us/resource/nwxe-4ae8.json`
- You have to expose and API endpoint accepting two parameters 
    1. A Cartesian Point specifying a center point along the x & y plane
    2. A search radius in meters

Output
 - You have to retrieve the count of "common name" (please see in the documentation on the same page above which field to refer to) for all 
 the species of trees in that search radius
 - Expected outcome from the api
```json
{
    "red maple": 30,
    "American linden": 1,
    "London planetree": 3
}
```


Assignment solution:

To retrieve the count of all the species of trees in the search radius.
Option 1: Access below endpoint with the HTTP Status as 'GET' and JSON body (REST Client ex. Postman)
endpoint: http://localhost:8080/searchTrees 
JSON body example: { "centerPoint_X": "1027420", "centerPoint_Y": "202750", "searchRadius": "2000"}
Option 2: Access below endpoint with parameters in the URL (Postman/browser)
endpoint: http://localhost:8080/searchTreesv2/?centerPoint_X=1027420&centerPoint_Y=202750&searchRadius=1700

To retrieve all StreetTree Data
endpoint: http://localhost:8080/streetTreeData