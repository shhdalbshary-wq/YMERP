@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // عرض جميع المستخدمين
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // عرض مستخدم محدد
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // إنشاء مستخدم جديد
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    // تعديل مستخدم
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // حذف مستخدم
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
