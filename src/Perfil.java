public class Perfil {
    private String nomeUsuario;
    private String email;

    public Perfil(String nomeUsuario, String email) {
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Perfil Ativo: " + nomeUsuario + " (" + email + ")";
    }
}