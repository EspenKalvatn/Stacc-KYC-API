# Stacc Code Challenge 2022 - KYC API

## Table of content
- [Task description](#task-description)
- [Project description](#project-description)
- [Setup](#setup)
- [How to run](#how-to-run)
- [How to use the GUI](#how-to-use-the-gui)
- [How to use the API](#how-to-use-the-api)
- [Further remarks](#further-remarks)

## Task description
The task to solve was to develop an application, API or a design that makes it possible to perform a KYC check 
on individuals and/or persons in within an organization. 

KYC or "know your customer" are tools that ensure that a financial company knows enough about its customers to 
prevent and detect whether the financial system is being used for illegal activities, such as money laundering, 
corruption and terrorist financing. One of these measures is to carry out a PEP (Politically Exposed Person) check 
on customers. A politically exposed person generally has a greater risk of corruption and bribery associated with him, 
and will thus be flagged for manual processing in, for example, a loan application at a bank. 

More about the task description can be found [here](https://github.com/stacc/stacc-code-challenge-public).

## Project description
In my solution, I chose to prioritize a backend solution, and created an API according to the task suggestions. 
I have also made a simple frontend to make it easier to call the API (no prior experience). 
The backend is coded in Java and is using the Springboot framework to make it a REST-API.
The frontend is coded in HTML, CSS and JavaScript.
For the backend, I focused on making two endpoints, 
one for performing a PEP check on an individual, and one for checking all persons in an organization. Both endpoints are using 
the provided CSV file [pep.csv](src/main/resources/pep.csv) with publicly exposed persons, to decide if a given name is publicly exposed or not.
The second endpoint is using the [provided API](https://code-challenge.stacc.dev/) from Stacc to search up an organization by an organization number to get 
people involved in that organization and then compare that to the list of publicly exposed persons.

## Setup
1. To run this project, you will need to install Java version 17.
2. Clone the project from the [GitHub](https://github.com/EspenKalvatn/Stacc-KYC-API.git) using either `SSH` or `HTTPS`.
Alternatively you can download the source code with the button to the left of the clone button and unzip the source code at the desired location.
3. Inside the IDE of your choice, open the project by
    - IntelliJ: navigate `File -> New -> Project from Version Control` and paste the link provided by step 2 (either with `SSH` or `HTTPS`).
    - Eclipse: navigate `File -> Import -> Git -> Clone URI` and paste the link provided by step 2 (either with `SSH` or `HTTPS`).
    - Other IDEs have similar ways to import projects from version control systems.
    - If you are downloading the source code directly. Open the project by `File -> New -> Project from Existing Source...` (IntelliJ). Eclipse have similar procedure.

## How to run
Inside the IDE of your choice, locate the main class `src/main/java/Stacc/KYC/API/StaccKycApiApplication.java` and run it.
The API is now running locally on your computer. 

## How to use the GUI
I have made a simple frontend to make it easier for the user to use the API. After running the application. The frontend can be reached on `localhost:8080`. 
This frontend solution do not preprocess the JSON response from the API call, but only redirects you to the JSON object returned by the API call. 
You can read more about what the endpoints of the API is returning in the next section.

## How to use the API
The API has two endpoints. To use the endpoints, you can for example use Postman, or simply open up a web browser and write 
the API call in the search bar. Since the API is running locally, you will need to use `localhost:8080/check`

### Check person
`/person/{name}` takes in a name or alias (text string) and performs a PEP-check on this name towards the list provided.
The call returns a JSON object containing information found about the person searching for. Information about the person is placed inside a response-body 
which tells you how many hits your search got and a list of person objects (if for example the name occurs in multiple datasets). If the person you are searching for is not publicly exposed, 
it will return a response body with `hits:0` and `results:[]`.  
#### Input:
    localhost:8080/check/person/Erna Solberg
#### Output:
    {"hits":1, "results":[ {...} ]}

### Check organization
`/organization/{organization number}` takes in an organization number (text string), performs a PEP-check on people inside 
that organization and returns a response-body as mentioned above with information on how many hits your search got, and a list of person-objects.
#### Input:
    localhost:8080/check/organization/870168012
#### Output: 
    {"hits":8, "results":[ {...}, {...}, ... , {...} ]}

## Further remarks
First, I misread the task description and based the API solution on the data from the pep_small.json file. This dataset was quite small compared to the pep.csv file.
I found working with JSON data in Java much easier than working with CSV files and found very easy solutions to convert JSON objects to POJO (plain old java objects).
I thought a lot about what information the endpoints of the API should return. In the start, I only returned a string sentence saying if a person is a publicly exposed person or not.
After some thought, I decided to return information about that person, so it could be used further. 
