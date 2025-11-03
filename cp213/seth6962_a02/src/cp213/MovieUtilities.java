package cp213;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utilities for working with Movie objects.
 *
 * @author your name, id, email here
 * @version 2025-10-18
 */
public class MovieUtilities {

    /**
     * Counts the number of movies in each genre given in {@code Movie.GENRES}. An
     * empty movies list should produce a count array of: [0,0,0,0,0,0,0,0,0,0,0]
     *
     * @param movies {@code List} of movies
     * @return number of genres across all movies - one entry for each genre in the
     *         {@code Movie.GENRES} array
     */
    public static int[] genreCounts(final ArrayList<Movie> movies) {

	// your code here
        int[] counts = new int[Movie.GENRES.length];

        if (movies == null || movies.isEmpty()) {
            return counts;
        }

        for (Movie movie : movies) {
            if (movie == null) continue;  // skip null entries
            int genreIndex = movie.getGenre();

            // Make sure the genre index is valid before incrementing
            if (genreIndex >= 0 && genreIndex < Movie.GENRES.length) {
                counts[genreIndex]++;
            }
        }

        return counts;
    }

    /**
     * Creates a {@code Movie} object by requesting data from a user. Uses the
     * format:
     *
     * <pre>
    Title:
    Year:
    Director:
    Rating:
    Genres:
     0: science fiction
     1: fantasy
    ...
    10: mystery

    Enter a genre number:
     * </pre>
     *
     * @param keyboard a keyboard ({@code System.in}) {@code Scanner} object
     * @return a {@code Movie} object.
     */
    public static Movie getMovie(final Scanner keyboard) {

	// your code here
    	Movie movie = new Movie("",0,"",0,0);
    	
    	System.out.print("Title: ");
    	movie.setTitle(keyboard.nextLine());
    	
		System.out.print("Year: ");
		movie.setYear(keyboard.nextInt());
		keyboard.nextLine();
		
		System.out.print("Director: ");
		movie.setDirector(keyboard.nextLine());
		
		System.out.print("Rating: ");
		movie.setRating(keyboard.nextDouble());
		keyboard.nextLine();
		
		System.out.print("Genres: ");
    	movie.setGenre(keyboard.nextInt());
    	    	

	return movie;
    }

    /**
     * Creates a {@code List} of movies whose genre is equal to the {@code genre}
     * parameter.
     *
     * @param movies {@code List} of movies
     * @param genre  genre to compare against
     * @return {@code List} of movies of type {@code genre}
     */
    public static ArrayList<Movie> getByGenre(final ArrayList<Movie> movies, final int genre) {

	// your code here
    	ArrayList<Movie> bygen = new ArrayList<Movie>();
    	
    	for(Movie movie: movies) {
    		if(movie.getGenre() == genre) {
    			bygen.add(movie);
    		}
    	}
    	
	return bygen;
    }

    /**
     * Creates a {@code List} of Movies whose ratings are equal to or higher than
     * rating.
     *
     * @param movies {@code List} of movies
     * @param rating rating to compare against
     * @return {@code List} of movies of {@code rating} or higher
     */
    public static ArrayList<Movie> getByRating(final ArrayList<Movie> movies, final double rating) {

	// your code here
    	ArrayList<Movie> byrating = new ArrayList<Movie>();
    	
    	for(Movie movie: movies) {
    		if(movie.getRating() == rating) {
    			byrating.add(movie);
    		}
    	}
    	
	return byrating;
    }

    /**
     * Creates a {@code List} of Movies from a particular year.
     *
     * @param movies {@code List} of movies
     * @param year   year to compare against
     * @return {@code List} of movies of year
     */
    public static ArrayList<Movie> getByYear(final ArrayList<Movie> movies, final int year) {

	// your code here
    	ArrayList<Movie> byrating = new ArrayList<Movie>();
    	
    	for(Movie movie: movies) {
    		if(movie.getYear() == year) {
    			byrating.add(movie);
    		}
    	}
    	
	return byrating;
    }

    /**
     * Asks a user to select a genre from a list of genres displayed by calling
     * {@code Movie.genresMenu} and returns an integer genre code. The genre must be
     * a valid index to an item in Movie.GENRES.
     *
     * @param keyboard a keyboard ({@code System.in}) {@code Scanner} object
     * @return an integer genre code
     */
    public static int readGenre(final Scanner keyboard) {

	// your code here
    	
    	Movie.genresMenu();
    	
    	int a = keyboard.nextInt();

	return a;
    }

    /**
     * Creates and returns a {@code Movie} object from a line of formatted string
     * data. The data should be in the format:
     *
     * <pre>
     * "title|year|director|rating|genre"
     * </pre>
     *
     * The quote character (") is not part of the string. See the file movies.txt
     * for an example of the file format and contents.
     *
     * @param line A vertical bar-delimited line of movie data
     *
     * @return the data from line as a {@code Movie} object
     */
    public static Movie readMovie(final String line) {

	// your code here
    	Movie movie = new Movie("",0,"",0,0);
    	String[] delim = line.split("\\|");
    	    	
    	movie.setTitle(delim[0]);
    	movie.setYear(Integer.parseInt(delim[1]));
    	movie.setDirector(delim[2]);
    	movie.setRating(Double.parseDouble(delim[3]));
    	movie.setGenre(Integer.parseInt(delim[4]));
    	
    	return movie;
    }

    /**
     * Reads a {@code List} of Movies from a file. Each line of the file is in the
     * format:
     *
     * <pre>
     * "title|year|director|rating|genre"
     * </pre>
     *
     * The quote character (") is not part of the string. See the file movies.txt
     * for an example of the file format and contents.
     *
     * @param fileIn a {@code Scanner} of a movie data file
     *
     * @return a {@code List} of movies
     */
    public static ArrayList<Movie> readMovies(final Scanner fileIn) {

	// your code here
    	ArrayList<Movie> movie = new ArrayList<Movie>();
    	    	
    	while(fileIn.hasNext()) {
    		movie.add(readMovie(fileIn.nextLine()));
    	}
    	
	return movie;
    }

    /**
     * Writes the contents of a {@code List} of movies to a {@code PrintStream}.
     *
     * @param movies {@code List} of movies
     * @param ps     output {@code PrintStream}
     */
    public static void writeMovies(final ArrayList<Movie> movies, PrintStream ps) {

	// your code here
    	 if (movies == null || ps == null) {
    	        return; // avoid NullPointerException
    	    }

    	    for (Movie movie : movies) {
    	        if (movie != null) {
    	            ps.append(movie.toString()); // or customize formatting if needed
    	        }
    	    }    	

	return;
    }

}
