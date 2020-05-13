package memoir.awt;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import memoir.awt.Cinema;
import memoir.awt.Person;
import memoir.awt.Rating;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-22T16:00:34")
@StaticMetamodel(Memoir.class)
public class Memoir_ { 

    public static volatile SingularAttribute<Memoir, Date> movieReleaseDate;
    public static volatile SingularAttribute<Memoir, String> movieComment;
    public static volatile SingularAttribute<Memoir, Date> movieWatchTime;
    public static volatile SingularAttribute<Memoir, Rating> movieRating;
    public static volatile SingularAttribute<Memoir, Date> movieWatchDate;
    public static volatile SingularAttribute<Memoir, Cinema> cinemaId;
    public static volatile SingularAttribute<Memoir, Person> personId;
    public static volatile SingularAttribute<Memoir, Integer> memoirId;
    public static volatile SingularAttribute<Memoir, String> movieName;

}