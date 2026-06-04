@Service
public class FirebaseService {

    private final Firestore db;

    public FirebaseService() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/google-services.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    // مزامنة فاتورة إلى Firebase
    public void syncInvoice(Invoice invoice) throws ExecutionException, InterruptedException {
        db.collection("invoices").document(invoice.getId().toString()).set(invoice).get();
    }

    // مزامنة مستخدم إلى Firebase
    public void syncUser(User user) throws ExecutionException, InterruptedException {
        db.collection("users").document(user.getId().toString()).set(user).get();
    }
}
