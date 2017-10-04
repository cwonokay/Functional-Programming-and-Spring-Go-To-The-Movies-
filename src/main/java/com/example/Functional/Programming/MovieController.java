package com.example.Functional.Programming;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";


    @RequestMapping(path = "now-playing", method = RequestMethod.GET)
    public String nowPlaying (Model model){
        List<Movie> movies = getMovies(url);
        model.addAttribute("movies", movies);
        return "now-playing";

    }


    @RequestMapping(path = "/medium-popular-long-name", method = RequestMethod.GET)
    public String medium(Model model) {
        List<Movie> movies = getMovies(url).stream()
                .filter(movie -> movie.getTitle().length() >= 10)
                .filter(movie -> movie.getPopularity() > 30 && movie.getPopularity() < 80)
                .collect(Collectors.toList());

        model.addAttribute("movies", movies);
        return "/medium-popular-long-name";

    }


    public List<Movie> getMovies(String route) {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject(route, ResultsPage.class);
        System.out.println(resultsPage);
        return resultsPage.getResults();
//


    }
}