import models.Actors;
import models.Film;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Actors> actorsList = new ArrayList();
    private static List<Film> filmList = new ArrayList();
    private static Map<Actors, List<Film>> filmOfActorList = new HashMap<>();
    private static List<Film> films = new ArrayList<>();

    private static final String GET_ACTORS = "SELECT * FROM actor";
    private static final String ADD_ACTOR = "INSERT INTO actor(first_name, last_name) VALUES(?,?)";
    private static final String GET_FILMS = "SELECT * FROM film";
    private static final String ADD_FILMS = "INSERT INTO film(title, description, release_year, language_id ) VALUES(?,?,?,?)";
    private static final String FIND_FILM_OF_ACTOR = "SELECT first_name, last_name, title, description FROM film, film_actor, actor\n" +
            "WHERE film.film_id = film_actor.film_id AND film_actor.actor_id = actor.actor_id AND first_name = 'GOLDIE'";

    private static Connection getConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/sakila?serverTimezone=UTC", "root", "rootroot");
    }

    private static void getActors(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTORS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            actorsList.add(
                    new Actors(
                            resultSet.getInt("actor_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getDate("last_update")
                    )
            );

        }
        List<Actors> collect = actorsList.stream().filter(actors -> actors.getActor_id() == 201).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void addActors(Connection connection) throws SQLException {
        PreparedStatement insertActor = connection.prepareStatement(ADD_ACTOR);
        insertActor.setString(1, "Sviat");
        insertActor.setString(2, "Kogut");
        insertActor.executeUpdate();
    }

    private static void addFilm(Connection connection) throws SQLException{
        PreparedStatement addFilm = connection.prepareStatement(ADD_FILMS);
        addFilm.setString(1, "The Invisible Man");
        addFilm.setString(2, "A 2020 science fiction horror film written and directed by Leigh Whannell, loosely based on the novel of the same name by H. G. Wells.");
        addFilm.setInt(3, 2012);
        addFilm.setInt(4, 6);
        addFilm.executeUpdate();
    }

    private static void getFilms(Connection connection) throws SQLException {
        PreparedStatement getFilms = connection.prepareStatement(GET_FILMS);
        ResultSet resultSet = getFilms.executeQuery();
        while (resultSet.next()) {
            filmList.add(new Film(
                            resultSet.getInt("film_id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getInt("release_year"),
                    resultSet.getInt("language_id")
                    )
            );
        }
        List<Film> newFilmList = filmList.stream().filter(film -> film.getTitle().startsWith("Th")).collect(Collectors.toList());
        System.out.println(newFilmList);
    }

    private static void getFilmOfActor(Connection connection) throws SQLException{
        PreparedStatement findFilmOfActor = connection.prepareStatement(FIND_FILM_OF_ACTOR);
        ResultSet resultSet = findFilmOfActor.executeQuery();

        while(resultSet.next()){
            films.add(new Film(resultSet.getString("title"), resultSet.getString("description")));
            filmOfActorList.put(new Actors(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")), films);
        }
            System.out.println(filmOfActorList);
    }
    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
//        addActors(connection);
//        getActors(connection);
//        addFilm(connection);
//        getFilms(connection);
        getFilmOfActor(connection);
        connection.close();
    }
}
