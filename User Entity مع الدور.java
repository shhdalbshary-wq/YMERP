@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String passwordHash;
    private String role; // ADMIN, ACCOUNTANT, USER
    private boolean active;

    // Getters & Setters
}
