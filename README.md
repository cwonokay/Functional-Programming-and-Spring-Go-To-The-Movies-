# Functional-Programming-and-Spring-Go-To-The-Movies-
Create a new Spring Boot project. You'll need to include Thymeleaf as a dependency. Create a Movie JavaBean with the following variables:

String title
String posterPath (You will need to annotate this with @JsonProperty to make it match to poster_path)
String overview
double popularity
TMDB has many more properties than just those, so you'll want to add the @JsonIgnoreProperties(ignoreUnknown = true) annotation to your class.

Next, create a ResultsPage JavaBean with a List<Movie> results.

Create HTML Files  
Add the following HTML templates:

home.html
now-playing.html
medium-popular-long-name.html
overview-mashup.html (EXTRA)
You can have IntelliJ generate these files for you but remember to close the tags (Thymeleaf). Add some text to distinguish them. "Home" should have links to /now-playing and /medium-popular-long-name. Add a simple @Controller class to serve up these three HTML views. (/ should lead to home.html, and the others to their respective routes). Run your program and test that you can see all three HTML pages.

Query the TMBD API  
Leave IntelliJ alone for now. Using curl or Postman, query the TMDB API. You will need to have an access token. You can either sign up for a free account and get your own, or use this one:

?api_key=be2a38521a7859c95e2d73c48786e4bb

Try to access curl or Postman to this route:

https://api.themoviedb.org/3/movie/now_playing

and you should get a JSON response back with data about movies that are currently playing.

Now, let's access the API through Java. Write a public static List<Movie> getMovies(String route) method in your controller. It should use the restTemplate.getForObject(URL, class) method to query the same route. (As a troubleshooting step, you may wish to add code to print the data you receive from the API to the console). Change your /now-playing endpoint to call the method and add the resulting list of movies to the Model.

Add a block of Thymeleaf templated code to your now-playing.html file. Your template should show each movie poster along with its title. (Changing ??? to some Thymeleaf statements).

These movies are playing in theaters:
<div>
    <img width="150px" src="'http://image.tmdb.org/t/p/original' + ???"/>
    <span ???></span>
</div>
Run your program and access the endpoint. You should see movie posters!

Medium Popular, Long Name Movies  
You have a friend who is very particular about movies. They want to be able to see a list of movies that are popular - but not TOO popular. Also, they only want to see movies whose titles are at least 10 characters long. Update your /medium-popular-long-name endpoint and HTML to show movies with long names whose popularity ranges from 30 to 80. (You can reuse the Thymeleaf template). Use stream() operations to filter the list.

