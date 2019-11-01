# Performance Reports

## It follows this flow:
1. reads tests results data from **Jenkins** output (json or csv)
2. creates a new folder to place the genarated files
3. creates a spreadsheet from a template in a Google Drive folder **(from item 2, above)**
4. fills the spreadsheet with data from datasource **(Jenkins output)**
5. creates a document from a template in the same folder as the spreadsheet
6. replaces data in the document (place holders)

## Config
There is a **.properties file** in the * *drools.reports.resources folder* *. It is possible to setup:
- input data params for the generated documents (template's place holders)
- datasource paths (json and csv): Jenkins output files
- template files IDs on Google Drive
- folder ID to place the generated report
- csv as alternative

## Google Auth (User Account auth)
1. Follow the instructions: **[1]** 
2. Download the Google API Key File (Json format)
3. Rename the file to "client_secret.json"
4. Place the file on this folder: src/main/java/org/benchmarks/reports/resources/client_secret.json
5. Call GoogleAuthorize.authorize() on GoogleService() constructor.

## Google Auth (Service Account auth)
1. Follow the instructions: **[2]**
2. Download the Service Google API Key File (Json format)
3. Rename the file to "service-account.json"
4. Place the file on this folder: src/main/java/org/benchmarks/reports/resources/service-account.json
5. Take note of the service account e-mail address (API Console -> learn more **[2]**)
7. Go to the sharing configurations on the Google Drive Folder where the reports and templates are
8. Set the service account e-mail address as a "People" to share the folder
9. Call GoogleCredencialAuthorize.authorize() on GoogleService() constructor.

## TO-DO
1. **{Critical}** ... draw chats and place them in the document
2. **{DONE}** ....... do the authentication process automated
3. **{DONE}** ....... set the output of the project in a shared space in Google Docs
4. **{Major}** ...... integrate it with the Jenkins jobs to have reports more often (once a day/night?)
5. **{Optional}** ... improvements in the code
6. **{Optional}** ... improvements in the templates
7. **{Optional}** ... suggestions and feedbacks are welcome

## Links
* [1] https://developers.google.com/maps/documentation/javascript/get-api-key
* [2] https://developers.google.com/identity/protocols/OAuth2ServiceAccount
