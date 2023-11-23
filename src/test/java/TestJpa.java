import jakarta.persistence.EntityManager;
import org.francescobasile.tisanoweb1.entity.fortest.UtenteTest;
import org.francescobasile.tisanoweb1.util.TextWrap;
import org.francescobasile.tisanoweb1.util.persistance.JpaUtilLocal;
import org.junit.jupiter.api.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestJpa {

    //Logger template
//        logger.log(Level.INFO, TextWrap.leadAndTrail("log something: {0}, {1}"), new String[]{this, that});
    static EntityManager entityManager;
    static Logger logger;
    static UtenteTest utente;
    static UtenteTest utenteUpdated;
    static String nome;
    static String cognome;
    boolean willCommit = false;

    @BeforeAll
    static void beforeAll() {
        logger = Logger.getLogger(TestJpa.class.getSimpleName());
        entityManager = JpaUtilLocal.getEntityManager();
//        logger.log(Level.INFO, TextWrap.leadAndTrail("BeforeALL::Getting Entity Manager:: {0} {1}"), new String[]{"", ""});
        entityManager.getTransaction().begin();
        logger.log(Level.INFO, TextWrap.leadAndTrail("BeforeALL::Starting Transaction:: {0} {1}"), new String[]{"", ""});
        nome = "nome_00";
        cognome = "cognome_00";
    }

    @BeforeEach
    void setUp() {
        logger.log(Level.INFO, TextWrap.leadAndTrail("BeforeEACH::Inizializzazione attributi Entity:: {0} {1}"), new String[]{nome, cognome});//, new String[]{nome, cognome});
        if (!entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().begin();
        }
    }

    @Order(1)
    @Test
    @DisplayName("Test operazione CRUD-->CREATE entity")
    void createEntityTest() {
        logger.log(Level.INFO, TextWrap.leadAndTrail("TESTCASE::CREATE Entity:: {0} {1}"), new String[]{"", ""});
        utente = new UtenteTest(nome, cognome);
        entityManager.persist(utente);
        willCommit = true;
    }

    @Order(2)
    @Test
    @DisplayName("Test operazione CRUD-->READ entity")
    void selectEntityTest() {
        logger.log(Level.INFO, TextWrap.leadAndTrail("TESTCASE::READ Entity:: {0} {1}"), new String[]{"", ""});
        utenteUpdated = new UtenteTest();
        assertDoesNotThrow(() -> entityManager.find(UtenteTest.class, 1L));
        utenteUpdated = entityManager.find(UtenteTest.class, 1L);
        assertNotNull(utenteUpdated);
        assertEquals("nome_00", utenteUpdated.getNome());
    }

    @Order(3)
    @Test
    @DisplayName("Test operazione CRUD-->UPDATE entity")
    void updateEntityTest() {
        logger.log(Level.INFO, TextWrap.leadAndTrail("TESTCASE::UPDATE Entity:: {0} {1}"), new String[]{"", ""});
        utenteUpdated = entityManager.find(UtenteTest.class, 1L);
        utenteUpdated.setNome("NomeUpdated");
        utenteUpdated.setCognome("CognomeUpdated");
        entityManager.persist(utenteUpdated);
        entityManager.getTransaction().commit(); // dobbiamo fare subito commit per rileggere lo stato aggiornato nel db
        UtenteTest u = entityManager.find(UtenteTest.class, 1L);
        assertEquals("NomeUpdated", u.getNome());
        assertEquals("CognomeUpdated", u.getCognome());
    }

    @Order(4)
    @Test
    @DisplayName("Test operazione CRUD-->DELETE entity")
    void deleteEntityTest() {
        logger.log(Level.INFO, TextWrap.leadAndTrail("TESTCASE::DELETE Entity:: {0} {1}"), new String[]{"", ""});
        assertDoesNotThrow(() -> {
            entityManager.remove(entityManager.find(UtenteTest.class, 1L));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            entityManager.remove(entityManager.find(UtenteTest.class, 100L));
        });
        willCommit = true;
    }

    @AfterEach
    void tearDown() {
        if (willCommit) {
            logger.log(Level.INFO, TextWrap.leadAndTrail("AfterEACH::Committing:: {0} {1}"), new String[]{"", ""});
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
            entityManager.getTransaction().commit();
            willCommit = false;
        } else {
            logger.log(Level.INFO, TextWrap.leadAndTrail("AfterEACH::Commit non necessario:: {0} {1}"), new String[]{"", ""});
        }
    }

    @AfterAll
    static void afterAll() {
        entityManager.close();
        logger.log(Level.INFO, TextWrap.leadAndTrail("AfterAll::CLosing Persistance:: {0}, {1}"), new String[]{"", ""});
    }

}

