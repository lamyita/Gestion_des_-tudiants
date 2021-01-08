package sample;


    public class Etudiant {
        private Integer id;
        private String nom;
        private String prenom;
        private String phone;
        private String email;

        public Etudiant(Integer id, String nom, String prenom, String phone, String email) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
            this.phone = phone;
            this.email = email;
        }

        public Integer getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

    }

