package hexlet.code;

public class App {
    public static void main(String[] args) {

        var v = new Validator();
        var schema = v.string().required();
        schema.isValid("");
        var numberSchema = v.number().required().positive().range(2, 6);
        numberSchema.isValid(4);
    }
}
