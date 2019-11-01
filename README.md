# Performance Reports

## It follows this flow:
1. reads tests results data from **Jenkins** output (json or csv)
2. creates a new folder to place the genarated files
3. creates a spreadsheet from a template in a Google Drive folder **[2]**
4. fills the spreadsheet with data from datasource **[1]**
5. creates a document from a template in the same folder as the spreadsheet
6. replaces data in the document (place holders)

## Config
There is a **.properties file** in the * *drools.reports.resources folder* *. It is possible to setup:
- input data params for the generated documents (template's place holders)
- datasource paths (json and csv): Jenkins output files
- template files IDs on Google Drive
- folder ID to place the generated report
- csv as alternative

## TO-DO
1. draw chats and place them in the document
2. do the authentication process automated
3. set the output of the project in a shared space in Google Docs
4. integrate it with the Jenkins jobs to have reports more often (once a day/night?)
5. improvements in the code
6. improvements in the templates
7. suggestions and feedbacks are welcome

## Issues to solve
1. embed chart into the doc - help is welcome (Google API is very limitaded for this operation)
2. auth as service (no user intervention) - will work on that (Google cloud API)
3. files and folders must be placed in public (company) dir - will work on that later
