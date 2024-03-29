package application.config.extraConfig;


public class ApplicationDetails {

    public static String text() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(forFormatting());
        sb.append("\n");
        sb.append("SECUNDARY SCHOOL MANAGMENT SYSTEM APPLICATION    ---    Benedito Hilario Muianga\n");
        sb.append(forFormatting());
        sb.append("\n");
        sb.append("*Java Version - 11 \n");
        sb.append("*Spring Boot Version -  2.6.7  \n");                     // COLOCAR QUANTAS LINHAS DE CÓDIGO E DEPENDENCIAS!
        sb.append("*Dependencies : \n" +
                "STARTER CACHE, STARTER DATA JPA, STARTER VALIDATION, STARTER WEB, DEVTOOLS, H2, MYSQL, STARTER TEST, JSONWEBTOKEN, STARTER-SECURITY, SPRINGDOC, LOMBOK \n");
        sb.append("*Number of lines : 3170 \n");
        sb.append("*Number of Classes / Files : 124\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(forFormatting());
        sb.append("\n");
        return sb.toString();
    }

    private static String forFormatting() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 70; i++) {
            sb.append("=");
        }
        return sb.toString();
    }
}
