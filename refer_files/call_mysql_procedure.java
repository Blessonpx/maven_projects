// ##############################################################
// https://springframework.guru/mysql-stored-procedures-with-spring-boot/
// ##############################################################

@Entity
public class Blog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column
private long blogId;
@Column
private String title;
@Column
private Integer yearOfPost;
public long getBlogId() {
return blogId;
}
public String getTitle() {
return title;
}
public Integer getYearOfPost() {
return yearOfPost;
}
}

// ############################################################################
// Approach 1 – @NamedStoredProcedureQuery Annotation
// ############################################################################
package org.springframework.guru.model;
import javax.persistence.*;
@Entity
@Table(name = "blog")
@NamedStoredProcedureQueries(
    {
        @NamedStoredProcedureQuery(name = "getAllBlogs",
        procedureName = "getAllBlogs"),
        @NamedStoredProcedureQuery(name = "getBlogsByTitle",
        procedureName = "getBlogsByTitle", 
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN,name = "tblogTitle",type=String.class)
            } )
    }
)
public class Blog {
@Id
private Integer blogId;
private String blogTitle;
private Integer yearOfPost;
}

// repository 
package org.springframework.guru.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.guru.domain.Blog;
import org.springframework.stereotype.Repository;
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
}


// service 
@Service
public class BlogService {
   @Autowired
   private BlogRepository blogRepository;
   @Autowired
   @PersistenceContext
   private EntityManager em;

   public List getTotalBlogs(){
       return em.createNamedStoredProcedureQuery("getAllBlogs").getResultList();
   }
   public List getBlogsByTitle(String title) {
       return em.createNamedStoredProcedureQuery("getBlogsByTitle").setParameter("tblogTitle",title).getResultList();
   }
}

// Controller
@RestController
public class BlogController {
   @Autowired
   private BlogService blogService;
   @GetMapping("/titleCount")
   public List getTotalBlogs(){
       return blogService.getTotalBlogs();
   }
   @GetMapping(path = "/titleCountP")
   public List getBlogsByTitle(@RequestParam("blogTitle") String title) {
       return blogService.getBlogsByTitle(title);
   }
}


// Testing

package org.springframework.guru.model;
import javax.persistence.*;
@Entity
@NamedStoredProcedureQueries(
    {
        @NamedStoredProcedureQuery(name = "getAllBlogs",
        procedureName = "getAllBlogs"),
    }
)
public class Blog {
@Id
private Integer blogId;
private String blogTitle;
private Integer yearOfPost;
}




@Entity
@NamedStoredProcedureQueries(
    {
        @NamedStoredProcedureQuery(name = "CLM_Event_Execution_RealTime",
        procedureName = "CLM_Event_Execution_RealTime", 
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN,name = "tblogTitle",type=String.class)
            } )
    }
)
@Immutable
public class CLMEventExecution_RealTime{
    @Id
    @Column
    private int countLines;
    public int getCountOdLines() {
        return countLines;
    }

}


create or replace procedure plus1inout (arg in int,res1 out int,res2 out int) is
BEGIN   
 res1 := arg + 1; 
 res2 := res1 + 1;
END;


@Repository
public interface AdjudConverDateSPRepository extends JpaRepository<AdjudConverDateSP, Long> {
    @Procedure(name = "plus1")
    Object[] plus1(@Param("arg") Integer arg);
}

@Entity
@NamedStoredProcedureQuery(name = "plus1", procedureName = "ADJUD.PLUS1INOUT",
        parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "arg", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "res1", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "res2", type = Integer.class)
})
public class AdjudConverDateSP implements Serializable {
        //stub to satisfy hibernate identifier requirement
        @Id @GeneratedValue
        private Long id;

}





@Repository
public interface AdjudConverDateSPRepository extends JpaRepository<CLMEventExecution_RealTime, Long> {
    @Procedure(name = "CLM_Event_Execution_RealTime")
    Object[] CLM_Event_Execution_RealTime(@Param("EventId") Integer EventId,@Param("MinId") Long EventId,@Param("MaxId") Long MaxId);
}
@Entity
@NamedStoredProcedureQuery(name = "CLM_Event_Execution_RealTime", procedureName = "CLM_Event_Execution_RealTime",
        parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "EventId", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "MinId", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "MaxId", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "voutSize", type = Long.class)
})
public class CLMEventExecution_RealTime{
    @Id
    private int countLines;
}


DELIMITER $$
CREATE  PROCEDURE CLM_Event_Execution_RealTime(IN EventId BIGINT(10),IN MinId BIGINT(10),IN MaxId BIGINT(10),OUT voutSize BIGINT(10))
BEGIN 

SELECT FLOOR(RAND()*(1000-1+1)+1000)  INTO voutSize;

END$$
DELIMITER ;
