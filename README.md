# xdesign test | Ricardo Vargas | ricvargas@gmail.com

To run this project simply install it on your favorite IDE or run it as a Gradle project with your console.

Just make sure to have your JAVA_HOME well configured in your environment.

1. The application will load the csv file within an in-memory database every time it starts.

2. To call the webservice go to this URL: [http://localhost:8080/munro/hills](http://localhost:8080/munro/hills)

3. Here is the list of parameters that you can use: 

    - **category** (values: "TOP" or "MUN") - The category of the hill based on the value of the column 'AB' (Post 1997)
    - **minHeight** - minimum height in meters.
    - **maxHeight** - maximum height in meters.
    - **total** - total of records retrieved.
    - **sortField1** - you can define which field you want to sort out here, originally meant for height in meters.
    - **sortField2** - you can define which field you want to sort out here, originally meant for name.
    - **sortOrder1** - (values: 'asc', 'desc') sort order for the 'sortField1'
    - **sortOrder2** - (values: 'asc', 'desc') sort order for the 'sortField2'

This is an example of how you can call the webservice with all the parameters:

[http://localhost:8080/munro/hills?category=TOP&minHeight=980&maxHeight=985&sortField1=heightMeters&sortField2=name&sortOrder1=desc&sortOrder2=desc](http://localhost:8080/munro/hills?category=TOP&minHeight=980&maxHeight=985&sortField1=heightMeters&sortField2=name&sortOrder1=desc&sortOrder2=desc)

You can sort them in any order and the result won't be affected.

If you have any questions or comments, please let me know at [ricvargas@gmail.com](mailto:ricvargas@gmail.com)

Regards!