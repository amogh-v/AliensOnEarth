# AliensOnEarth
To run the code, create respective packages as specified in the files, and run the AlienApplication class in alienapp file.
To add PDFWriter download the JAR file from http://sourceforge.net/projects/itext/ , then configure it to the classpath.
To add a new input format say foobar, just create a class foobarWriter in the exportdata package and in the exportFactory add the foobar condition. This does not include any code changes in the application layer of the code just in the backend part to configure the plugin to the application. 
